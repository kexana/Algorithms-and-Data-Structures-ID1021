import java.util.Random;

public class App {

    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length ; i++) {
            sum = sum + array[indx[i]];
        }
        return sum;
    }
    
    public static long bench(int n, int loop) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++)
            indx[i] = rnd.nextInt(n);
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int candidate = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[candidate]) {
                    candidate = j;
                }
            }
            swap(array, i, candidate);
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int point = i;
            for (int j = i - 1; ((j > -1) && (array[point] < array[j])); j--) {
                swap(array, point, j);
                point--;
            }
        }
        // for (int i : array) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
    }
    
    public static void mergeSort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        mergeSortSort(org, aux, 0, org.length - 1);
        // for (int i : org) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
    }
    
    private static void mergeSortSort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi) / 2;
            // sort the items from lo to mid
            mergeSortSort(org, aux, lo, mid);
            // sort the items from mid+1 to hi
            mergeSortSort(org, aux, mid+1, hi);
            // merge the two sections using the additional array
            mergeSortMerge(org, aux, lo, mid, hi);
        }
        return;
    }

    private static void mergeSortMerge(int[] org, int[] aux, int lo, int mid, int hi) {
        // copy all items from lo to hi from org to aux
        for (int i = lo; i <= hi; i++) {
            aux[i] = org[i];
        }
        // let's do the merging
        int i = lo; // the index in the first part
        int j = mid + 1; // the index in the second part
        // for all indices from lo to hi
        for (int k = lo; k <= hi; k++) {
            // if i is greater than mid then
            // move the j'th item to the org array, update j
            if (i > mid) {
                org[k] = aux[j];
                j++;
            } else if (j > hi) {
                org[k] = aux[i];
                i++;
            } else if (aux[i] <= aux[j]) {
                org[k] = aux[i];
                i++;
            } else {
                org[k] = aux[j];
                j++;
            }
            // else if j is greate than hi then
            // move the i'th item to the org array, update i
            // else if the i'th item is smaller or equal to the j'th item,
            // move the i'th item to the org array, update i
            // else
            // move the j'th item to the org array, update j
        }
    }
    
    public static void mergeSortV2(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];

        for (int i = 0; i <= org.length-1; i++) {
            aux[i] = org[i];
        }

        mergeSortSortV2(org, aux, 0, org.length - 1);
        // for (int i : org) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();
    }
    
    private static void mergeSortSortV2(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi) / 2;

            mergeSortSortV2(aux, org, lo, mid);

            mergeSortSortV2(aux, org, mid + 1, hi);
            
            mergeSortMergeV2(org, aux, lo, mid, hi);
        }
        return;
    }

    private static void mergeSortMergeV2(int[] org, int[] aux, int lo, int mid, int hi) {

        int i = lo;
        int j = mid+1;
        for ( int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j];
                j++;
            } else if (j > hi) {
                org[k] = aux[i];
                i++;
            } else if (aux[i] <= aux[j]) {
                org[k] = aux[i];
                i++;
            } else {
                org[k] = aux[j];
                j++;
            }
        }
    }
        

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static long sort(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2);
        }
        long t0 = System.nanoTime();
        mergeSortV2(array);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) throws Exception {

        int[] sizes = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500};
        
        // JIT warmup
        sort(1000);
    
        int k = 1000;
        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = sort(n);
                if (t < min1)
                    min1 = t;
            }
            System.out.print("(" + n + "," + min1 + ")");
        }
        
    }
}
