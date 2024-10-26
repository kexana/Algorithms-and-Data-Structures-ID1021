import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Map {
    private City[] cities;
    public static final int mod = 541;

    public class City {
        String name;
        Integer id;
        ArrayList<Connection> conections;

        public City(String name, Integer id) {
            this.name = name;
            this.conections = new ArrayList<Connection>();
            this.id = id;
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

    Integer itr = 0;

    public Map(String file) {
        this.cities = new City[mod];


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
        if (getCityByName(name) == null) {
            City res = new City(name, itr);
            cities[itr++] = res;
            return res;
        }
        return getCityByName(name);
    }
    
    public City getCityByName(String name) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i] != null) {
                if (cities[i].name.equals(name)) {
                    return cities[i];
                }
            }
        }
        return null;
    }
}
