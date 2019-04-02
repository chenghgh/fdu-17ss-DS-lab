package lab02;

public abstract class MyStack {

    public MyDeque myDeque;
    MyStack(MyDeque myDeque){
        this.myDeque = myDeque;
    }


    public abstract void push(DLNode node);
    public abstract DLNode pop();
    public abstract DLNode top();
    public abstract int size();
    public abstract boolean isEmpty();

    public String toString(){
        String s = myDeque.toString();
        return s;
    };
}
