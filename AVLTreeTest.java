import org.junit.Assert;
import org.junit.Test;

public class AVLTreeTest {
    AVLTree<Integer, Integer> tree = new AVLTree<>();

    @Test
    public void testInsertDelete() {
        tree.insert(1, 1);
        Assert.assertEquals("[1]", tree.inorderRec().toString());
        Assert.assertEquals(Integer.valueOf(1), tree.search(1));
        tree.insert(2, 2);
        Assert.assertEquals("[1, 2]", tree.inorderRec().toString());
        tree.insert(3, 3); // tree will have to rebalance
        Assert.assertEquals("[1, 2, 3]", tree.inorderRec().toString());
        Assert.assertEquals("[1, 3, 2]", tree.postorder().toString());
        tree.insert(7, 7);
        tree.insert(6, 6); // rebalance
        Assert.assertEquals("[1, 3, 7, 6, 2]", tree.postorder().toString());
        Assert.assertEquals("[1, 2, 3, 6, 7]", tree.inorderRec().toString());
        tree.insert(9, 9); // rebalance
        Assert.assertEquals("[1, 2, 3, 6, 7, 9]", tree.inorderRec().toString());
        Assert.assertEquals("[1, 3, 2, 9, 7, 6]", tree.postorder().toString());
        tree.insert(-3, -3);
        tree.insert(-2, -2); // rebalance
        Assert.assertEquals("[-3, -2, 1, 2, 3, 6, 7, 9]", tree.inorderRec().toString());
        Assert.assertEquals("[-3, 1, -2, 3, 2, 9, 7, 6]", tree.postorder().toString());

        // Testing delete
        tree.delete(-3); // should do nothing but remove -3
        Assert.assertEquals("[-2, 1, 2, 3, 6, 7, 9]", tree.inorderRec().toString());
        Assert.assertEquals("[1, -2, 3, 2, 9, 7, 6]", tree.postorder().toString());
        tree.delete(3); // needs rebalance
        Assert.assertEquals("[-2, 1, 2, 6, 7, 9]", tree.inorderRec().toString());
        Assert.assertEquals("[-2, 2, 1, 9, 7, 6]", tree.postorder().toString());
        tree.delete(7); // no rebalancing
        Assert.assertEquals("[-2, 1, 2, 6, 9]", tree.inorderRec().toString());
        Assert.assertEquals("[-2, 2, 1, 9, 6]", tree.postorder().toString());
        tree.delete(9); // rebalancing
        Assert.assertEquals("[-2, 1, 2, 6]", tree.inorderRec().toString());
        Assert.assertEquals("[-2, 1, 6, 2]", tree.postorder().toString());
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> AVL = new AVLTree<>();
        BinarySearchTree<Integer, Integer> BST = new BinarySearchTree<>();
        double start = System.currentTimeMillis();
        for (int i = -10000; i <= 10000; i++)
            BST.insert(i, i);
        double end = System.currentTimeMillis();
        System.out.println("Time to insert 20000 values into BST: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        for (int i = -10000; i <= 10000; i++)
            AVL.insert(i, i);
        end = System.currentTimeMillis();
        System.out.println("Time to insert 20000 values into AVL tree: " + (end - start) + " ms");
        start = System.nanoTime();
        BST.search(10000);
        end = System.nanoTime();
        System.out.println("Time to search for 10000 in BST: " + (end - start) + " ns");
        start = System.nanoTime();
        AVL.search(10000);
        end = System.nanoTime();
        System.out.println("Time to search for 10000 in AVL tree: " + (end - start) + " ns");
    }
}
