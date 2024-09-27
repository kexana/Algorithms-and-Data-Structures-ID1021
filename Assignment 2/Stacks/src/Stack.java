public abstract class Stack<T> {
    T[] stack;

    int size;
    int top;

    public abstract void push(T value);

    public abstract T pop();
    
    }
    