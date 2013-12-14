import java.util.NoSuchElementException;

public class LinkedQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean offer(E item) {
        if (item == null)
            throw new NullPointerException();

        Node<E> last = tail;
        Node<E> newNode = new Node<E>(last, item, null);
        tail = newNode;
        if (last == null)
            head = newNode;
        else
            last.next = newNode;
        size++;
        return true;
    }

    public E element() {
        if (head == null || head.value == null)
            throw new NoSuchElementException();
        return head.value;
    }

    public E peek() {
        if (head == null)
            return null;
        return head.value;
    }

    public E remove() {
        if (head == null)
            throw new NoSuchElementException();
        E result = head.value;
        Node<E> next = head.next;
        head = next;
        if (next == null)
            tail = null;
        else
            head.prev = null;
        size--;

        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    public E poll() {
        if (head == null)
            return null;
        E result = head.value;
        head = head.next;
        head.prev = null;
        size--;
        return result;
    }


    private class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value) {
            this(null, value, null);
        }

        public Node(Node prev, E value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}