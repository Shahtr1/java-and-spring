package stack.using_queues;

import java.util.*;

public class PushCostly {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    // Push
    public void push(int x) {
        // Move all elements from queue1 to queue2
        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }

        queue1.add(x);

        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }

    }

    // Pop
    public int pop() {
        if (queue1.isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        }
        return queue1.remove();
    }

    // Peek
    public int peek() {
        if (queue1.isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        }
        return queue1.peek();
    }

    // isEmpty
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        PushCostly stack = new PushCostly();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top element: " + stack.peek()); // Output: 3
        System.out.println("Popped element: " + stack.pop()); // Output: 3
        System.out.println("Top element after pop: " + stack.peek()); // Output: 2
    }
}
