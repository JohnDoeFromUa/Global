import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

public class BinaryTreeTest {
    BinaryTree<Integer, Integer> tree;

    @Before
    public void init() {
        tree = new BinaryTree<Integer, Integer>();
    }

    @Test
    public void testSizeEmptyTree() {
        assertTrue(tree.getSize() == 0);
    }

    @Test
    public void testSizeAddOne() {
        tree.insert(10, 10);
        assertTrue(tree.getSize() == 1);
    }

    @Test
    public void testSizeAddMultipleDifferentKeys() {
        tree.insert(10, 10);
        tree.insert(20, 10);
        tree.insert(15, 9);
        assertTrue(tree.getSize() == 3);
    }

    @Test
    public void testSizeAddMultipleEqualsKeys() {
        tree.insert(10, 20);
        tree.insert(10, 30);
        assertTrue(tree.getSize() == 1);
    }

    @Test
    public void testSizeIncreaseDifferentKeys() {
        tree.insert(10, 5);
        int sizeBefore = tree.getSize();

        tree.insert(20, 3);
        int sizeAfter = tree.getSize();

        assertTrue(sizeAfter == sizeBefore + 1);
    }

    @Test
    public void testSizeIncreaseSameKeys() {
        tree.insert(10, 5);
        int sizeBefore = tree.getSize();

        tree.insert(10, 3);
        int sizeAfter = tree.getSize();

        assertTrue(sizeAfter == sizeBefore);
    }

    @Test
    public void testSizeDecreaseRemoveKey() {
        tree.insert(10, 5);
        tree.insert(20, 3);
        int sizeBefore = tree.getSize();

        tree.remove(10);
        int sizeAfter = tree.getSize();

        assertTrue(sizeAfter == sizeBefore - 1);
    }

    @Test
    public void testSizeNotChangedRemoveNotKey() {
        tree.insert(10, 5);
        tree.insert(20, 3);
        int sizeBefore = tree.getSize();

        tree.remove(15);
        int sizeAfter = tree.getSize();

        assertTrue(sizeAfter == sizeBefore);
    }

    @Test
    public void testDepthRange() {
        assertTrue(tree.getDepth() >= 0);
    }

    @Test
    public void testDepthEmptyTree() {
        assertTrue(tree.getDepth() == 0);
    }

    @Test
    public void testDepthAddOne() {
        tree.insert(10, 10);
        assertTrue(tree.getDepth() == 0);
    }

    @Test
    public void testDepthAddTwoDifferentKeys() {
        tree.insert(10, 1);
        tree.insert(9, 2);
        assertTrue(tree.getDepth() == 1);
    }

    @Test
    public void testDepthNotChangedAddSameKey() {
        tree.insert(25, 13);
        tree.insert(92, 3);
        int depthBefore = tree.getDepth();

        tree.insert(25, 69);
        int depthAfter = tree.getDepth();

        assertTrue(depthAfter == depthBefore);
    }

    @Test
    public void testContainsEmptyTree() {
        assertFalse(tree.contains(10));
    }

    @Test
    public void testContainsCheckKeyThatWasAdded() {
        tree.insert(10, 5);
        assertTrue(tree.contains(10));
    }

    @Test
    public void testContainsCheckKeyThatWasNotAdded() {
        tree.insert(25, 15);
        assertFalse(tree.contains(1));
    }

    @Test
    public void testFindInEmptyTree() {
        assertTrue(tree.find(20) == null);
    }

    @Test
    public void testFindKeyNotMatches() {
        tree.insert(23, 1);
        tree.insert(21, 12);
        assertTrue(tree.find(22) == null);
    }

    @Test
    public void testFindKeyInRoot() {
        tree.insert(25, 33);
        assertTrue(tree.find(25) == tree.getRoot());
    }

    @Test
    public void testFindKeyNotInRoot() {
        tree.insert(1, 1);
        assertFalse(tree.find(2) == tree.getRoot());
    }

    @Test
    public void testRemoveAllSize() {
        tree.insert(25, 14);
        tree.insert(12, 34);
        tree.insert(45, 53);
        tree.removeAll();
        assertTrue(tree.getSize() == 0);
    }

    @Test
    public void testRemoveAllDepth() {
        tree.insert(25, 14);
        tree.insert(12, 34);
        tree.insert(45, 53);
        tree.removeAll();
        assertTrue(tree.getDepth() == 0);
    }

    @Test
    public void testRemoveAllRootIsNull() {
        tree.insert(25, 14);
        tree.insert(12, 34);
        tree.insert(45, 53);
        tree.removeAll();
        assertTrue(tree.getRoot() == null);
    }

    @Test
    public void testRemoveNotContainsWithThatKey() {
        tree.remove(2);
        assertFalse(tree.contains(2));
    }

    @Test
    public void testInsertContainsWithThatKey() {
        tree.insert(25, 99);
        assertTrue(tree.contains(25));
    }
}