package bookstore.data_structures;

import bookstore.data_structures.MyArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    @Test
    public void testAddAndGet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Book A");
        list.add("Book B");
        assertEquals("Book A", list.get(0));
        assertEquals("Book B", list.get(1));
    }

    @Test
    public void testSize() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }
}
