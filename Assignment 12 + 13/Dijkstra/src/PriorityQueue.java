import java.util.ArrayList;

public class PriorityQueue {


    private final ArrayList<Path> data;
    private final int first;
    private int last;

    public PriorityQueue(){
        this.data = new ArrayList<>();
        first = 0;
        last = 0;
    }

    public void enqueue(Path path) {
        data.add(path);
        path.setIndex(last);
        if (data.size() > 1) {
            bubble(last);
        }
        last++;
    }
    
    private void bubble(int pos) {
        if(pos > first) {
            int parentIndex = parentTo(pos);
            if (data.get(parentIndex).compareTo(data.get(pos)) > 0) {
                swapPriority(parentIndex, pos);
                bubble(parentIndex);
            }
        }

    }

    private void swapPriority(int p1, int p2) {
        if (data.size() > 1) {
            Path temp = data.get(p1);
            data.set(p1, data.get(p2));
            data.set(p2, temp);

            data.get(p1).setIndex(p1);
            data.get(p2).setIndex(p2);
        }
    }
    
    public boolean isEmpty() {
        return first == last;
    }

    public int getLength() {
        return last;
    }

    private int parentTo(int i) {
        return ((i - 1) / 2);
    }

    public void clear() {
        data.clear();
        last = first;
    }
    
    public Path dequeue(){
        if (isEmpty()) {
            return null;
        }
            
        Path path = data.get(first);
         swapPriority(first, --last);
         data.remove(last);
         sink(first);


        path.setIndex(null);

        return path;
    }

    private void sink(int i) {
        int firstChild = firstChildTo(i);
        int secondChild = secondChildTo(i);

        if(childrenInRange(firstChild, secondChild) ) {
            int child = priorityChild(firstChild,secondChild);
            if(hasHigherPriority( child, i )){
                swapPriority(child ,i);
                sink(child);
            }
        }
    }
    private boolean childrenInRange(int c1,int c2) {
        return c1 < last || c2 < last;
    }

    private int priorityChild(int c1, int c2){

        if(c2>= last)
            return c1;
        else if (c1 >= last)
            return c2;
        else
            return hasHigherPriority(c1,c2)? c1 : c2;

    }
    private boolean hasHigherPriority(int c1, int c2){
        return data.get(c1).compareTo(data.get(c2)) < 0;
    }

    private int secondChildTo(int i) {
        return ((i*2)+2);
    }

    private int firstChildTo(int i) {
        return ((i*2)+1);
    }



    public Path contains(Map.City connectedCity) {
        for (Path path : data) {
            if (path.getCity().equals(connectedCity))
                return path;
        }
        return null;
    }


    public void updatePath(Path path, Integer newDist, Map.City newPrev) {
        int priority = path.getIndex();
        data.get(priority).updatePath(newPrev,newDist);
        bubble(priority);
    }


}