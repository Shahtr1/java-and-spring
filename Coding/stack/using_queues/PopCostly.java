package stack.using_queues;

import java.util.*;

public class PopCostly {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    // Push
    public void push(int x) {
        queue1.add(x);
    }

    // Pop
    public int pop() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }

        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }

        int top = queue1.remove();

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return top;
    }

    // Peek
    public int peek() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }

        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }

        int top = queue1.peek();

        queue2.add(queue1.remove());

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return top;
    }

    // isEmpty
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        PopCostly stack = new PopCostly();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top element: " + stack.peek()); // Output: 3
        System.out.println("Popped element: " + stack.pop()); // Output: 3
        System.out.println("Top element after pop: " + stack.peek()); // Output: 2
    }
}
