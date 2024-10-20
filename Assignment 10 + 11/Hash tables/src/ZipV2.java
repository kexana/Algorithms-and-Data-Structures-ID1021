import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV2 {
    Area[] postnr;
    int max = 10000;

    public class Area {
    
        private Integer zipcode;
        private String name;
        private Integer population;

        public Area(Integer zipcode, String name, Integer population) {
            this.zipcode = zipcode;
            this.name = name;
            this.population = population;
        }
    }

    public ZipV2(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {

                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                postnr[i++] = new Area(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean BadLookup(String zip) {
        for (Area area : postnr) {
            if (area != null) {
                if (area.zipcode.equals(Integer.valueOf(zip.replaceAll("\\s","")))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean BinaryLookup(String zip) {
        int first = 0;
        int last = postnr.length - 1;
        int key = Integer.valueOf(zip.replaceAll("\\s",""));

        while (true) {
            int index = (first + last) / 2;

            if (postnr[index] != null) {

                if (postnr[index].zipcode == key) {
                    return true;
                }

                if (postnr[index].zipcode < key && index < last) {
                    first = index + 1;
                    continue;
                }

                if (postnr[index].zipcode > key && index > first) {
                    last = index;
                    continue;
                }
            }

            return false;
        }
    }
}
