package bookstore.data_structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTest {

    @Test
    public void testEnqueueAndSize() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testDequeue() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        String first = queue.dequeue();
        assertEquals("A", first);
        assertEquals(1, queue.size());

        String second = queue.dequeue();
        assertEquals("B", second);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeek() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("First");
        queue.enqueue("Second");

        assertEquals("First", queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    public void testContains() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("apple");
        queue.enqueue("banana");

        assertTrue(queue.contains("apple"));
        assertFalse(queue.contains("orange"));
    }

    @Test
    public void testDequeueOnEmptyQueueThrowsException() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        Exception exception = assertThrows(RuntimeException.class, queue::dequeue);
        assertEquals("Queue is empty!", exception.getMessage());
    }

    @Test
    public void testPeekOnEmptyQueueThrowsException() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        Exception exception = assertThrows(RuntimeException.class, queue::peek);
        assertEquals("Queue is empty!", exception.getMessage());
    }
}
