package lab02;

public class MyDeque {

    DLNode head = new DLNode(0);
    DLNode tail = new DLNode(0);
    int size = 0;

    MyDeque(DLNode node){
        node.setPrev(head);
        node.setNext(tail);
        head.setNext(node);
        tail.setPrev(node);
        size++;
    }
    public void insertFirst(DLNode node){
        node.setNext(head.getNext());
        node.setPrev(head);//node.prev = head;
        head.getNext().setPrev(node);//head.next.prev = node;
        head.setNext(node);//head.next = node;
        size++;
    }
    public DLNode removeFirst(){
        DLNode tmp = head.getNext();
        head.getNext().getNext().setPrev(head);
        head.setNext(head.getNext().getNext());
        tmp.setPrev(null);
        tmp.setNext(null);
        return tmp;
    }
    public void insertLast(DLNode node){
        node.setPrev(tail.getPrev());//node.prev = tail.prev;
        node.setNext(tail);//node.next = tail;
        tail.getPrev().setNext(node);//tail.prev.next = node;
        tail.setPrev(node);//tail.prev = node;
        size++;

    }

    public DLNode removeLast() {
        DLNode tmp = tail.getPrev();
        tail.getPrev().getPrev().setNext(tail);//tail.prev.prev.next = tail;
        tail.setPrev(tail.getPrev().getPrev());//tail.prev = tail.prev.prev;
        tmp.setNext(null);
        tmp.setPrev(null);
        size--;
        return tmp;

    }
    public DLNode first(){
        return head.getNext();
    }
    public DLNode last(){
        return tail.getPrev();
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
        String str = " ";
        DLNode node = head;
        while (node.getNext() != tail){
            str += " " + node.getNext().getElement();
            node = node.getNext();
        }
        return str;
    }

}
