import java.io.*;

public class DoFile {

    private int READSIZE = 4096;

    private File zipFile;
    private File sourceFile;
    private File tmpFile;
    private int type;
    private long len = 0;

    private FileOutputStream fileOutputStream;
    private FileOutputStream tmpOutputStream;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private Core core;
    private Uncompress uncompress;

    public DoFile(String zipFileName,String sourceFileName){//压缩
        sourceFile = new File(sourceFileName);
        zipFile = new File(zipFileName);
    }

    public DoFile(String zipFileName){//解压缩
        zipFile = new File(zipFileName);
    }//解压

    public void doCompress() throws Exception{
        fileOutputStream = new FileOutputStream(zipFile);
        core = new Core();
        directory(sourceFile,sourceFile.getName());
        fileOutputStream.close();
    }

    public void doUncompress() throws Exception{
        fileInputStream = new FileInputStream(zipFile);
        uncompress = new Uncompress();
        try{
            while(firstRead()){
                if(type == 0 || type == 3)
                    continue;
                while (len > 0){
                    byte[] bytes;
                    if(len > READSIZE)
                        bytes = new byte[READSIZE];
                    else bytes = new byte[(int) len];
                    int lenb = fileInputStream.read(bytes);
                    Object[] cons = uncompress.uncompress(bytes,lenb);
                    fileOutputStream.write((byte[])cons[0],0,(int)cons[1]);
                    len = len - bytes.length;
                }
                fileOutputStream.close();
            }
            fileInputStream.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean firstRead() throws IOException, ClassNotFoundException { //预读文件头
        try{
            objectInputStream = new ObjectInputStream(fileInputStream);
        }catch (EOFException ignored){}

        int mytype;
        Object object;
        try {
                mytype = objectInputStream.readByte();
                if(mytype == 1){
                    type = mytype;
                    object = objectInputStream.readObject();
                    String entry = (String) object;
                    object = objectInputStream.readObject();
                    uncompress.rebuildHuffmanTree(object);

                    int leaveNumber = (Integer)objectInputStream.readObject();
                    uncompress.setLeaveNumber(leaveNumber);
                    len = (Long) objectInputStream.readObject();
                    uncompress.setLengthzips(len);
                    String name = getCurrentFilePath(entry);
                    checkPath(name);
                    File currentFile = new File(name);
                    fileOutputStream = new FileOutputStream(currentFile);
                    uncompress.emptyLeave();
                }else{
                    type = mytype;
                    object = objectInputStream.readObject();
                    String entry = (String) object;
                    String name = getCurrentFilePath(entry);
                    if(type == 0){
                        checkPath(name + "\\");
                    }else{
                        checkPath(name);
                        File empty = new File(name);
                        if(empty.exists()){
                            empty.delete();
                        }
                        empty.createNewFile();
                    }

                }
                return true;

        }catch (EOFException e){
            objectInputStream.close();
            fileInputStream.close();
            return false;
        }
    }

    private void directory(File sourceFile, String base)  throws Exception {
        if(sourceFile.isDirectory()){
            File[] flist = sourceFile.listFiles();

            assert flist != null;
            if(flist.length==0){
                title(  base+"\\",0);
            }
            else{
                for (File aFlist : flist) {
                    directory(aFlist, base + "\\" + aFlist.getName());
                }
            }
        }
        else{
            if(sourceFile.length() == 0){//空文件
                title(base,3);
            }
            else {
                FileInputStream fis1 = new FileInputStream(sourceFile);
                int len;
                byte[] buf = new byte[READSIZE];
                while ((len = fis1.read(buf)) != -1) {
                    scan(buf,  len);//扫描文件
                }
                title(base,1);
                fis1.close();
                FileInputStream fis2 = new FileInputStream(sourceFile);
                while ((len = fis2.read(buf)) != -1) {
                    byte[] bytes = core.compress(buf,len);
                    tmpOutputStream.write(bytes);
                }
                tmpOutputStream.close();
                tmp2file();
                fis2.close();
            }

        }
    }

    private void scan(byte[] bytes, int len){core.creatWeight(bytes,len);}

    private String getCurrentFilePath(String oldPath){//解压到当前目录
        return zipFile.getAbsolutePath().replaceAll(zipFile.getName(),"") + oldPath;
    }

    private void checkPath(String currentPath){
        for(int j = 0;j < currentPath.length();j++){
            if(currentPath.charAt(j) == '\\'){
                File file = new File(currentPath.substring(0,j));
                if(!file.exists()){
                    file.mkdir();
                }
            }
        }
    }

    private void title(String nextEntry, int type) throws IOException {
        if(type == 1){
            tmpFile = new File(sourceFile.getAbsolutePath().replace(sourceFile.getName(),"$" + sourceFile.getName()));
            tmpOutputStream = new FileOutputStream(tmpFile);
            core.initial();

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeByte(1);//写入类型1
            objectOutputStream.writeObject(nextEntry);//写入文件名
            objectOutputStream.writeObject(core.getChars());//写入字符集
        }else{
            objectOutputStream = new ObjectOutputStream(fileOutputStream);//空文件夹只需要写入类型0  文件名
            objectOutputStream.writeByte(type);
            objectOutputStream.writeObject(nextEntry);
        }
    }

    private void tmp2file() throws IOException {//临时文件写到file
        objectOutputStream.writeObject(core.getLeaveNumber());
        objectOutputStream.writeObject(core.getLengthzips());
        byte[] buf = new byte[2048];
        fileInputStream = new FileInputStream(tmpFile);
        int len;
        while((len = fileInputStream.read(buf)) != -1){
            fileOutputStream.write(buf,0,len);
        }
        fileInputStream.close();
        tmpFile.delete();
    }
}