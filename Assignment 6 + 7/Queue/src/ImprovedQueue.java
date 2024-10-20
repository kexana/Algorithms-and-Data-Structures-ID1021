public class ImprovedQueue<T> extends JQueue <T>{
    Node head;
    Node tail;

    private class Node {
        T item;
        Node next;

        private Node(T item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public ImprovedQueue() {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(T item) {
        Node n = new Node(item, null);

        if (this.tail != null) {
            this.tail.next = n;
        } else {
            this.head = n;
        }
        this.tail = n;
    }

    public T dequeue() {
        if(this.head!=null){
            Node temp = this.head;
            this.head = this.head.next;
            temp.next = null;
            return temp.item;
        } else {
            return null;
        }
    }

    public String ToString() {
        Node nxt = this.head;
        StringBuilder str = new StringBuilder();
        while (nxt != null) {
            str.append(nxt.item).append(' ');
            nxt = nxt.next;
        }
        return str.toString();
    }
}
