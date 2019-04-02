package lab02;

public class StackAdaptor extends MyStack {

    public StackAdaptor(MyDeque myDeque){
        super(myDeque);
    }

    @Override
    public void push(DLNode node) {
        myDeque.insertLast(node);
    }

    @Override
    public DLNode pop() {
        return myDeque.removeLast();
    }

    @Override
    public DLNode top() {
        return myDeque.last();
    }

    @Override
    public int size() {
        return myDeque.size();
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
