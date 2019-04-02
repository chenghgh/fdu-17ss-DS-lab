package lab03;
import java.util.ArrayList;

public class EventSimulator {
    int tellers;
    ArrayList<Customer> arrivalLine;

    public EventSimulator(int tellers, ArrayList<Customer> arrivalLine) {
        this.tellers = tellers;
        this.arrivalLine = arrivalLine;
    }

    public void setArrivalLine(ArrayList<Customer> arrivalLine) {
        this.arrivalLine = arrivalLine;
    }

    public void simulate() {
        BinaryHeap heap = new BinaryHeap(3);
        int i = 0;
        while (i < arrivalLine.size()) {
            if (heap.currentSize < tellers) {
                heap.insert(arrivalLine.get(i).arrival + arrivalLine.get(i).service);
                int sum = arrivalLine.get(i).arrival + arrivalLine.get(i).service;
                System.out.println("Tick " + arrivalLine.get(i).arrival + ": process customer who arrival at tick " +
                        arrivalLine.get(i).arrival + " and leave at tick " + sum);
                i++;
            }
            else {
                int temp = heap.findMin();
                heap.insert(heap.deleteMin() + arrivalLine.get(i).service);
                int sum = temp + arrivalLine.get(i).service;
                System.out.println("Tick " + temp + ": process customer who arrival at tick " +
                        arrivalLine.get(i).arrival + " and leave at tick " + sum);
                i++;
                }
        }
    }
}




