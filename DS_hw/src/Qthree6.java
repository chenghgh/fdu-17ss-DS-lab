import java.util.Scanner;

public class Qthree6 {
    public static void main(String[] args) {
        System.out.print("先输 m 后输 n : ");
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();

        long start = System.nanoTime();

        if(m == 1){
            System.out.println("剩下的数字为："+ n);
            long end = System.nanoTime();
            System.out.println("运行时间为："+ (end - start));
            System.exit(0);
        }

        Node first = new Qthree6().startRun(n);
        int count = 1;
        while(first.next != first) {
            first = first.next;
            count++;
            if(m == count) {
                first.previous.next = first.next;
                first.next.previous = first.previous;
                first = first.next;
                count = 1;
            }
        }

        long end = System.nanoTime();
        System.out.println("剩下的数字为："+first.n);
        System.out.println("运行时间为："+ (end - start));
    }

    //给链表复制，从大到小
    public Node startRun(int n) {
        Node first = new Node();
        first.previous = null;
        first.n = n ;
        Node current = first;
        Node last = first;

        while((--n)>0) {
            current.next = new Node();
            current = current.next;
            current.n = n;
            current.previous = last;
            last = current;
        }
        current.next = first;
        first.previous = current;
        return first;
    }

    static class Node {
        int n ;
        Node next;
        Node previous;
    }
}
