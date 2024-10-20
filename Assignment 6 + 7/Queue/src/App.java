
public class App {

    public static void generate(int n, JQueue<Integer> q) {
        for (int i = 0; i < n; i++) {
            q.enqueue(i);
        }
    }

    private static long bench(int n) {

        JQueue<Integer> iq = new ImprovedQueue<Integer>();
        JQueue<Integer> q = new Queue<Integer>();
        JQueue<Integer> aq = new ArrayQueue<Integer>(Integer.class);
        //generate(n,iq);
        //generate(n, q);
        //generate(n, aq);
        long sum = 0;

        for (int i = 0; i < n; i++) {
            long t0 = System.nanoTime();
            iq.enqueue(5);
            //iq.dequeue();
            long t1 = System.nanoTime();
            sum += (t1 - t0);
        }
        


        return (sum/n);
    }

    public static void main(String[] args) throws Exception {

        //JIT warmup
        bench(10000);

        int k = 100;
        for (int n = 100; n < 10000; n *= 1.2) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t = bench(n);
                if (t < min) {
                    min = t;
                }
            }
            if (min == 0) {
                min = 1;}
            System.out.print("(" + n + "," + min + ")");
        }
        
    }
}
