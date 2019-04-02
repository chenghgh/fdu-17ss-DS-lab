public class MinHeap{
    private int currentSize;
    private Vertex[] array;


    public MinHeap(){
        currentSize = 0;
        array = new Vertex[1000];
    }

    public MinHeap(Vertex[] items){
        currentSize = items.length;
        array =  new Vertex[(currentSize+2)*11/10];

        int i = 1;
        for(Vertex item : items)
            array[i++] = item;

        buildHeap();
    }
    public void insert(Vertex x){
        if(currentSize == array.length-1)
            enlargeArray(array.length*2+1);

        int hole = ++currentSize;
        for(array[0] = x;x.dist < array[hole/2].dist;hole /= 2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }
    public Vertex findMin(){
        return array[1];
    }
    public Vertex deleteMin(){
        if(isEmpty())
            return null;
        Vertex minItem = findMin();
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
        Vertex tmp = array[hole];
        for(; hole * 2 <= currentSize; hole = child){
            child = hole*2;
            if(child != currentSize && array[child+1].dist<array[child].dist)
                child++;
            if(array[child].dist<tmp.dist)
                array[hole] = array[child];
            else break;
        }
        array[hole] = tmp;
    }
    public void enlargeArray(int size){
        Vertex[] arrayNew = new Vertex[size];
        for(int i =0;i<array.length;i++)
            arrayNew[i] = array[i];
        array = arrayNew;
    }

}

