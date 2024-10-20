public class Queue <T> extends JQueue <T>{
    Node head;

    private class Node {
        T item;
        Node next;

        private Node(T item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        this.head = null;
    }

    public void enqueue(T item) {
        Node n = new Node(item, null);

        Node nxt = this.head;
        if (nxt == null) {
            this.head = n;
        }else{
            while (nxt.next != null) {
                nxt = nxt.next;
            }
            nxt.next = n;
        }
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
