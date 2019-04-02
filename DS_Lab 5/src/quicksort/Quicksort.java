package quicksort;

import org.junit.Test;

//import javax.swing.plaf.synth.SynthDesktopIconUI;

public class Quicksort {

    private Quicksort() { }
    private static int M;
    // Your code here
    public static int[] sort(int[] unsorted) {
    	quicksort(unsorted,0,unsorted.length-1);
        return unsorted;
    }

    private static void quicksort(int[] array, int left, int right) {

        if(left + M >= right){
            int pivotindex = findpivot (array, left, right);
            swapReference(array, pivotindex, right);
            int k = partition (array, left-1, right, array[right]);
            swapReference(array, k, right);
            if ((k-left)>1) quicksort (array, left, k-1);
            if ((right-k)>1) quicksort (array, k+1, right);
        }
        else insertionSort(array,left,right);
    }

    static int partition (int[] array, int l, int r, int pivot) {
        do {
            while (array[++l] < pivot);
            while ((r!=0) && (array[--r] > pivot));
            swapReference(array, l, r);
        } while (l<r);
        swapReference(array, l, r);
        return l;
    }

    private static void insertionSort(int[] a, int left, int right) {
        int j;
        for(int p = 1;p < a.length;p++){
            int tmp = a[p];
            for(j = p;j > 0 && tmp<a[j-1];j--)
                a[j] = a[j-1];
            a[j] = tmp;
        }
    }

    private static int findpivot(int[] a, int left, int right) {
        int center = (left+right)/2;
        if(a[center] < a[left])
            swapReference(a,left,center);
        if(a[right] < a[left])
            swapReference(a,left,right);
        if(a[right] < a[center])
            swapReference(a,center,right);
        swapReference(a,center,right-1);
        return a[right-1];
    }

    private static void swapReference(int[] a, int left, int right) {
        int tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
    }


    // Your code here
    public static void main(String[] args) {
        for(M = 0;M <= 30;M++){
            long start = System.currentTimeMillis();
            TestQuicksort test = new TestQuicksort();
            test.testQuicksort();
            long end = System.currentTimeMillis();
            System.out.println(M+"  "+(end-start) + " ms");
        }

    }
}
