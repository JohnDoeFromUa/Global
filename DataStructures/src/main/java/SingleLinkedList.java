import java.util.*;

public class SingleLinkedList<T> {
    private Node<T> first;
    private int size;

    public T getFirst() {
        if (first == null) throw new NoSuchElementException();
        return first.value;
    }

    public int getSize() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size " + size);
        }
    }

    public Node<T> node(int index) {
        checkIndex(index);

        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public T get(int index) {
        checkIndex(index);
        if (index == size)
            throw new IndexOutOfBoundsException();

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        T result = node.value;
        return  result;
    }

    public void addFirst(T element) {
        if (first == null) {
            first = new Node(element, null);
        } else {
            first = new Node(element, first);
        }
        size++;
    }

    public void addLast(T element) {
        add(size, element);
    }

    public void add(int index, T element) {
        checkIndex(index);
        if (index == 0) {
            addFirst(element);
        } else {
            Node<T> before = node(index - 1);
            Node<T> after = node(index);
            Node<T> newNode = new Node(element, after);

            before.next = newNode;
            size++;
        }
    }

    public void removeFirst() {
        if (first != null) {
            first = first.next;
            size--;
        }
    }

    public void removeLast() {
        remove(size - 1);
    }

    public void remove(int index) {
        checkIndex(index);
        if (index == size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0 || size == 1) {
            removeFirst();
        } else {
            Node<T> before = node(index - 1);

            if (index == size - 1) {
                before.next = null;
            } else {
                before.next = node(index + 1);
            }
            size--;
        }
    }

    public void removeAll() {
        first = null;
        size = 0;
    }

    public boolean contains(T elem) {
        return indexOf(elem) != -1;
    }

    public int indexOf(T elem) {
        int index = 0;

        if (elem != null) {
            for (Node i = first; i != null; i = i.next) {
                if (elem.equals(i.value)) {
                    return index;
                } else {
                    index++;
                }
            }
        }
        return -1;
    }

    public Iterator<T> listIterator(int index) {
        checkIndex(index);
        return new ListIter(index);
    }


    private class ListIter implements Iterator<T> {
        private int nextIndex;
        private Node<T> next;

        public ListIter(int index) {
            checkIndex(index);
            nextIndex = index;
            next = (index == size) ? null : node(index);
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Node<T> temp = next;
                next = next.next;
                nextIndex++;
                return temp.value;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    private static class Node<T> {
        Node next;
        T value;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}