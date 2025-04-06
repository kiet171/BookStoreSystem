package bookstore.data_structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    public void testAddAndSize() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGet() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testGetWithInvalidIndexThrowsException() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("test");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testRemoveFirst() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("first");
        list.add("second");

        list.removeFirst();
        assertEquals(1, list.size());
        assertEquals("second", list.get(0));
    }

    @Test
    public void testRemoveFirstOnEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        // Should not throw exception
        list.removeFirst();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());

        list.add("data");
        assertFalse(list.isEmpty());
    }
}
