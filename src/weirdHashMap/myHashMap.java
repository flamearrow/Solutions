package weirdHashMap;

import java.util.HashMap;

/**
 * implement a Map that has three dimensions
 * MyHashMap<K, t, V> has K as key, t a float and V as value
 * to get a value, need to call map.get(key, t), if key doesn't exist, return nothing
 * if key exist and t doesn't exist, return map<key, t'> such that t' is the next smallest number
 * than t
 */
// use a hash map to map K to a node
// the node is a BST with key equals to the float value and V equals to value
// first find if the BST exists for the key, then search for the closest value from that BST
public class MyHashMap<K, V> {
    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, 1, "m");
        myHashMap.put(2, 1, "l");
        myHashMap.put(2, 2, "g");
        myHashMap.put(3, 1, "b");

        System.out.println(myHashMap.get(1, 1));//m
        System.out.println(myHashMap.get(1, 2));//m
        System.out.println(myHashMap.get(1, 0.5f));//null
        System.out.println(myHashMap.get(2, 1));//l
        System.out.println(myHashMap.get(2, 2));//g
        System.out.println(myHashMap.get(2, 1.5f));//l
        System.out.println(myHashMap.get(3, 1.5f));//b
        System.out.println(myHashMap.get(3, 0.5f));//null

    }

    private HashMap<K, BSTNode<V>> mMap;

    public MyHashMap() {
        mMap = new HashMap<>();
    }

    public void put(K key, float f, V value) {
        if (mMap.containsKey(key)) {
            mMap.get(key).insert(new BSTNode<>(f, value));
        } else {
            mMap.put(key, new BSTNode<>(f, value));
        }
    }

    public V get(K key, float f) {
        if (mMap.containsKey(key)) {
            return mMap.get(key).searchFor(f);
        } else {
            return null;
        }
    }

    class BSTNode<VA> {
        float mKey;
        VA mValue;
        BSTNode<VA> mLeft, mRight;

        public BSTNode(float key, VA value) {
            mKey = key;
            mValue = value;
        }

        void insert(BSTNode<VA> newNode) {
            if (newNode.mKey < mKey) {
                if (mLeft == null) {
                    mLeft = newNode;
                } else {
                    mLeft.insert(newNode);
                }
            } else if (newNode.mKey > mKey) {
                if (mRight == null) {
                    mRight = newNode;
                } else {
                    mRight.insert(newNode);
                }
            } else {
                mValue = newNode.mValue;
            }
        }

        // search 1.5, have 1 and 2, should return 1

        VA searchFor(float key) {
            if (key == mKey) {
                return mValue;
            } else if (key < mKey) {
                // should go left
                if (mLeft == null) {
                    return null;
                } else {
                    return mLeft.searchFor(key);
                }
            } else {
                // should go right
                // nothing on right, this is the biggest node that's smaller than key
                if (mRight == null) {
                    return mValue;
                } else {
                    return mRight.searchFor(key);
                }
            }
        }

    }
}

