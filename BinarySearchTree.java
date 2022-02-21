import java.util.LinkedList;
import java.util.List;

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
         * @param value Value stored in the noce
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
        while (trav != null) {
            parent = trav;
            if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
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
        while (trav != null) {
            if (key.compareTo(trav.key) == 0)
                return trav.value;
            else if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
        return null;
    }

    /**
     * Recursive method to help with delete method
     * @param root the root of the tree containing the node to be deleted
     * @param key the key of the node to be deleted
     * @return 
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
        } else { // Two children
            Node replacementParent = toDelete;
            Node replacement = toDelete.left;
            while (replacement.right != null) {
                replacementParent = replacement;
                replacement = replacement.right;
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
        Node parent = trav;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key.compareTo(trav.key) < 0)
                trav = trav.left;
            else
                trav = trav.right;
        }
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
     * @return an inorder list of the values in the BST
     */
    public List<V> inorderRec() {
        LinkedList<V> list = new LinkedList<V>();
        return inorder(root, list);
    }

    /**
     * Returns the k-th smallest element in the BST (1-indexed)
     * @param k the position from the smallest element
     * @return the k-th smallest value in the BST
     */
    public V kthSmallest(int k) {
        LinkedList<V> list = (LinkedList<V>)inorderRec();
        if (k > 0 && (k-1) < inorderRec().size())
            return list.get(k-1);
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
    }
}
