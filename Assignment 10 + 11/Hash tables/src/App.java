public class App {
    public static void main(String[] args) throws Exception {

        String[] codes = { "111 15", "984 99", "213 15", "964 89" };
        String[] zip1codes = { "111 15", "984 99"};
        long[] benchs = new long[4];
        long t1, t2;

        int k = 100;
        int i = 0;

        ZipV2 zip = new ZipV2("postnummer.csv");

        zip.BadLookup("121 15");
        zip.BadLookup("113 15");
        zip.BinaryLookup("112 15");
        zip.BinaryLookup("113 15");

        for (String c : zip1codes) {
            long sum = 0;
            for (int j = 0; j < k; j++) {
                t1 = System.nanoTime();
                zip.BadLookup(c);
                t2 = System.nanoTime();

                sum += t2 - t1;
            }
            benchs[i++] = sum / k;
        }
        for (String c : zip1codes) {
            long sum = 0;
            for (int j = 0; j < k; j++) {
                t1 = System.nanoTime();
                zip.BinaryLookup(c);
                t2 = System.nanoTime();

                sum += t2 - t1;
            }
            benchs[i++] = sum / k;
        }
        
        for (long l : benchs) {
            System.out.println(l);
        }

        // ZipV3 zipV3 = new ZipV3("./postnummer.csv");
        // zipV3.Lookup("112 15");
        // zipV3.Lookup("113 15");

        // i = 0;
        // for (String c : zip1codes) {
        //     long sum = 0;
        //     for (int j = 0; j < k; j++) {
        //         t1 = System.nanoTime();
        //         zipV3.Lookup(c);
        //         t2 = System.nanoTime();

        //         sum += t2 - t1;
        //     }
        //     benchs[i++] = sum / k;
        // }
        // for (long l : benchs) {
        //     System.out.println(l);
        // }

        // ZipV4 zipv4 = new ZipV4("./postnummer.csv");
        // zipv4.collisions(10000);
        // zipv4.collisions(20000);
        // zipv4.collisions(12345);
        // zipv4.collisions( 17389);
        // zipv4.collisions( 13513);
        // zipv4.collisions( 13600);
        // zipv4.collisions( 14000);
        // zipv4.collisions(9973);
        i = 0;
        ZipV5 zipv5 = new ZipV5("postnummer.csv");

        zipv5.Lookup("121 15");
        zipv5.Lookup("123 15");
        zipv5.Lookup("113 75");
        zipv5.Lookup("213 15");

        for (String c : codes) {
            long sum = 0;
            for (int j = 0; j < k; j++) {
                t1 = System.nanoTime();
                zipv5.Lookup(c);
                t2 = System.nanoTime();

                sum += t2 - t1;
            }
            benchs[i++] = sum/k;
        }

        for (long l : benchs) {
            //System.out.println(l);
        }

        ZipV6 zipv6 = new ZipV6("postnummer.csv",10000,9973);

        zipv6.Lookup("121 15");
        zipv6.Lookup("123 15");
        zipv6.Lookup("113 75");
        zipv6.Lookup("213 15");

        i = 0;
        for (String c : codes) {
            long sum = 0;
            for (int j = 0; j < k; j++) {
                t1 = System.nanoTime();
                zipv6.Lookup(c);
                t2 = System.nanoTime();

                sum += t2 - t1;
            }
            benchs[i++] = sum/k;
        }


        for (long l : benchs) {
            //System.out.println(l);
        }
        

        for (String c : codes) {
            //System.out.println(zipv6.LookupCount(c));
        }
    }
}
