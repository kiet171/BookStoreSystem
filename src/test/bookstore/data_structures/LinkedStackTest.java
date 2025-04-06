package bookstore.data_structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedStackTest {

    @Test
    public void testPushAndSize() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPop() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("A");
        stack.push("B");

        String top = stack.pop();
        assertEquals("B", top);
        assertEquals(1, stack.size());

        String next = stack.pop();
        assertEquals("A", next);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeek() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("X");
        stack.push("Y");

        assertEquals("Y", stack.peek());
        assertEquals(2, stack.size()); // Make sure peek doesn't change size
    }

    @Test
    public void testContains() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("dog");
        stack.push("cat");

        assertTrue(stack.contains("dog"));
        assertFalse(stack.contains("elephant"));
    }

    @Test
    public void testPopOnEmptyStackThrowsException() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        Exception exception = assertThrows(RuntimeException.class, stack::pop);
        assertEquals("Empty stack!", exception.getMessage());
    }

    @Test
    public void testPeekOnEmptyStackThrowsException() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        Exception exception = assertThrows(RuntimeException.class, stack::peek);
        assertEquals("Empty stack!", exception.getMessage());
    }
}
