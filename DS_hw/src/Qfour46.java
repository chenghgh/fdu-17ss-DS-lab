public class Qfour46 {
    private class Node{
        Node left;
        Node right;
        int n;
    }
    private static boolean isSimilar(Node a, Node b){
        if(a == null|| b == null) {
            return a == null && b == null;
        }
        return isSimilar(a.left,b.left)&&isSimilar(a.right,b.right);
    }
}
