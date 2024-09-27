import java.util.Random;

public class App {
    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        while (true) {
            int index = (first + last) / 2;

            if (array[index] == key) {
                return true;
            }

            if (array[index] < key && index < last) {
                first = index + 1;
                continue;
            }

            if (array[index] > key && index > first) {
                last = index;
                continue;
            }

            return false;
        }
    }

    private static boolean recursive(int[] arr, int key, int min, int max) {
        int mid = min + ((max - min) / 2);
        if (arr[mid] == key) {
            return true;
        }
        if ((arr[mid] > key) && (min < mid)) {
            return recursive(arr, key, min, mid);
        }
        if ((arr[mid] < key) && (mid < max)) {
            return recursive(arr, key, mid + 1, max);
        }
        return false;
    }

    public static boolean recursive_search(int[] array, int key) {
        return recursive(array, key, 0, array.length - 1);
    }
        

    public static boolean unsorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean sorted_search(int[] array, int key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == key) {
                return true;
            }
            if(array[index] > key){
                return false;
            }
        }
        return false;
    }

    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }
        
        
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
        int sum = 0;
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    private static long search(int n, int loop) {
        Random rnd = new Random();
        int[] array = new int[n];
        // for (int i = 0; i < n; i++) {
        //     array[i] = rnd.nextInt(n * 2);
        // }

        array = sorted(n);

        int[] keys = new int[loop];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n * 2);
        }
        //int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            if (recursive_search(array, key)) {
                break;
            }
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    private static long duplicates(int n) {
        Random rnd = new Random();
        int[] array_a = new int[n];
        for (int i = 0; i < n; i++) {
            array_a[i] = rnd.nextInt(n*2);
        }
        int[] array_b = new int[n];
        for (int i = 0; i < n; i++) {
        array_b[i] = rnd.nextInt(n*2);
        }
        int sum = 0;
        long t0 = System.nanoTime();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                int key = array_a[i];
                for (int j = 0; j < n; j++) {
                    if (key == array_b[j]) {
                        sum++;
                        break;
                    }
                }
             }
        }
        long t1 = System.nanoTime();
        return t1 - t0;
    }


    public static void main(String[] args) throws Exception {

        int[] sizes = { 100, 200, 400, 800, 1600, 3200, 4800, 9600, 19200, 38400, 76800, 153600, 307200, 614400, 1228800};
        
        // JIT warmup
        duplicates(1000);
        search(1000, 1000);
        
        int loop = 1000;
        int k = 10;
        for(int n : sizes) {
            long min1 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
                if (t < min1)
                    min1 = t;
            }
            long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
                if (t < min2)
                    min2 = t;
            }
            long min3 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
                if (t < min3) min3 = t;
            }
            System.out.print("(" + n + "," + (min1+min2+min3)/3 + ")");
        }
    }
}
