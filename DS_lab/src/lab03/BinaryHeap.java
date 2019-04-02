package lab03;

public class BinaryHeap {
    int currentSize;
    Integer[] array;


    public BinaryHeap(int capacity){
        currentSize = 0;
        array = new Integer[capacity+1];
    }
    public BinaryHeap(int[] items){
        currentSize = items.length;
        array =  new Integer[(currentSize+2)*11/10];

        int i = 1;
        for(int item : items)
            array[i++] = item;
        buildHeap();
    }
    public void insert(int x){
        if(currentSize == array.length-1)
            enlargeArray(array.length*2+1);

        int hole = ++currentSize;
        for(array[0] = x;x < array[hole/2];hole /= 2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }
    public int findMin(){
        return array[1];
    }
    public int deleteMin(){
        if(isEmpty())
            return 0;
        int minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;

    }
    public boolean isEmpty(){
        return currentSize == 0;
    }
    public void makeEmpty(){
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }



    public void buildHeap(){
        for(int i = currentSize/2;i>0;i--)
            percolateDown(i);
    }
    public void percolateDown(int hole){

        int child;
        int tmp = array[hole];
        for(; hole * 2 <= currentSize; hole = child){
            child = hole*2;
            if(child != currentSize && array[child+1]<array[child])
                child++;
            if(array[child]<tmp)
                array[hole] = array[child];
            else break;
        }
        array[hole] = tmp;
    }

    public void enlargeArray(int size){
        Integer[] arrayNew = new Integer[size];
        for(int i =0;i<array.length;i++)
            arrayNew[i] = array[i];
        array = arrayNew;
    }
}
