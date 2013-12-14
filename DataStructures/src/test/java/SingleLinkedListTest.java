import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import java.util.*;

public class SingleLinkedListTest {
    SingleLinkedList<Integer> list;

    @Before
    public void init() {
        list = new SingleLinkedList<Integer>();
    }

    @Test
    public void testSizeNewList() {
        assertTrue(list.getSize() == 0);
    }

    @Test
    public void testSizeAddOne() {
        list.addFirst(5);
        assertTrue(list.getSize() == 1);
    }

    @Test
    public void testSizeAddTwoDifferent() {
        list.addFirst(5);
        list.addLast(10);
        assertTrue(list.getSize() == 2);
    }

    @Test
    public void testSizeAddTwoEqual() {
        list.addFirst(5);
        list.addFirst(5);
        assertTrue(list.getSize() == 2);
    }

    @Test
    public void testSizeAddTwoRemoveOne() {
        list.addFirst(5);
        list.addLast(10);
        list.removeFirst();
        assertTrue(list.getSize() == 1);
    }

    @Test
    public void testAddOneSizeIncrease() {
        list.addFirst(5);
        list.addLast(10);
        int sizeBefore = list.getSize();

        list.addFirst(3);
        int sizeAfter = list.getSize();

        assertTrue(sizeAfter == sizeBefore + 1);
    }

    @Test
    public void testRemoveSizeDecrease() {
        list.addFirst(3);
        list.addFirst(10);
        int sizeBefore = list.getSize();

        list.removeFirst();
        int sizeAfter = list.getSize();

        assertTrue(sizeAfter == sizeBefore - 1);
    }

    @Test
    public void testAddFirstOneAddGetAtIndex0() {
        list.addFirst(5);
        assertTrue(list.get(0) == 5);
    }

    @Test
    public void testAddFirstMultipleAddsGetAtLastIndex() {
        list.addLast(5);
        list.addLast(10);
        list.addFirst(3);
        assertTrue(list.get(0) == 3);
    }

    @Test
    public void testAddLastOneAddGetAtLastIndex() {
        list.addLast(5);
        assertTrue(list.get(list.getSize() - 1) == 5);
    }

    @Test
    public void testAddLastMultipleAddsGetAtLastIndex() {
        list.addFirst(3);
        list.addFirst(12);
        list.addLast(20);
        assertTrue(list.get(list.getSize() - 1) == 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexIndexLessZero() {
        list.add(-1, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexIndexGreaterSize() {
        list.add(list.getSize() + 1, 90);
    }

    @Test
    public void testAddAtIndexGetAtIndex() {
        list.addFirst(5);
        list.addLast(2);
        list.addFirst(9);
        list.add(1, 12);
        assertTrue(list.get(1) == 12);
    }

    @Test
    public void testRemoveFirstFromEmptyListSize() {
        list.removeFirst();
        assertTrue(list.getSize() == 0);
    }

    @Test
    public void testRemoveFirstFromListWithDifferentValues() {
        list.addFirst(3);
        list.addFirst(5);
        list.addFirst(10);
        int temp = list.get(0);
        list.removeFirst();
        assertTrue(list.get(0) != temp);
    }

    @Test
    public void testRemoveLastFromListWithDifferentValues() {
        list.addFirst(3);
        list.addFirst(5);
        list.addFirst(12);

        int temp = list.get(list.getSize() - 1);

        list.removeLast();
        assertTrue(list.get(list.getSize() - 1) != temp);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexIndexLessZero() {
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexIndexEqualsSize() {
        list.addFirst(3);
        list.remove(list.getSize());
    }

    @Test
    public void testRemoveAtIndexFromListWithDifferentValues() {
        list.addFirst(3);
        list.addFirst(5);
        list.addFirst(12);

        int temp = list.get(1);

        list.remove(1);
        assertTrue(list.get(1) != temp);
    }

    @Test
    public void testRemoveAllSize() {
        list.addFirst(3);
        list.addLast(10);
        list.removeAll();
        assertTrue(list.getSize() == 0);
    }

    @Test
    public void testContainsAfterRemoveAll() {
        list.addFirst(3);
        list.addLast(34);
        list.removeAll();
        assertFalse(list.contains(34));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetAtEmptyList() {
        list.getFirst();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtIndexLessZero() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtIndexGreaterSize() {
        list.get(list.getSize());
    }

    @Test
    public void testAddOneContainsThat() {
        list.addFirst(5);
        assertTrue(list.contains(5));
    }

    @Test
    public void testContainsEmptyList() {
        assertFalse(list.contains(10));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorIndexLessZero() {
        Iterator<Integer> it = list.listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorIndexGreaterSize() {
        Iterator<Integer> it = list.listIterator(list.getSize() + 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Iterator<Integer> it = list.listIterator(0);
        it.remove();
    }

    @Test
    public void testIteratorHasNextEmptyListStartFrom0Index() {
        Iterator<Integer> it = list.listIterator(0);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextEmptyList() {
        Iterator<Integer> it = list.listIterator(0);
        it.next();
    }

    @Test
    public void testIteratorHasNextNotEmptyListStartFrom0Index() {
        list.addFirst(3);
        Iterator<Integer> it = list.listIterator(0);
        assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorHasNextIndexEqualsSize() {
        list.addFirst(1);
        Iterator<Integer> it = list.listIterator(list.getSize());
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorNextNotEmptyListIndex0() {
        list.addFirst(3);
        Iterator<Integer> it = list.listIterator(0);
        assertTrue(it.next() == 3);
    }

    @Test
    public void testIteratorNextNotEmptyListIndexGreater0LessSize() {
        list.addLast(3);
        list.addLast(5);
        Iterator<Integer> it = list.listIterator(1);
        assertTrue(it.next() == 5);
    }
}