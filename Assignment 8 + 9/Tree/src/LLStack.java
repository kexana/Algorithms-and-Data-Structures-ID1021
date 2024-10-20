class LLStack<T> {
    public Cell first;

    private class Cell {
        T head;
        Cell tail;

        Cell(T val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    public LLStack() {
        first = null;
    }        

    private void add(T item) {
        Cell newCell = new Cell(item, this.first);
        this.first = newCell;
    }

    public void push(T item) {
        add(item);
    }

    public T pop() {
        Cell temp = first;
        remove(first.head);
        if (temp == null) {
            return null;
        }else{
            return temp.head;
        }
    }

    public T peek() {
        return first.head;
    }

    public int length() {
        int i = 0;
        Cell itr = this.first;
        while (itr != null) {
            i++;
            itr = itr.tail;

        }
        return i;
    }
    
    public boolean find(T item) {
        Cell itr = this.first;
        while (itr != null) {
            if (itr.head == item) {
                return true;
            }
            itr = itr.tail;
        }
        return false;
    }

    private void remove(T item) {
        Cell itr = this.first;
        Cell prev = null;
        while (itr.tail != null) {
            if (itr.head == item) {
                if (prev != null) {
                    prev.tail = itr.tail;
                    itr.head = null;
                } else {
                    this.first = itr.tail;
                }
            }
            prev = itr;
            itr = itr.tail;
        }
    }
    
    public String ToString() {
        Cell nxt = this.first;
        StringBuilder str = new StringBuilder();
        while (nxt != null) {
            str.append(nxt.head.toString()).append(' ');
            nxt = nxt.tail;
        }
        return str.toString();
    }
        
}
