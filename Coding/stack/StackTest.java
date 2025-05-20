package stack;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

public class StackTest {

    Stack<Integer> stack;

    @Test
    public void shouldReturnTheTopElement() {
        stack = new Stack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int top = stack.peek();
        assertEquals(3, top);
        assertEquals(3, stack.length());
    }

    @Test
    public void shouldShowStackFullIfNoSpaceLeft() {
        stack = new Stack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertThrows(StackOverflowError.class, () -> stack.push(6));
        assertEquals(5, stack.getSize());
    }

    @Test
    public void shouldPopTheTopElementAndReturnIt() {
        stack = new Stack<>(100);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.push(11);
        int poppedElement = stack.pop();
        assertEquals(11, poppedElement);
        assertEquals(100, stack.getSize());
        assertEquals(4, stack.length());
        assertEquals(10, stack.peek());
        stack.push(12);
        stack.push(13);
        stack.push(14);
        assertEquals(7, stack.length());
        assertEquals(14, stack.peek());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertThrows(EmptyStackException.class, () -> stack.pop());
        assertEquals(0, stack.length());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.length());
        assertEquals(3, stack.peek());
    }
}
