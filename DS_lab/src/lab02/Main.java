package lab02;

public class Main {
    public static void main(String[] args){
        DLNode temp = null;

        System.out.println("Stack");
        MyStack stack = new StackAdaptor(new MyDeque(new DLNode(1)));// 1
        System.out.println("1: " + stack.toString());
        stack.push(new DLNode(2));
        System.out.println("2: " + stack.toString());//12
        temp = stack.pop();
        System.out.println("pop " + temp.getElement());
        System.out.println("3: " + stack.toString());//1
        temp = stack.pop();
        System.out.println("pop " + temp.getElement());
        System.out.println("4: " + stack.toString());//
        stack.push(new DLNode(3));
        System.out.println("5: " + stack.toString());//3

        System.out.println("Queue");
        MyQueue queue = new QueueAdaptor(new MyDeque(new DLNode(1)));
        System.out.println("1: " + queue.toString());//1
        temp = queue.dequeue();
        System.out.println("dequeue " + temp.getElement());
        System.out.println("2: " + queue.toString());//
        queue.enqueue(new DLNode(2));
        System.out.println("3: " + queue.toString());//2
        queue.enqueue(new DLNode(3));
        System.out.println("4: " + queue.toString());//23
        temp = queue.dequeue();
        System.out.println("dequeue " + temp.getElement());
        System.out.println("5: " + queue.toString());//3
    }

}
