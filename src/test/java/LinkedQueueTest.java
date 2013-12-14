import org.junit.*;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class LinkedQueueTest {
    LinkedQueue<Integer> queue;

    @Before
    public void init() {
        queue = new LinkedQueue<Integer>();
    }

    @Test
    public void testSizeEmptyQueue() {
        assertTrue(queue.getSize() == 0);
    }

    @Test
    public void testSizeOneAdd() {
        queue.offer(5);
        assertTrue(queue.getSize() == 1);
    }

    @Test
    public void testSizeTwoSameAdd() {
        queue.offer(3);
        queue.offer(3);
        assertTrue(queue.getSize() == 2);
    }

    @Test
    public void testSizeTwoDifferentAdd() {
        queue.offer(1);
        queue.offer(2);
        assertTrue(queue.getSize() == 2);
    }

    @Test
    public void testSizeOneAddOneRemove() {
        queue.offer(2);
        queue.remove();
        assertTrue(queue.getSize() == 0);
    }

    @Test
    public void testAddIncreaseSize() {
        queue.offer(3);
        int beforeSize = queue.getSize();
        queue.offer(23);
        int afterSize = queue.getSize();
        assertTrue(beforeSize + 1 == afterSize);
    }

    @Test(expected =  NullPointerException.class)
    public void testAddNull() {
        queue.offer(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testElementEmptyQueue() {
        queue.element();
    }

    @Test
    public void testElementNotEmptyQueue() {
        queue.offer(3);
        queue.offer(5);
        assertTrue(queue.element() == 3);
    }

    @Test
    public void testPeekEmptyQueue() {
        assertEquals(null, queue.peek());
    }

    @Test
    public void testPollEmptyQueue() {
        assertEquals(null, queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmptyQueue() {
        queue.remove();
    }

    @Test
    public void testRemoveOne() {
        queue.offer(3);
        queue.offer(25);
        assertTrue(queue.remove() == 3);
    }

    @Test
    public void testRemoveTwo() {
        queue.offer(1);
        queue.offer(2);
        assertTrue(queue.remove() == 1);
        assertTrue(queue.remove() == 2);
    }

    @Test
    public void testRemoveDecreaseSize() {
        queue.offer(3);
        int beforeSize = queue.getSize();
        queue.remove();
        int afterSize = queue.getSize();
        assertTrue(beforeSize - 1 == afterSize);
    }
}