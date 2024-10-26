public class Naive {

    // public static Integer shortest(Map.City from, Map.City to, int max) {

    //     return shortestH(from, to, max,0);
    // }

    // private static Integer shortestH(Map.City from, Map.City to, int max,int dist){
    //     if (from.equals(to)) {
    //         return dist;
    //     } else if (max < 0) {
    //         return -1;
    //     } else {
    //         for (Map.Connection c : from.conections) {
    //             System.out.println(c.destination.name);
    //             return shortestH(c.destination, to, max - c.timeToReach, dist + c.timeToReach);
    //         }
    //     }
    //     return -1;
    // }

    private static Integer shortest(Map.City from, Map.City to, Integer max) {

        if (max < 0){
            return null;
        }
        if (from.equals(to)){
            return 0;
        }
        Integer shrt = null;

        for (Map.Connection c : from.conections) {
            if (c != null) {
                Integer currDist = shortest(c.destination, to, max - c.timeToReach);
                if (currDist != null) {
                    Integer distSum = c.timeToReach + currDist;
                    if (shrt == null || distSum < shrt) {
                        shrt = distSum;
                    }
                }
            }
        }
        return shrt;
    }

public static void main(String[] args) {
        Map map = new Map("trains.csv");
        String from = "Göteborg";
        String to = "Umeå";
        Integer max = 500;

        //Map.City test = map.lookup(to);

        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        System.out.println(from + " to "+ to + "  &  "+ dist +" &   "+ time +" \\\\");
    }
}