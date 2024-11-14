package vydrenkova.internship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        assertTrue(list.add(1));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void testAddAtIndex() {
        int capacity = 10;
        list = new MyArrayList<>(capacity);
        list.add(0, 1);
        list.add(1, 3);
        list.add(1, 2);

        assertEquals(capacity + 3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testAddAll() {
        list.addAll(Arrays.asList(1, 2, 3));

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());

        list.add(1);
        assertEquals(1, list.size());

        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);

        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testToString() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void testSort() {
        list.addAll(Arrays.asList(3, 1, 2));
        list.sort();

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testQuickSort() {
        list.addAll(Arrays.asList(3, 1, 2));
        list.quickSort(Comparator.naturalOrder());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }
}
