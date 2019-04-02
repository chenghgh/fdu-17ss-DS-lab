package lab02;

public abstract class MyQueue {
    public MyDeque myDeque;
    MyQueue(MyDeque myDeque){
        this.myDeque = myDeque;
    }

    public abstract void enqueue(DLNode node);
    public abstract DLNode dequeue();
    public abstract DLNode front();
    public abstract int size();
    public abstract boolean isEmpty();

    public String toString(){
        String s = myDeque.toString();
        return s;
    };
}
