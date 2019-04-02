package lab03;

public class Customer {
    int arrival;
    int service;
    public Customer(int arrival, int service){
        this.arrival = arrival;
        this.service = service;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
}
