package lab02;

public class DLNode {

    DLNode next;
    DLNode prev;
    int  element;

    DLNode(int element){
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public int getElement(){
        return this.element;
    }
    public void setElement(int element){
        this.element = element;
    }
    public DLNode getPrev() {
        return this.prev;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }

}
