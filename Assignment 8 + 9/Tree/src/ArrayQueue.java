import java.lang.reflect.Array;

public class ArrayQueue<T> {

    private T[] queue;
    private int first; //index of first element in queue
    private int last; //index of last element in queue
    private int size; //number of elements in queue

    public ArrayQueue() {
        this.queue = (T[]) new Object[4];
        first = -1;
        last = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void enqueue(T item) {
        if (size == 0) {
            first = 0;
            last = 0;
        }
        if(last == first && size!=0){
            int n = queue.length*2;
            int tempfirst = first;
            T[] tempQueue = (T[]) new Object[n];
            for (int i = 0; i < size; i++) {
                tempQueue[i] = queue[tempfirst];
                tempfirst = (tempfirst + 1) % queue.length;
            }
            queue = tempQueue;
            first = 0;
            last = size;
        }
        queue[last] = item;
        last = (last + 1) % queue.length;
        size++;

    }
    
    public T dequeue() {
        if (first == -1) {
            return null;
        }
        size--;
        T temp = queue[first];
        queue[first] = null;
        first = (first + 1) % queue.length;
        if (size == 0) {
            first = -1;
            last = -1;
        }
        return temp;
    }
    
    public String ToString() {
        StringBuilder str = new StringBuilder();
        int tempfirst = first;
        for (int i = 0; i < size; i++) {
            str.append(queue[tempfirst]).append(' ');
            tempfirst = (tempfirst + 1) % queue.length;
        }
        return str.append("array size: ").append(queue.length).toString();
    }

}
