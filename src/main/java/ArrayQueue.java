import java.util.NoSuchElementException;

public class ArrayQueue<E> {
    final static int MIN_CAPACITY = 16;

    private E[] elements;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        elements = (E[]) new Object[MIN_CAPACITY];
    }

    public ArrayQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        if (capacity <= MIN_CAPACITY)
            elements = (E[]) new Object[MIN_CAPACITY];
        else elements = (E[]) new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        if (newCapacity < 0)
            throw new IllegalStateException("Queue is too big");
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, head, newArray, 0, elements.length - head);
        elements = (E[]) newArray;
        head = 0;
        tail = elements.length - head;
    }

    public boolean offer(E item) {
        if (item == null)
            throw new NullPointerException();
        if (tail == elements.length) {
            increaseCapacity();
        }
        elements[tail] = item;
        tail++;
        size++;
        return true;
    }

    public E element() {
        if (size == 0 || elements[head] == null) {
            throw new NoSuchElementException();
        }
        return elements[head];
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return elements[head];
    }

    public E remove() {
        if (size == 0)
            throw new NoSuchElementException();
        E result = elements[head];
        Object[] newArray = new Object[elements.length];
        System.arraycopy(elements, head + 1, newArray, 0, elements.length - head - 1);
        elements = (E[]) newArray;
        tail--;
        size--;

        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    public E poll() {
        if (size == 0)
            return null;
        E result = elements[head];
        Object[] newArray = new Object[elements.length];
        System.arraycopy(elements, head, newArray, 0, elements.length - head);
        elements = (E[]) newArray;
        tail--;
        size--;

        return result;
    }
}