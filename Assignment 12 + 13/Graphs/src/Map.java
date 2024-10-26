import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Map {
    private City[] cities;
    private ArrayList<City>[] buckets;
    private final int mod = 541;

    public class City {
        String name;
        ArrayList<Connection> conections;

        public City(String name) {
            this.name = name;
            this.conections = new ArrayList<Connection>();
        }
        
        public void connect(City nxt, int dst) {
            this.conections.add(new Connection(nxt, dst));
        }
    }
    
    public class Connection {
        City destination;
        int timeToReach;

        public Connection(City destination, int timeToReach) {
            this.destination = destination;
            this.timeToReach = timeToReach;
        }
    }


    public Map(String file) {
        this.cities = new City[mod];
        this.buckets = (ArrayList<City>[]) new ArrayList[mod];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                City one = lookup(row[0]);
                City two = lookup(row[1]);
                int dist = Integer.valueOf(row[2]);

                one.connect(two, dist);
                two.connect(one, dist);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public City lookup(String name) {
        int hashedIndex = hash(name, this.mod);
        if (cities[hashedIndex] != null) {
            if (cities[hashedIndex].name.equals(name)) {
                return cities[hashedIndex];
            }else if (buckets[hashedIndex] == null){
                buckets[hashedIndex] = new ArrayList<City>();
                City res = new City(name);
                buckets[hashedIndex].add(res);
                return res;
            }else if(!buckets[hashedIndex].stream().anyMatch(c -> c.name == name)) {
                City res = new City(name);
                buckets[hashedIndex].add(res);
                return res;
            } else {
                return buckets[hashedIndex].stream().filter(c -> c.name == name).findFirst().orElse(null);
            }
        } else {
            cities[hashedIndex] = new City(name);
        }

        return cities[hashedIndex];
    }

    private static Integer hash(String name, int mod) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 + name.charAt(i)) % mod;
        }
        return hash;
    }

}
