package lab01;

public class Term {

    private double coef ;
    private int exp;
    private Term next;

    public Term(double coef,int exp){
       this.coef = coef;
       this.exp = exp;
    }
    public double getCoefficient(){
        return this.coef;
    }
    public void setCoefficient(double coef){
        this.coef = coef;
    }
    public int getExponent(){
        return this.exp;
    }
    public void setExponent(int exp){
        this.exp = exp;
    }
    public Term getNext(){
        return this.next;
    }
    public void setNext(Term next){
        this.next = next;
    }


}
