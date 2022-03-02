import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * P3 Extra Credit: Implementation of an AVL Tree
 * 
 * @author <i>Charlie Lin</i>
 */
public class AVLTree<T extends Comparable<T>, V> {
    /** root of the AVL Tree */
    private Node root;

    /**
     * Individual nodes of the tree
     */
    private class Node {
        /** references to parent and left and right children */
        private Node right, left;

        /** the key associated with this node */
        private T key;

        /** the element stored in this node */
        private V value;

        /**
         * Constructor for tree node
         * 
         * @param key   Key used for comparison and value access
         * @param value Value stored in the node
         */
        private Node(T key, V value) {
            this.key = key;
            this.value = value;
            right = left = null;
        }
    }

    /**
     * Creates a new AVL tree
     */
    public AVLTree() {
        root = null;
    }

    /**
     * Returns the depth of a subtree
     * 
     * @param root the root of the subtree
     * @return the depth of the subtree
     */
    private int depth(Node root) {
        if (root == null)
            return -1;
        else {
            // compute the depth of each subtree
            int leftDepth = depth(root.left);
            int rightDepth = depth(root.right);
            if (leftDepth > rightDepth)
                return leftDepth + 1;
            else
                return rightDepth + 1;
        }
    }

    /**
     * Returns the balance of a subtree
     * 
     * @param root the root of the subtree
     * @return the balance of the subtree
     */
    private int getBalance(Node root) {
        if (root != null)
            return depth(root.right) - depth(root.left);
        return 0;
    }

    /**
     * Rightward rotation
     * 
     * @param node the node to be rotated around
     * @return the new root of the subtree after the rotation
     */
    private Node rotateRight(Node node) {
        // save value for rotation
        Node leftChild = node.left;
        Node leftRightChild = leftChild.right;
        // rotate
        leftChild.right = node;
        node.left = leftRightChild;
        if (node == root)
            root = leftChild;
        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        Node rightLeftChild = rightChild.left;
        rightChild.left = node;
        node.right = rightLeftChild;
        if (node == root)
            root = rightChild;
        return rightChild;
    }

    /**
     * Recursive insert helper method
     * 
     * @param root  the root of the tree to be inserted into
     * @param key   the key of the node to be inserted
     * @param value the value of the node to be inserted
     * @return child nodes
     */
    private Node insert(Node currentParent, Node newNode) {
        if (currentParent == null)
            return newNode;
        else if (newNode.key.compareTo(currentParent.key) < 0)
            currentParent.left = insert(currentParent.left, newNode);
        else
            currentParent.right = insert(currentParent.right, newNode);
        // balancing
        int balance = getBalance(currentParent);
        // right subtree, right imbalance
        if (balance > 1 && getBalance(currentParent.right) > 0)
            return rotateLeft(currentParent);
        // right subtree, left imbalance
        if (balance > 1 && getBalance(currentParent.right) < 0) {
            currentParent.right = rotateRight(currentParent.right);
            return rotateLeft(currentParent);
        }
        // left subtree, left imbalance
        if (balance < -1 && getBalance(currentParent.left) < 0)
            return rotateRight(currentParent);
        // left subtree, right imbalance
        if (balance < -1 && getBalance(currentParent.left) > 0) {
            currentParent.left = rotateLeft(currentParent.left);
            return rotateRight(currentParent);
        }
        return currentParent;
    }

    /**
     * Inserts a key-value pair into the AVL Tree and rebalances accordingly
     * 
     * @param key   the key of the node being inserted
     * @param value the value stored by the node being inserted
     */
    public void insert(T key, V value) {
        if (root == null)
            root = new Node(key, value);
        else
            insert(root, new Node(key, value));
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
     * Returns the minimum value in the subtree
     * @param root
     * @return
     */
    private Node minValue(Node root) {
        Node trav = root;
        while (trav.left != null)
            trav = trav.left;
        return trav;
    }

    /**
     * Recursive method to help with delete method
     * USING LEWICKI'S SLIDES
     * 
     * @param toDelete the node to be deleted
     * @param parent   the parent node of the node to be deleted
     */
    private Node remove(Node root, T key) {
        // traversal
        if (root == null) 
            return root;
        if (key.compareTo(root.key) < 0)
            root.left = remove(root.left, key);
        else if (key.compareTo(root.key) > 0) 
            root.right = remove(root.right, key);
        // deletion
        else {
            // one or no children
            if (root.left == null || root.right == null) {
                Node temp = null;
                if (root.left == null)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            } else { // two children
                Node temp = minValue(root.right);
                root.key = temp.key;
                root.value = temp.value;
                root.right = remove(root.right, temp.key);
            }
        }
        
        if (root == null)
            return root;

        // balancing
        int balance = getBalance(root);

        // right subtree, right imbalance
        if (balance > 1 && getBalance(root.right) > 0)
            return rotateLeft(root);
        // right subtree, left imbalance
        if (balance > 1 && getBalance(root.right) < 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        // left subtree, left imbalance
        if (balance < -1 && getBalance(root.left) < 0)
            return rotateRight(root);
        // left subtree, right imbalance
        if (balance < -1 && getBalance(root.left) > 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        return root;
    }

    /**
     * Deletes a specified node if it exists
     * 
     * @param key the key of the node to be deleted
     */
    public void delete(T key) {
        remove(root, key);
        /*
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
        */
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
        ArrayList<V> list = new ArrayList<V>();
        return inorder(root, list);
    }

    /**
     * Recursive helper method for postorder
     */
    private List<V> postorder(Node root, List<V> list) {
        if (root == null)
            return list;
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.value);
        return list;
    }

    /**
     * Returns a list containing the post order traversal of the tree
     * 
     * @return list in postorder traversal
     */
    public List<V> postorder() {
        LinkedList<V> list = new LinkedList<>();
        return postorder(root, list);
    }
}
