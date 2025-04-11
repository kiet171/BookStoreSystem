
package bookstore.data_structures.model;

import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.data_structures.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        order = new Order();
        book1 = new Book("Java Programming", "James Gosling", 40.0, 10);
        book2 = new Book("Algorithms", "Robert Sedgewick", 35.5, 5);
        book3 = new Book("Clean Code", "Robert C. Martin", 45.0, 7);
    }

    @Test
    public void testAddBooksAndGetBooks() {
        order.addBook(book1);
        order.addBook(book2);

        MyArrayList<Book> books = order.getBooks();
        assertEquals(2, books.size());
        assertEquals("Java Programming", books.get(0).getTitle());
        assertEquals("Algorithms", books.get(1).getTitle());
    }

    @Test
    public void testOrderObjectIsCreated() {
        assertNotNull(order);
        assertEquals(0, order.getBooks().size());
    }
}
