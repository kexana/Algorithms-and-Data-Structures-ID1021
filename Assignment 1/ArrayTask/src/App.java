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
        int sum = 0;
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    private static long search(int n, int loop) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n * 2);
        }
        int[] keys = new int[loop];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n * 2);
        }
        //int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            for (int j = 0; j < n; j++) {
                if (key == array[j]) {
                    //sum++;
                    break;
                }
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

        int[] sizes = {100, 200, 400, 800, 1600, 3200};
        // JIT warmup
        duplicates(1000);
        
        int loop = 1000;
        int k = 10;
        for(int n : sizes) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = duplicates(n);
                if (t < min) min = t;
            }
            System.out.println(n + " " + min + " ns");
        }
    }
}
