package bookstore.data_structures.order_management;

import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.order_management.OrderQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderQueueTest {

    private OrderQueue queue;
    private Order order1, order2, order3;

    @BeforeEach
    public void setUp() {
        queue = new OrderQueue(2);

        order1 = new Order();
        order1.addBook(new Book("Book A", "Author A", 10.0, 1));

        order2 = new Order();
        order2.addBook(new Book("Book B", "Author B", 15.0, 2));

        order3 = new Order();
        order3.addBook(new Book("Book C", "Author C", 20.0, 3));
    }

    @Test
    public void testEnqueueAndDequeue() {
        assertTrue(queue.isEmpty());

        queue.enqueue(order1);
        queue.enqueue(order2);

        assertTrue(queue.isFull());

        // Add too much quantity
        queue.enqueue(order3); // will not be added

        Order first = queue.dequeue();
        assertEquals(order1, first);

        Order second = queue.dequeue();
        assertEquals(order2, second);

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueFromEmptyQueueReturnsNull() {
        assertNull(queue.dequeue());
    }

    @Test
    public void testEnqueueCircularBehavior() {
        queue.enqueue(order1);
        queue.enqueue(order2);
        queue.dequeue(); // withdraw order1
        queue.enqueue(order3); // add order3 to the exact place drawn (circle)

        assertEquals(order2, queue.dequeue());
        assertEquals(order3, queue.dequeue());
    }
}
