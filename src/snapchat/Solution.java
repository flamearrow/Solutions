package snapchat;

/**
 * Created by flamearrow on 1/21/16.
 */
public class Solution {
    public static void main(String[] args) {

        MyMap<Integer, Integer> map = new MyMap<>();
        // 0
        System.out.println(map.size());
        map.put(1, 2);
        // return 2
        System.out.println(map.get(1));
        // 1
        System.out.println(map.size());
        // return null
        System.out.println(map.get(2));
        map.put(2, 3);
        // 2
        System.out.println(map.size());
        // return 3
        System.out.println(map.get(2));

        map.put(2, 4);
        // 2
        System.out.println(map.size());
        // return 4
        System.out.println(map.get(2));


        System.out.println(map.toString());
    }
}

class MyMap<K extends Comparable<K>, V> {
    Node<K, V> root;
    int size;

    public MyMap() {

    }

    V get(K key) {
        return searchFromBST(root, key);
    }

    void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size = 1;
        } else {
            boolean addedNewNode = insertToBST(root, key, value);
            if (addedNewNode) {
                size++;
            }
        }
    }

    int size() {
        return size;
    }

    V searchFromBST(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        } else {
            if (root.key.compareTo(key) == 0) {
                return root.value;
            } else if (root.key.compareTo(key) < 0) {
                return searchFromBST(root.right, key);
            } else {
                return searchFromBST(root.left, key);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        doTraverse(sb, root);
        return sb.toString();
    }

    void doTraverse(StringBuilder sb, Node root) {
        if (root == null) {
            return;
        } else {
            doTraverse(sb, root.left);
            sb.append(" ");
            sb.append(root.key);
            sb.append("->");
            sb.append(root.value);
            sb.append(" ");
            doTraverse(sb, root.right);
        }
    }

    boolean insertToBST(Node<K, V> root, K key, V value) {
        if (root.key.compareTo(key) == 0) {
            root.value = value;
            return false;
        } else {
            if (root.key.compareTo(key) < 0) {
                if (root.right == null) {
                    root.setRight(key, value);
                    return true;
                } else {
                    return insertToBST(root.right, key, value);
                }
            } else {
                if (root.left == null) {
                    root.setLeft(key, value);
                    return true;
                } else {
                    return insertToBST(root.left, key, value);
                }
            }
        }
    }
}

class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    Node<K, V> left, right;

    public Node(K argKey, V argValue) {
        key = argKey;
        value = argValue;
    }

    public Node setLeft(K key, V value) {
        left = new Node(key, value);
        return left;
    }

    public Node setRight(K key, V value) {
        right = new Node(key, value);
        return right;
    }
}