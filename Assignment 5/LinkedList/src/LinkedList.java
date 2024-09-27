class LinkedList {
    Cell first;

    private class Cell {
        int head;
        Cell tail;

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }        

    void add(int item) {
        Cell newCell = new Cell(item, this.first);
        this.first = newCell;
    }

    void push(int item) {
        add(item);
    }

    Cell pop() {
        Cell temp = first;
        remove(first.head);
        return temp;
    }

    int length() {
        int i = 0;
        Cell itr = this.first;
        while (itr != null) {
            i++;
            itr = itr.tail;

        }
        return i;
    }
    
    boolean find(int item) {
        Cell itr = this.first;
        while (itr != null) {
            if (itr.head == item) {
                return true;
            }
            itr = itr.tail;
        }
        return false;
    }

    void remove(int item) {
        Cell itr = this.first;
        Cell prev = null;
        while (itr.tail != null) {
            if (itr.head == item) {
                if (prev != null) {
                    prev.tail = itr.tail;
                } else {
                    this.first = itr.tail;
                }
            }
            prev = itr;
            itr = itr.tail;
        }
    }

    public void append(LinkedList b) {
        Cell nxt = this.first;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
        b.first = null;
    }
    
    public String ToString() {
        Cell nxt = this.first;
        StringBuilder str = new StringBuilder();
        while (nxt != null) {
            str.append(nxt.head).append(' ');
            nxt = nxt.tail;
        }
        return str.toString();
    }
        
}
