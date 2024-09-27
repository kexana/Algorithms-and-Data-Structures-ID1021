public class StaticStack<T> extends Stack<T>{
    T[] stack;
    int top;

    public StaticStack(int size) {
        stack = (T[]) new Object[size];
        top = -1;
    }

    public void push(T val) {
        if (top < stack.length-1) {
            top += 1;
            stack[top] = val;
        }
    }

    public T pop() {
        T val = null;
        if (top >= 0) {
            val = stack[top];
            stack[top] = null;
            top -= 1;
        }
        return val;
    }
}