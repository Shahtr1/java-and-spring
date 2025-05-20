package stack;

import java.util.EmptyStackException;

public class Stack<T> {

    private int size = 10;

    private T[] array = (T[]) new Object[size];

    private int top = -1;

    public Stack() {
    }

    public Stack(int size) {
        this.size = size;
        array = (T[]) new Object[size];
    }

    public int getSize() {
        return this.size;
    }

    public boolean isFull() {
        return top == getSize() - 1;
    }

    public void push(Object item) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        array[++top] = (T) item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int length() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

}
