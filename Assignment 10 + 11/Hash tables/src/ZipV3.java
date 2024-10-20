import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV3 {
    Area[] postnr;
    int max = 100000;

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

    public ZipV3(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                postnr[Integer.valueOf(row[0].replaceAll("\\s",""))] = new Area(row[0], row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean Lookup(String zip) {
        if (postnr[Integer.valueOf(zip.replaceAll("\\s",""))] != null) {
            return true;
        }
        return false;
    }
}
