import java.io.BufferedReader;
import java.io.FileReader;

public class ZipV5 {
    Area[] postnr;
    Bucket[] buckets;
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

    public class Bucket {
        Node head;
        int size;

        public Bucket() {
            this.head = null;
            this.size = 0;
        }

        private class Node{
            Area val;
            Node next;

            public Node(Area val) {
                this.val = val;
                this.next = null;
            }

        }

        public void add(Area element) {
            Node itr = head;
            size++;
            while (itr!= null){
                itr = itr.next;
            }
            itr = new Node(element);
        }
        
        public Area remove(String code) {
            Node itr = head;
            Node prv = null;
            while (itr != null) {
                if (itr.val.zipcode == code) {
                    if (prv != null) {
                        prv.next = itr.next;
                    } else {
                        this.head = itr.next;
                    }
                    Node temp = itr;
                    itr = null;
                    return temp.val;
                }
                prv = itr;
                itr = itr.next;
            }
            return null;
        }

        public Area find(String code) {
            Node itr = head;
            while (itr != null) {
                if (itr.val.zipcode == code) {
                    return itr.val;
                }
                itr = itr.next;
            }
            return null;
        }
        
    }

    public ZipV5(String file) {
        this.postnr = new Area[this.max];
        this.buckets = new Bucket[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                int hashedindex = HashFunc(Integer.valueOf(row[0].replaceAll("\\s","")));
                if(postnr[hashedindex] == null){
                    postnr[hashedindex] = new Area(row[0], row[1], Integer.valueOf(row[2]));
                } else {
                    if (buckets[hashedindex] == null) {
                        buckets[hashedindex] = new Bucket();
                    }
                    buckets[hashedindex].add(new Area(row[0], row[1], Integer.valueOf(row[2])));
                }
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Area Lookup(String zip) {
        int hashedZip = HashFunc(Integer.valueOf(zip.replaceAll("\\s", "")));
        if (postnr[hashedZip] != null) {
            if(postnr[hashedZip].zipcode.equals(zip)){
                return postnr[hashedZip];
            } else if(buckets[hashedZip] != null) {
                return buckets[hashedZip].find(zip);
            }
        }
        return null;
    }

    private int HashFunc(int num) {
        return num % 9973;
    }

}
