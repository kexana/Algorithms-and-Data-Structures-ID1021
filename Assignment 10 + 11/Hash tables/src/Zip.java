import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
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

    public Zip(String file) {
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

    public boolean BadLookup(String zip) {
        for (Area area : postnr) {
            if (area != null) {
                if (area.zipcode.equals(zip)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean BinaryLookup(String zip) {
        int first = 0;
        int last = postnr.length - 1;

        while (true) {
            int index = (first + last) / 2;

            if (postnr[index] != null) {

                if (postnr[index].zipcode == zip) {
                    return true;
                }

                if (postnr[index].zipcode.compareTo(zip) < 0 && index < last) {
                    first = index + 1;
                    continue;
                }

                if (postnr[index].zipcode.compareTo(zip) > 0 && index > first) {
                    last = index;
                    continue;
                }
            }

            return false;
        }
    }
}
