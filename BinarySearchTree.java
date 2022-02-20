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
     * FROM PROF LEWICKI'S SLIDES
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
     * Inorder traversal of the BST recursively
     * 
     * @return an inorder list of the values in the BST
     */
    public List<V> inorderRec() {
        return null;
    }

    /**
     * Returns the k-th smallest element in the BST
     * 
     * @param k the position from the smallest element
     * @return the k-th smallest value in the BST
     */
    public V kthSmasllest(int k) {
        return null;
    }
}
