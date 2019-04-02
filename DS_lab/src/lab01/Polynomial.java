package lab01;

public class Polynomial {
    private Term head;
    private Term tail;

    public Polynomial(Term firstTerm){
        this.head = firstTerm;
    }
    public Term getFirst(){
        return this.head;
    }
    public void setFirst(Term first){
        this.head = first;
    }
    // add a single term to the polynomial

    public void addTerm(Term term){
        addTerm2(head,term);
    }
    public void addTerm2(Term current ,Term term){


        if (term.getExponent() > current.getExponent()){
            term.setNext(current);
            head=term;
        }
        if (term.getExponent() == current.getExponent()){
            current.setCoefficient(current.getCoefficient()+term.getCoefficient());
        }
        if (term.getExponent() < current.getExponent()){
            if (current.getNext() != null){
                if (term.getExponent() > current.getNext().getExponent()){
                    term.setNext(current.getNext());
                    current.setNext(term);
                }
                else {
                    addTerm2(current.getNext(),term);
                }
            }
            else {
                current.setNext(term);
            }
        }

        }

    // add another polynomial, return the sum
    public Polynomial add(Polynomial another) {

        Term termA = another.head;
        addTerm(new Term(termA.getCoefficient(),termA.getExponent()));
        while (termA.getNext() != null){
            addTerm(new Term(termA.getNext().getCoefficient(),termA.getNext().getExponent()));
            termA = termA.getNext();
        }
        return this;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Term current = head;
        while(current!=null){
            buffer.append("("+current.getCoefficient()+"x^"+current.getExponent()+")+");
            current = current.getNext();
        }
        return buffer.toString();
    }
}
