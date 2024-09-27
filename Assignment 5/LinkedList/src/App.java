public class App {
    
    private static int[] genArr(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        return res;
    }

    private static long bench(int sizeA, int sizeB) {

        LinkedList a = new LinkedList(sizeA);
        LinkedList b = new LinkedList(sizeB);
        int[] one = genArr(sizeA);
        int[] two = genArr(sizeB);
        long t0 = System.nanoTime();
        a.append(b);
        //arrayAppend(one, two);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static int[] arrayAppend(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        
        // JIT warmup
        bench(1000,1000);
    
        int k = 1000;
        int b = 1500;
        for (int n = 100; n < 100000; n *= 1.5) {
            long min1 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = bench(n,b);
                if (t < min1)
                    min1 = t;
            }
            System.out.print("(" + n + "," + min1 + ")");
        }
    }
}
