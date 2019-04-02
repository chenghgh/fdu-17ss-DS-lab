package lab03;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        ArrayList<Customer> arrivalLine = new ArrayList<Customer>();
        arrivalLine.add(new Customer(0,8));
        arrivalLine.add(new Customer(1,5));
        arrivalLine.add(new Customer(2,3));
        arrivalLine.add(new Customer(4,1));
        arrivalLine.add(new Customer(4,5));
        arrivalLine.add(new Customer(4,4));
        arrivalLine.add(new Customer(5,4));
        arrivalLine.add(new Customer(6,1));
        arrivalLine.add(new Customer(7,3));
        EventSimulator es = new EventSimulator(3,arrivalLine);
        es.simulate();
    }
}
