import java.util.*;


public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public Node<K, V> getRoot() {
        return root;
    }

    public int getDepth() {
        return getDepth(root);
    }

    private int getDepth(Node<K, V> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0;
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node node) {
        return (node == null) ? 0 : 1 + getSize(node.left) + getSize(node.right);
    }

    public boolean contains(K key) {
        return find(key) != null;
    }

    public Node<K, V> find(K key) {
        return find(root, key);
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return find(node.left, key);
        } else if (cmp > 0) {
            return find(node.right, key);
        } else {
            return node;
        }
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node(null, key, value, null);
        } else {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                node.left = insert(node.left, key, value);
            } else if (cmp > 0) {
                node.right = insert(node.right, key, value);
            } else {
                node.value = value;
            }
        }
        return node;
    }

    public void removeAll() {
        root = null;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<K, V> temp = node;
                node = min(temp.right);
                node.right = removeMin(node.right);
                node.left = temp.left;
            }
        }
        return node;
    }

    public void removeMin() {
        root = removeMin(root);
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node.right;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    private Node<K, V> min(Node<K, V> node) {
        return (node.left == null) ? node : min(node.left);
    }

    public void printWidth() {
        printWidth(root, 0);
    }

    private void printWidth(Node<K, V> node, int depth) {
        if (node != null) {
            printWidth(node.right, depth + 1);
            for (int k = 0; k < depth; k++) {
                System.out.print("    ");
            }
            System.out.println(node.key);
            printWidth(node.left, depth + 1);
        }
    }

    public void printDepth() {
        printDepth(root);
    }

    private void printDepth(Node<K, V> root) {
        printNodeInternal(Collections.singletonList(root), 0, getDepth());
    }

    private boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

    private void printNodeInternal(List<Node<K, V>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<Node<K, V>> newNodes = new ArrayList<Node<K, V>>();
        for (Node<K, V> node : nodes) {
            if (node != null) {
                System.out.print(node.key);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(2 * endLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(2 * endLines - i);
            }
            System.out.println("");
        }
        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node left;
        Node right;

        Node(Node<K, V> left, K key, V value, Node<K, V> right) {
            this.left = left;
            this.key = key;
            this.value = value;
            this.right = right;
        }
    }
}