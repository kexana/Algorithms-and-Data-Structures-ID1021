public class BinaryTree<T extends Comparable<T>> {

    private class Node {

        private T value;
        private Node left, right;

        public Node(T value) {
            this.value = value;
            this.left = this.right = null;
        }

        public void printNode() {

            if (left != null)
                left.printNode();

            System.out.println(value);

            if (right != null)
                right.printNode();
        }
            
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
        }else{
            addRec(value, root);
        }
    }
    
    private void addRec(T value, Node itr) {
        if (itr.value != value) {
            if (itr.value.compareTo(value) > 0) {
                if (itr.left != null) {
                    addRec(value, itr.left);
                    return;
                } else {
                    itr.left = new Node(value);
                    return;
                }
            } else {
                if (itr.right != null) {
                    addRec(value, itr.right);
                    return;
                } else {
                    itr.right = new Node(value);
                    return;
                }
            }
        }
    }
    public void addNonRec(T value) {
        if (root == null) {
            root = new Node(value);
        } else {
            Node itr = root;
            Node prev = null;
            boolean isLess = false;
            while (itr != null) {
                prev = itr;
                if (value.compareTo(itr.value) < 0) {
                    itr = itr.left;
                    isLess = true;
                } else {
                    itr = itr.right;
                    isLess = false;
                }
            }
            if (isLess) {
                prev.left = new Node(value);
            } else {
                prev.right = new Node(value);
            }
        }
    }
    
    public boolean lookup(T key) {
        return lookupRec(key, root);
    }

    private boolean lookupRec(T key, Node itr) {
        if (itr.value != key) {
            if (itr.value.compareTo(key) > 0) {
                if (itr.left != null) {
                    return lookupRec(key, itr.left);
                } else {
                    return false;
                }
            } else {
                if (itr.right != null) {
                    return lookupRec(key, itr.right);
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        toStringRec(str, root);

        return str.toString();

    }
    
    private void toStringRec(StringBuilder str, Node itr) {
        if (itr != null) {
            toStringRec(str, itr.left);
            str.append(itr.value).append(" ");
            str.append(System.getProperty("line.separator"));
            toStringRec(str, itr.right);
        }
    }
    
    public void printTree() {
        if (root != null) {
            root.printNode();
        }
    }
    
    public void printDFS() {
        DynamicStack<Node> stk = new DynamicStack<Node>();
        Node itr = this.root;

        while (itr.left != null && itr != null) {
            stk.push(itr);
            itr = itr.left;
        }

        while (itr != null) {
            System.out.println(itr.value);
            if (itr.right != null) {
                itr = itr.right;
                stk.push(itr);
                while (itr.left != null) {
                    itr = itr.left;
                    stk.push(itr);
                }
            }
            itr = stk.pop();
        }
    }

    public void printBFS() {
        ArrayQueue<Node> que = new ArrayQueue<Node>();
        Node itr = this.root;

        que.enqueue(itr);

        while (!que.isEmpty()) {
            itr = que.dequeue();
            System.out.println(itr.value);
            if (itr.left != null) {
                que.enqueue(itr.left);
            }
            if (itr.right != null) {
                que.enqueue(itr.right);
            }
        }
    }

    public Sequence sequence() {
        Sequence res = new Sequence<T>(this.root);
        while (!res.internalEmpty()) {
            System.out.println(res.next());
        }
        return res;
    }
    
    public class Sequence<T> {
        private ArrayQueue<BinaryTree.Node> que;
        
        public Sequence(BinaryTree.Node root) {
            this.que = new ArrayQueue<BinaryTree.Node>();
            this.que.enqueue(root);
        }
    
        public T next() {
            BinaryTree.Node itr;
            if (!this.que.isEmpty()) {
                itr = que.dequeue();
                if (itr.left != null) {
                    que.enqueue(itr.left);
                }
                if (itr.right != null) {
                    que.enqueue(itr.right);
                }
                return (T) itr.value;
            } else {
                return null;
            }
        }
        
        public boolean internalEmpty() {
            return que.isEmpty();
        }
    }

}
