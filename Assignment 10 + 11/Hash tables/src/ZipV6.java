import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV6 {
    Area[] postnr;
    public int max;

    public int mod;

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

    
    public ZipV6(String file) {
       this(file, 10000, 9973);
    }

    public ZipV6(String file, int maxsize, int hashmodulo) {
        this.max = maxsize;
        this.mod = hashmodulo;
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                int hashedindex = HashFunc(Integer.valueOf(row[0].replaceAll("\\s","")));
                if (postnr[hashedindex] != null) {
                    while (postnr[hashedindex] != null) {
                        hashedindex++;
                        if (hashedindex == this.max) {
                            hashedindex = 0;
                        }
                    }
                }
                postnr[hashedindex] = new Area(row[0], row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Area Lookup(String zip) {
        int hashedZip = HashFunc(Integer.valueOf(zip.replaceAll("\\s", "")));
        while (postnr[hashedZip] != null) {
            if (!postnr[hashedZip].zipcode.equals(zip)) {
                break;
            }
            hashedZip++;
            if (hashedZip == this.max - 1) {
                hashedZip = 0;
            }
        }
        return postnr[hashedZip];
    }
    
    public Integer LookupCount(String zip) {
        int hashedZip = HashFunc(Integer.valueOf(zip.replaceAll("\\s", "")));
        int counter = 0;
        while (postnr[hashedZip] != null) {
            if (!postnr[hashedZip].zipcode.equals(zip)) {
                break;
            }
            hashedZip++;
            if (hashedZip == this.max - 1) {
                hashedZip = 0;
            }
            counter++;
        }
        return counter;
    }

    private int HashFunc(int num) {
        return num % mod;
    }

}
