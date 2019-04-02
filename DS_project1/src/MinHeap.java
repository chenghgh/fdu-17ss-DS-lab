public class MinHeap{
    private int currentSize;
    private Node[] array;


    public MinHeap(){
        currentSize = 0;
        array = new Node[100];
    }

    public MinHeap(Node[] items){
        currentSize = items.length;
        array =  new Node[(currentSize+2)*11/10];

        int i = 1;
        for(Node item : items)
            array[i++] = item;

        buildHeap();
    }
    public void insert(Node x){
        if(currentSize == array.length-1)
            enlargeArray(array.length*2+1);

        int hole = ++currentSize;
        for(array[0] = x;x.getWeight() < array[hole/2].getWeight();hole /= 2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }
    public Node findMin(){
        return array[1];
    }
    public Node deleteMin(){
        if(isEmpty())
            return null;
        Node minItem = findMin();
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
        Node tmp = array[hole];
        for(; hole * 2 <= currentSize; hole = child){
            child = hole*2;
            if(child != currentSize && array[child+1].getWeight()<array[child].getWeight())
                child++;
            if(array[child].getWeight()<tmp.getWeight())
                array[hole] = array[child];
            else break;
        }
        array[hole] = tmp;
    }
    public void enlargeArray(int size){
        Node[] arrayNew = new Node[size];
        for(int i =0;i<array.length;i++)
            arrayNew[i] = array[i];
        array = arrayNew;
    }

}

