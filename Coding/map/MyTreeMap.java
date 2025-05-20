package map;

import java.util.Comparator;

public class MyTreeMap<K extends Comparable<K>, V> {
    private TreeNode<K, V> root;
    private int size;
    private final Comparator<? super K> comparator;

    public MyTreeMap() {
        this.comparator = null;
    }

    public MyTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class TreeNode<K, V> {
        K key;
        V value;
        TreeNode<K, V> left, right, parent;
        boolean color = RED; // New nodes are red

        TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable<? super K>) k1).compareTo(k2);
    }

    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("TreeMap does not support null keys");

        if (root == null) {
            root = new TreeNode<>(key, value, null);
            root.color = BLACK;
            size++;
            return;
        }

        TreeNode<K, V> parent = null;
        TreeNode<K, V> current = root;
        int cmp = 0;

        while (current != null) {
            parent = current;
            cmp = compare(key, current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.value = value;
                return;
            }
        }

        TreeNode<K, V> newNode = new TreeNode<>(key, value, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixAfterInsert(newNode);
        size++;
    }

    private void fixAfterInsert(TreeNode<K, V> x) {
        while (x != null && x != root && x.parent.color == RED) {
            TreeNode<K, V> parent = x.parent;
            TreeNode<K, V> grandparent = parent.parent;

            if (parent == grandparent.left) {
                TreeNode<K, V> uncle = grandparent.right;

                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    x = grandparent;
                } else {
                    if (x == parent.right) {
                        x = parent;
                        rotateLeft(x);
                    }
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    rotateRight(grandparent);
                }
            } else {
                TreeNode<K, V> uncle = grandparent.left;

                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    x = grandparent;
                } else {
                    if (x == parent.left) {
                        x = parent;
                        rotateRight(x);
                    }
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    rotateLeft(grandparent);
                }
            }
        }

        root.color = BLACK;
    }

    private boolean colorOf(TreeNode<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(TreeNode<K, V> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    private void rotateLeft(TreeNode<K, V> p) {
        if (p == null)
            return;

        TreeNode<K, V> r = p.right;
        p.right = r.left;

        if (r.left != null)
            r.left.parent = p;
        r.parent = p.parent;

        if (p.parent == null) {
            root = r;
        } else if (p == p.parent.left) {
            p.parent.left = r;
        } else {
            p.parent.right = r;
        }

        r.left = p;
        p.parent = r;
    }

    private void rotateRight(TreeNode<K, V> p) {
        if (p == null)
            return;

        TreeNode<K, V> l = p.left;
        p.left = l.right;

        if (l.right != null)
            l.right.parent = p;
        l.parent = p.parent;

        if (p.parent == null) {
            root = l;
        } else if (p == p.parent.left) {
            p.parent.left = l;
        } else {
            p.parent.right = l;
        }

        l.right = p;
        p.parent = l;
    }

    public V get(K key) {
        TreeNode<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    private TreeNode<K, V> getNode(K key) {
        if (key == null)
            throw new NullPointerException();

        TreeNode<K, V> current = root;

        while (current != null) {
            int cmp = compare(key, current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }

        return null;

    }

    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printInOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode<K, V> node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        System.out.println(node.key + " = " + node.value);
        inOrderTraversal(node.right);
    }

    public static void main(String[] args) {
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(10, "A");
        map.put(20, "B");
        map.put(30, "C");
        map.put(15, "D");
        map.put(25, "E");
        map.put(5, "F");

        map.printInOrder();

    }

}
