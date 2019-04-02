import java.util.HashMap;

public class Core {
    private int READSIZE = 4096;
    private MinHeap minHeap = new MinHeap();
    private int kind = 0;
    private Node root;
    private HashMap<Integer,String> hashMap=new HashMap<Integer, String>();//利用散列表储存字符及对应编码

    private String leaveStr = "";
    private int leaveNumber = 0;
    private long lengthzips = 0;

    private int[] chars = new int[256];

    public void creatWeight(byte[] bytes,int len){
        for(int i = 0;i<len;i++){
            chars[bytes[i]+128]++;
        }
    }

    public void inMinHeaq(){//加入最小堆
        kind = 0;
        minHeap = new MinHeap();
        for(int i = 0;i < chars.length;i++){
            if(chars[i] != 0){
                minHeap.insert(new Node((char)i,chars[i]));
                kind++;
            }
        }
    }

    public void buildTree(){//构建Huffman树
        inMinHeaq();
        for(int i = 0;i < kind-1;i++){
            Node tmp = new Node();
            Node node_l = minHeap.deleteMin();
            tmp.setLeftChild(node_l);
            Node node_r = minHeap.deleteMin();
            tmp.setRightChild(node_r);
            tmp.setWeight(node_l.getWeight()+node_r.getWeight());
            minHeap.insert(tmp);
        }
        root = minHeap.deleteMin();
    }

    public void huffmanNodeCode(Node node,String code){//构建Huffman编码存入
        if(node.getKey() != '№'){
            hashMap.put((int)node.getKey(),code);
            return;
        }
        if(node.getLeftChild() != null){
            huffmanNodeCode(node.getLeftChild(),code+"0");
        }
        if(node.getRightChild() != null){
            huffmanNodeCode(node.getRightChild(),code+"1");
        }
    }

    public byte[] compress(byte[] bytes,int len){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <len ; i++) {
            res.append(hashMap.get(bytes[i]+128));
        }
        return str2Bytes(res.toString(),len);
    }

    public byte[] str2Bytes(String str,int len){ //字符串转换成byte[]
        str = leaveStr + str;
        int foot=str.length();
        int n=foot/8;
        int m=foot%8;
        int c=0;
        if(m>0 && len < READSIZE) c=1;
        byte[] to=new byte[n+c];
        for(int i=0;i<n;i++){
            to[i]=(byte)Integer.parseInt(str.substring(i * 8, i * 8 + 8), 2);
        }
        leaveStr = str.substring(n * 8,foot);
        if(m>0 && len < READSIZE){
            leaveNumber = m;
            String string = "00000000";
            to[n]=(byte)Integer.parseInt(str.substring(n * 8,foot) + string.substring(0,8 - m),2);
        }
        lengthzips += n + c;
        return to;
    }

    public void initial(){
        lengthzips = 0;
        leaveNumber = 0;
        buildTree();
        huffmanNodeCode(root,"");
    }

    public int[] getChars(){return chars;}
    public long getLengthzips(){return lengthzips;}
    public int getLeaveNumber(){return leaveNumber;}
}
