import java.util.LinkedList;

public class HuffmNode {
    public long data;
    public int index;

    public HuffmNode left;
    public HuffmNode right;

    public HuffmNode(long data,int index){
        this.data = data;
        this.index = index;
    }

    public long getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public HuffmNode getLeft() {
        return left;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLeft(HuffmNode left) {
        this.left = left;
    }

    public HuffmNode getRight() {
        return right;
    }

    public void setRight(HuffmNode right) {
        this.right = right;
    }
}
