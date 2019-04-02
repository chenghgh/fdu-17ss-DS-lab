package lab02;

public class QueueAdaptor extends MyQueue {

    public QueueAdaptor(MyDeque myDeque){
        super(myDeque);
    }
    @Override
    public void enqueue(DLNode node) {
        myDeque.insertLast(node);
    }

    @Override
    public DLNode dequeue() {
        return myDeque.removeFirst();
    }

    @Override
    public DLNode front() {
        return myDeque.first();
    }

    @Override
    public int size() {
        return myDeque.size;
    }

    @Override
    public boolean isEmpty() {
        return myDeque.isEmpty();
    }

//    public String toString() {
//        lab02.DLNode count = myDeque.first();
//        while(count.next!=null){
//            if(count.element != 0){
//                System.out.print(count.element + " ");
//                count = count.getNext();
//            }
//        }return "";
//    }
}
