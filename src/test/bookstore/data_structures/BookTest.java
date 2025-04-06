package bookstore.data_structures;

import bookstore.model.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testConstructorAndGetters() {
        Book book = new Book("Clean Code", "Robert C. Martin", 39.99, 5);

        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert C. Martin", book.getAuthor());
        assertEquals(39.99, book.getPrice(), 0.001);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        Book book = new Book("Refactoring", "Martin Fowler", 45.00, 3);

        book.setQuantity(10);
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void testToStringFormat() {
        Book book = new Book("Domain-Driven Design", "Eric Evans", 55.5, 2);
        String expected = "Book: Domain-Driven Design | Author: Eric Evans | Price: $55.5 | Quantity: 2";

        assertEquals(expected, book.toString());
    }
}
