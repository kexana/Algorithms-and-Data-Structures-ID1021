public class DynamicStack<T>{
    T[] stack;
    int top = -1;
    int size = 1;
    private int stackStep = 4;
    private int popfrequency = 0;
    public DynamicStack() {
        stack = (T[]) new Object[size];
    }

    public void push(T val) {
        popfrequency = 0;
        top+=1;
        if (top == size) {
            T[] newStack = (T[]) new Object[size + stackStep];
            for (int i = 0; i < size; i++) {
                newStack[i] = stack[i];
            }
            size += stackStep;
            stack = newStack;
        }
        stack[top] = val;
    }

    public T pop() {
        popfrequency++;
        if (popfrequency > stackStep) {
            size -= stackStep;
            T[] newStack = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
            popfrequency = 0;   
        }
        T val = null;
        if (top >= 0) {
            val = stack[top];
            stack[top] = null;
            top -= 1;
        }
        return val;
    }
}
