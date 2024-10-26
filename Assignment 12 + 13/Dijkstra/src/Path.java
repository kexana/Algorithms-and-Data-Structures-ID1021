public class Path implements Comparable<Path>{

    private Map.City city;
    private Map.City prev;
    private Integer dist;

    private Integer index;

    public Path(Map.City currCity, Map.City prevCity, Integer dist) {
        this.index = null;
        this.city = currCity;
        this.prev = prevCity;
        this.dist = dist;
    }

    public Map.City getCity() {
        return this.city;
    }

    public Integer getIndex() {
        return this.index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public void updatePath(Map.City prevCity, Integer dist) {
        this.prev = prevCity;
        this.dist = dist;
    }

    public Integer getDistance() {
        return this.dist;
    }
    
    @Override
    public int compareTo(Path s) {
        return Integer.compare(this.dist, s.dist);
    }
}