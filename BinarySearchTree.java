import java.util.ArrayList;
import java.util.List;

/**
 * P3: Implementation of a Binary Search Tree
 * 
 * @author <i>Charlie Lin</i>
 */
public class BinarySearchTree<T extends Comparable<T>, V> {
    /** root of tree */
    private Node root;

    /** public class for the purpose of testing */
    private class Node {
        /** key to access/sort the node */
        private T key;

        /** value stored in the node */
        private V value;

        /** reference to left node */
        private Node left = null;

        /** reference to right node */
        private Node right = null;

        /**
         * Node of a BST
         * 
         * @param key   Key used for comparison and value access
         * @param value Value stored in the node
         */
        private Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Creates a new binary search tree
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a specified value into the BST
     * 
     * @param key   A comparable data type that designates the associated value
     * @param value The value being inserted into the tree
     */
    public void insert(T key, V value) {
        Node trav = root;
        // parent node is separate from trav for the sake of inserting
        Node parent = root;
        Node newNode = new Node(key, value);
        // traverse to insertion point
        while (trav != null) {
            parent = trav;
            if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
        // insert new node
        if (parent == null)
            root = newNode;
        else if (newNode.key.compareTo(parent.key) < 0)
            parent.left = newNode;
        else
            parent.right = newNode;
    }

    /**
     * Returns a specified node if it exists
     * 
     * @param key the value of the node to be searched for
     * @return the value associated with the key
     */
    public V search(T key) {
        Node trav = root;
        // traverse through tree
        while (trav != null) {
            if (key.compareTo(trav.key) == 0)
                return trav.value;
            else if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
        // if key not found, return null
        return null;
    }

    /**
     * Recursive method to help with delete method
     * USING LEWICKI'S SLIDES
     * 
     * @param toDelete the node to be deleted
     * @param parent   the parent node of the node to be deleted
     */
    private void remove(Node toDelete, Node parent) {
        // One or no children
        if (toDelete.left == null || toDelete.right == null) {
            Node toDeleteChild = null;
            if (toDelete.left != null)
                toDeleteChild = toDelete.left;
            else
                toDeleteChild = toDelete.right;
            if (toDelete == root)
                root = toDeleteChild;
            else if (toDelete.key.compareTo(parent.key) < 0)
                parent.left = toDeleteChild;
            else
                parent.right = toDeleteChild;
        } else { // two children
            Node replacementParent = toDelete;
            Node replacement = toDelete.right;
            // find largest value in left subtree
            while (replacement.left != null) {
                replacementParent = replacement;
                replacement = replacement.left;
            }
            toDelete.key = replacement.key;
            toDelete.value = replacement.value;
            remove(replacement, replacementParent);
        }
    }

    /**
     * Deletes a specified node if it exists
     * 
     * @param key the key of the node to be deleted
     */
    public void delete(T key) {
        Node trav = root;
        Node parent = null;
        // traverse to deletion node
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
        // helper method for deletion
        if (trav != null)
            remove(trav, parent);
    }

    /**
     * Recursive helper method for inorderRec
     */
    private List<V> inorder(Node root, List<V> list) {
        if (root == null)
            return list;
        inorder(root.left, list);
        list.add(root.value);
        inorder(root.right, list);
        return list;
    }

    /**
     * Inorder traversal of the BST recursively
     * 
     * @return an inorder list of the values in the BST
     */
    public List<V> inorderRec() {
        ArrayList<V> list = new ArrayList<V>(); // using array list for the kth smallest method due to constant access time
        return inorder(root, list);
    }

    /**
     * Returns the k-th smallest element in the BST (1-indexed)
     * 
     * @param k the position from the smallest element
     * @return the k-th smallest value in the BST
     */
    public V kthSmallest(int k) {
        ArrayList<V> list = (ArrayList<V>) inorderRec(); // array list is constant access time, O(n) insert, overall kthSmallest is O(n)
        if (k > 0 && (k - 1) < list.size())
            return list.get(k - 1);
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        System.out.println("BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>()");
        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(4, 4);
        tree.insert(5, 5);
        tree.insert(9, 9);
        tree.insert(3, 3);
        tree.insert(6, 6);
        tree.insert(7, 7);
        tree.insert(10, 10);
        tree.insert(12, 12);
        tree.insert(11, 11);
        System.out.println("tree.insert(2, 2)\ntree.insert(1, 1)\ntree.insert(4, 4)\ntree.insert(5, 5)\ntree.insert(9, 9)\ntree.insert(3, 3)\ntree.insert(6, 6)\ntree.insert(7, 7)\ntree.insert(10, 10)\ntree.insert(12, 12)\ntree.insert(11, 11)");
        tree.delete(4);
        tree.delete(9);
        System.out.println("tree.delete(4)\ntree.delete(9)");
        System.out.println("tree.inorderRec()\n" + tree.inorderRec().toString());
        System.out.println("tree.search(12)\n" + tree.search(12));
        System.out.println("tree.search(4)\n" + tree.search(4));
        System.out.println("tree.kthSmallest(3)\n" + tree.kthSmallest(3));
        BinarySearchTree<Character, String> tree2 = new BinarySearchTree<>();
        System.out.println("BinarySearchTree<Character, String> tree2 = new BinarySearchTree<>()");
        tree2.insert('G', "George");
        tree2.insert('S', "Susan");
        tree2.insert('J', "Jeff");
        tree2.insert('B', "Bertha");
        tree2.insert('M', "Matt");
        tree2.insert('A', "Alyssa");
        tree2.insert('L', "Lex");
        tree2.insert('M', "Madeline");
        System.out.println("tree2.insert('G', \"George\")\ntree2.insert('S', \"Susan\")\ntree2.insert('J', \"Jeff\")\ntree2.insert('B', \"Bertha\")\ntree2.insert('M', \"Matt\")\ntree2.insert('A', \"Alyssa\")\ntree2.insert('L', \"Lex\")\ntree2.insert('M', \"Madeline\")");
        System.out.println("tree2.inorderRec()\n" + tree2.inorderRec().toString());
        tree2.delete('M');
        tree2.delete('J');
        System.out.println("tree2.delete('M')\ntree2.delete('J')");
        System.out.println("tree2.inorderRec()\n" + tree2.inorderRec().toString());
        System.out.println("tree2.kthSmallest(3)\n" + tree2.kthSmallest(3));
    }
}
