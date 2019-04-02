public class Uncompress {

    private long lengthzips = 0;
    private int READSIZE = 4096;
    private Node root;
    private Node left;
    private int leaveNumber = 0;

    private int[] chars = new int[256];
    private MinHeap minHeap = new MinHeap();
    private int kind = 0;

    Object[] uncompress(byte[] bytes,long len){//解码
        lengthzips = lengthzips - len;
        Node now = root;
        if(left != null){
            now = left;
        }
        byte []bytes1 = new byte[READSIZE];
        int k = 0;
        for (int i = 0; i < len; i++) {
            byte aByte = bytes[i];
            if(lengthzips == 0 && i == len - 1 && leaveNumber != 0){//lengthzips等于零表示读到最后一个字节
                for (int j = 1; j <= leaveNumber ; j++) {
                    if (gitBit(j, aByte) == 0 && now.getLeftChild() != null) {
                        now = now.getLeftChild();
                    } else if (gitBit(j, aByte) == 1 && now.getRightChild() != null) {
                        now = now.getRightChild();
                    } else {
                        if(k == bytes1.length){
                            byte [] temp = new byte[bytes1.length * 2];
                            System.arraycopy(bytes1,0,temp,0,bytes1.length);
                            bytes1 = temp;
                        }
                        bytes1[k++] = (byte)(now.getKey() - 128);
                        left = null;
                        now = gitBit(j, aByte) == 0 ? root.getLeftChild() : root.getRightChild();
                    }
                    if(j == leaveNumber){
                        if(k == bytes1.length){
                            byte [] temp = new byte[bytes1.length * 2];
                            System.arraycopy(bytes1,0,temp,0,bytes1.length);
                            bytes1 = temp;
                        }
                        bytes1[k++] = (byte)(now.getKey() - 128);
                        left = null;
                    }
                }
            }else{
                for (int j = 1; j <= 8 ; j++) {
                    if (gitBit(j, aByte) == 0 && now.getLeftChild() != null) {
                        now = now.getLeftChild();
                    } else if (gitBit(j, aByte) == 1 && now.getRightChild() != null) {
                        now = now.getRightChild();
                    }else{
                        if(k == bytes1.length){
                            byte [] temp = new byte[bytes1.length * 2];
                            System.arraycopy(bytes1,0,temp,0,bytes1.length);
                            bytes1 = temp;
                        }
                        bytes1[k++] = (byte)(now.getKey() - 128);
                        now = gitBit(j, aByte) == 0 ? root.getLeftChild() : root.getRightChild();
                    }
                    if(i == len - 1 && now.getKey() == '№' && j == 8){
                        left = now;
                    }else if(i == len - 1 && j == 8){
                        if(k == bytes1.length){
                            byte [] temp = new byte[bytes1.length * 2];
                            System.arraycopy(bytes1,0,temp,0,bytes1.length);
                            bytes1 = temp;
                        }
                        bytes1[k++] = (byte)(now.getKey() - 128);
                        left = null;
                    }
                }
            }

        }
        return new Object[]{bytes1,k};
    }

    public int gitBit(int index, byte b){
        return (b & (1 << 8 - index))>>(8 - index);
    }

    public void rebuildHuffmanTree(Object tree){//重构huffman树
        chars = (int[])tree;
        buildTree();
    }

    public void inMinHeaq(){//加入最小堆
        kind = 0;
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

    public void setLeaveNumber(int leaveNumber) {
        this.leaveNumber = leaveNumber;
    }

    public void setLengthzips(long lengthzips) {
        this.lengthzips = lengthzips;
    }

    public void emptyLeave(){
        left = null;
    }
}
