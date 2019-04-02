public class Qthree11 {
    static class Node{
        int n ;
        Node next;
    }

    static class Link{
        Node first;
        int size = 0;

        public Link(Node node){
            this.first = node;
            size++;
        }
        public boolean isContain(int x){
            Node current = first;
            while (current!= null){
                if(current.n == x){
                    return true;
                }
                current = current.next;
            }
            return false;
        }
        public void addNode(int x){
            Node c = first;
            while(c.next != null){
                c = c.next;
            }
            Node add = new Node();
            add.n = x;
            add.next = null;
            c.next = add;
            size++;
        }
        public void deleteNode(int x){
            Node c = first;
            Node pre = new Node();
            while(c.n != x){
                pre = c;
                c = c.next;
            }
            pre.next = c.next;
            c.next = null;
            size--;

        }
        public int getSize(){
            return this.size;
        }
        public String toString(){
            StringBuffer buffer = new StringBuffer();
            Node current = first;
            while (current!= null){
                buffer.append(current.n+"  ");
                current = current.next;
            }
            return buffer.toString();
        }
    }

    public static void main(String[] args){
        Node node1 = new Node();
        node1.n = 1;
        Link link = new Link(node1);
        link.addNode(2);
        link.addNode(3);
        System.out.println("link的大小为："+link.getSize());
        System.out.println("link是否包含3："+link.isContain(3));
        System.out.println("link的元素为："+link.toString());
        link.deleteNode(3);
        System.out.println("link的元素为："+link.toString());
        System.out.println("link的大小为："+link.getSize());

    }
}
