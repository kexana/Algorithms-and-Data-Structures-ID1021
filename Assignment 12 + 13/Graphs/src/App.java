import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {

    public static ArrayList<String> getAllCitiesExcept(String name, String file) {
        ArrayList<String> res = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (!res.contains(row[0]) && !row[0].equals(name)) {
                    res.add(row[0]);
                }
                if (!res.contains(row[1]) && !row[1].equals(name)) {
                    res.add(row[1]);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public static class Result {
        String destination;
        long executionTime;
        Integer distance;
        public Result(String destination, long executionTime, Integer distance) {
            this.destination = destination;
            this.executionTime = executionTime;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws Exception {
        Map map = new Map("trains.csv");
        String from = "Malmö";
        ArrayList<String> to = getAllCitiesExcept(from, "trains.csv");
        
        ArrayList<Result> results = new ArrayList<Result>();

        int testIters = 10;

        EvenBetter.Paths pth = new EvenBetter.Paths();
        for (String c : to) {
            Integer dist = null;
            Long timeSum = 0l;
            for(int k = 0; k < testIters; k++){
                long t0 = System.nanoTime();
                dist = pth.shortest(map.lookup(from), map.lookup(c), null);
                long time = (System.nanoTime() - t0) / 1_000_000;
                timeSum += time;
            }

            if (dist != null) {
                results.add(new Result(c, timeSum/testIters, dist));
            }

            
            //System.out.println(c + " " + dist + " " + time);
        }
        results.sort((r1, r2) -> r1.distance.compareTo(r2.distance));

        for (Result r : results) {
            System.out.print("(" + r.distance + ", " + r.executionTime + ")");
        }
    }
}
