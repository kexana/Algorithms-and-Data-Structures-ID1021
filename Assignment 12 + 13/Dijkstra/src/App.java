import java.util.ArrayList;

public class App {
   
    public static Result shortest(Map.City from, Map.City to) {
        PriorityQueue queue = new PriorityQueue();

        Path[] done = new Path[Map.mod];
        
        queue.enqueue(new Path(from, null, 0));

        while (!queue.isEmpty()) {
            Path fromCurrCity = queue.dequeue();
            Map.City toCity = fromCurrCity.getCity();
            Integer totalDist = fromCurrCity.getDistance();
            if (toCity.equals(to)) {
                int doneEntries = 0;
                for (Path p : done) {
                    if (p != null) {
                        doneEntries++;
                    }
                }
                return new Result(totalDist, doneEntries);
            } else {
                for (Map.Connection c : toCity.conections) {
                    if (done[c.destination.id]==null) {
                        done[c.destination.id] = new Path(c.destination, toCity, c.timeToReach + totalDist);
                        queue.enqueue(done[c.destination.id]);
                    }
                }
            }
        }
        return null;
    }
    
    public static class Result {

        Integer distance;
        Integer doneEntries;
        Long executionTime;

        public Result(Integer distance, Integer doneEntries) {
            this.distance = distance;
            this.doneEntries = doneEntries;
            this.executionTime = null;
        }

        public Result(Integer doneEntries, Long executionTime) {
            this.distance = null;
            this.doneEntries = doneEntries;
            this.executionTime = executionTime;
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        Map map = new Map("europe.csv");
        String from = "Malmö";
        String[] to = { "Geneve", "München", "Bologna", "Zaragoza", "Tammerfors", "Gdansk", "Lille", "Amsterdam",
                "Eskilstuna", "Montpellier", "Świnoujście", "Brindisi" };

        String[] to2 = {"Tammerfors" , 
                        "Åbo" , 
                        "Helsingfors" , 
                        "Östersund" , 
                        "Trondheim" , 
                        "Narvik" , 
                        "Berlin" , 
                        "Hannover" , 
                        "Kassel" , 
                        "Leipzig" , 
                        "Dresden" , 
                        "Bologna" , 
                        "Pescara" , 
                        "Rom" , 
                        "Neapel" , 
                        "Foggia" , 
                        "Bari" , 
                        "Taranto" , 
                        "Genova" , 
                        "Florens" , 
                        "Montpellier" , 
                        "Barcelona" , 
                        "Zaragoza" , 
                        "Madrid",
                "Valencia",
                 "Liege" , 
                                          "Köln" , 
                                          "Frankfurt" , 
                                          "Hannover" , 
                                          "Göttingen" , 
                                          "Nürnberg" , 
                                          "Stuttgart" , 
                                          "München" , 
                                          "Innsbruck" , 
                                          "Graz" , 
                                          "Milano" , 
                                          "Verona" , 
                                          "Venedig"               
                             };

        // JIT warmup
        for (int i = 0; i < 100; i++) {
            Result r = shortest(map.lookup(from), map.lookup(""));
        }

        ArrayList<Result> reses = new ArrayList<Result>();

        for (String d : to2) {
            Result res = shortest(map.lookup(from), map.lookup(d));
            long timeSum = 0;
            int resolution = 50;
            for(int i=0;i<resolution;i++){
                long t0 = System.nanoTime();
                Result r = shortest(map.lookup(from), map.lookup(d));
                long time = (System.nanoTime() - t0) / 1_000;
                timeSum += time;
            }
            reses.add(new Result(res.doneEntries, timeSum / resolution));

        }
        reses.sort((r1, r2) -> r1.doneEntries.compareTo(r2.doneEntries));

        for (Result r : reses) {
            System.out.print("(" + r.doneEntries + ", " + r.executionTime + ")");
        }
        // Map map = new Map("trains.csv");
        // String[] from = { "Malmö","Göteborg", "Malmö", "Stockholm", "Stockholm","Göteborg", "Sundsvall","Umeå","Göteborg", "Malmö"};
        // String []to = {"Göteborg", "Stockholm", "Stockholm", "Sundsvall","Umeå", "Sundsvall","Umeå","Göteborg","Umeå", "Kiruna"};

        // for (int i = 0; i < from.length; i++) {

        //     long t0 = System.nanoTime();
        //     Integer dist = shortest(map.lookup(from[i]), map.lookup(to[i])).distance;
        //     long time = (System.nanoTime() - t0) / 1_000;

        //     //System.out.println("shortest: " + dist + " min (" + time + " ms)");
        //     System.out.println(from[i] + " to " + to[i] + "  &  " + dist + " &   " + time + " \\\\");
        // }
        
    }
}
