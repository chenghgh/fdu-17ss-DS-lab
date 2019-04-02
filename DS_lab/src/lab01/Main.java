package lab01;

public class Main {
    public static void main(String[] args){

        Term t1 = new Term(3,4);
        Term t2 = new Term(-6,0);
        Term t3 = new Term(-10,2);
        Polynomial p1 = new Polynomial(t3);
        p1.addTerm(t2);
        p1.addTerm(t1);
        System.out.println(p1);

        Term t4 = new Term(2,5);
        Term t5 = new Term(-4,4);
        Term t6 = new Term(5,1);
        Term t7 = new Term(9,2);
        Polynomial p2 = new Polynomial(t4);
        p2.addTerm(t5);
        p2.addTerm(t6);
        p2.addTerm(t7);
        System.out.println(p2);
        Polynomial result = p1.add(p2);
        System.out.println(result);
    }
}
