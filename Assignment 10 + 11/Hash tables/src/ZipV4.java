import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV4 {
    Area[] postnr;
    int max = 10000;

    public class Area {

        private String zipcode;
        private String name;
        private Integer population;

        public Area(String zipcode, String name, Integer population) {
            this.zipcode = zipcode;
            this.name = name;
            this.population = population;
        }
    }

    public ZipV4(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                postnr[i++] = new Area(row[0], row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }

    }

    public boolean Lookup(String zip) {
        if (postnr[Integer.valueOf(zip.replaceAll("\\s", ""))] != null) {
            return true;
        }
        return false;
    }

    public void collisions(int mod) {
        int mx = 10;
        int[] data = new int[mod];
        int[] cols = new int[mx];
        int[] keys = new int[this.max];

        int j = 0;

        for (int i = 0; i < this.max; i++){
            if (postnr[i] != null) {
                keys[j] = Integer.valueOf(postnr[i].zipcode.replaceAll("\\s", ""));
                j++;
            }
        }

        for (int i = 0; i < this.max; i++) {
            Integer index = keys[i] % mod;
            data[index]++;
        }
        for (int i = 0; i < mod; i++) {
            if (data[i] < mx)
                cols[data[i]]++;
        }
        System.out.print(mod + ": " + " & ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i] + " & ");
        }
        System.out.print("\\\\");
        System.out.println();
    }

}
