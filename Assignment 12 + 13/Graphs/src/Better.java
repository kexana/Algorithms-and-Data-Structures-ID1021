public class Better {

    public static class Paths {
        Map.City[] path;
        int sp;

        public Paths() {
            path = new Map.City[54];
            sp = 0;
        }

        private Integer shortest(Map.City from, Map.City to) {
            if (from.equals(to)) {
                return 0;
            }
            
            Integer shrt = null;

            for (int i = 0; i < sp; i++) {
                if (path[i] == from)
                return null;
            }
            path[sp++] = from;
            for (Map.Connection c : from.conections) {
                if (c != null) {
                    Integer currDist = shortest(c.destination, to);
                    if (currDist != null) {
                        Integer distSum = c.timeToReach + currDist;
                        if (shrt == null || distSum < shrt) {
                            shrt = distSum;
                        }
                    }
                }
            }
            path[sp--] = null;
            return shrt;
        }
    }

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
        String[] from = { "Malmö","Göteborg", "Malmö", "Stockholm", "Stockholm","Göteborg", "Sundsvall","Umeå","Göteborg", "Malmö"};
        String []to = {"Göteborg", "Stockholm", "Stockholm", "Sundsvall","Umeå", "Sundsvall","Umeå","Göteborg","Umeå", "Kiruna"};

        Paths pth = new Paths();
        for (int i = 0; i < from.length; i++) {
            
            long t0 = System.nanoTime();
            Integer dist = pth.shortest(map.lookup(from[i]), map.lookup(to[i]));
            long time = (System.nanoTime() - t0) / 1_000_000;
            
            //System.out.println("shortest: " + dist + " min (" + time + " ms)");
            System.out.println(from[i] + " to "+ to[i] + "  &  "+ dist +" &   "+ time +" \\\\");
        }
    }
}