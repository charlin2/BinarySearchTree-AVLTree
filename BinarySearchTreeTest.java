import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {
    BinarySearchTree<Integer, Integer> intTree = new BinarySearchTree<Integer, Integer>();
    BinarySearchTree<String, String> stringTree = new BinarySearchTree<String, String>();

    @Test
    public void testInsertSearch() {
        // Test 0
        intTree.insert(5, 50);
        stringTree.insert("G", "Game");

        Assert.assertEquals(Integer.valueOf(50), intTree.search(5));
        Assert.assertEquals("Game", stringTree.search("G"));

        // searching nonexistant key
        Assert.assertEquals(null, intTree.search(100));
        Assert.assertEquals(null, stringTree.search("Null"));

        // Test many
        intTree.insert(5, 55); // duplicate insert
        stringTree.insert("G", "AB");

        Assert.assertEquals(Integer.valueOf(50), intTree.search(5)); // will return first instance of duplicate key
        Assert.assertEquals("Game", stringTree.search("G"));

        for (int i = 2; i < 8; i++) intTree.insert(i, i*10); 

        stringTree.insert("A", "Abacus");
        stringTree.insert("N", "Name");
        stringTree.insert("E", "Elephant");
        stringTree.insert("Z", "Zebra");
        
        Assert.assertEquals(Integer.valueOf(20), intTree.search(2));
        Assert.assertEquals(Integer.valueOf(30), intTree.search(3));
        Assert.assertEquals(Integer.valueOf(40), intTree.search(4));
        Assert.assertEquals(Integer.valueOf(50), intTree.search(5));
        Assert.assertEquals(Integer.valueOf(60), intTree.search(6));
        Assert.assertEquals(Integer.valueOf(70), intTree.search(7));

        Assert.assertEquals("Abacus", stringTree.search("A"));
        Assert.assertEquals("Name", stringTree.search("N"));
        Assert.assertEquals("Elephant", stringTree.search("E"));
        Assert.assertEquals("Zebra", stringTree.search("Z"));
        Assert.assertEquals("Game", stringTree.search("G"));

        // searching nonexistant key
        Assert.assertEquals(null, intTree.search(100));
        Assert.assertEquals(null, stringTree.search("Null"));
    }

    @Test
    public void testDelete() {
        // Test 0
        try {
            intTree.delete(4);
            stringTree.delete("A");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        // Test 1
        intTree.insert(5, 50);
        stringTree.insert("H", "Helmet");

        // should do nothing to BSTs
        intTree.delete(1);
        stringTree.delete("A");
        Assert.assertEquals(Integer.valueOf(50), intTree.search(5));
        Assert.assertEquals("Helmet", stringTree.search("H"));

        // objects should be deleted
        intTree.delete(5);
        stringTree.delete("H");
        Assert.assertEquals(null, intTree.search(5));
        Assert.assertEquals(null, stringTree.search("H"));

        // Test many
        intTree.insert(5, 55);
        intTree.insert(9, 90);
        intTree.insert(3, 30);
        intTree.insert(5, 50);
        intTree.insert(13, 130);
        intTree.insert(20, 200);
        intTree.insert(1, 10);
        intTree.insert(7, 70);
        
        stringTree.insert("A", "Abacus");
        stringTree.insert("N", "Name");
        stringTree.insert("E", "Elephant");
        stringTree.insert("Z", "Zebra");
        stringTree.insert("H", "Helmet");
        stringTree.insert("A", "Alphabet");

        // deleting root of tree (second duplicate should still exist)
        intTree.delete(5);
        stringTree.delete("A");
        Assert.assertEquals(Integer.valueOf(50), intTree.search(5));
        Assert.assertEquals("Alphabet", stringTree.search("A"));
        Assert.assertEquals("Zebra", stringTree.search("Z"));
        Assert.assertEquals("Helmet", stringTree.search("H"));
        Assert.assertEquals("Elephant", stringTree.search("E"));
        Assert.assertEquals("Name", stringTree.search("N"));

        intTree.delete(7);
        stringTree.delete("E");
        Assert.assertEquals(null, intTree.search(7));
        Assert.assertEquals(null, stringTree.search("E"));
    }

    @Test
    public void testInorderRec() {
        /** Inorder traversal will always return elements of BST in ascending order */
        // Test 0
        Assert.assertEquals("[]", intTree.inorderRec().toString());
        Assert.assertEquals("[]", stringTree.inorderRec().toString());

        // Test 1
        intTree.insert(6, 60);
        stringTree.insert("F", "Frolic");
        Assert.assertEquals("[60]", intTree.inorderRec().toString());
        Assert.assertEquals("[Frolic]", stringTree.inorderRec().toString());

        // Test many
        intTree.insert(4, 40);
        intTree.insert(2, 20);
        intTree.insert(5, 50);
        intTree.insert(9, 90);
        intTree.insert(13, 130);
        stringTree.insert("B", "Bee");
        stringTree.insert("E", "Elephant");
        stringTree.insert("P", "Platypus");
        stringTree.insert("Z", "Zebra");
        Assert.assertEquals("[20, 40, 50, 60, 90, 130]", intTree.inorderRec().toString());
        Assert.assertEquals("[Bee, Elephant, Frolic, Platypus, Zebra]", stringTree.inorderRec().toString());

        intTree.delete(5);
        stringTree.delete("F");
        Assert.assertEquals("[20, 40, 60, 90, 130]", intTree.inorderRec().toString());
        Assert.assertEquals("[Bee, Elephant, Platypus, Zebra]", stringTree.inorderRec().toString());
    }

    @Test
    public void testKthSmallest() {
        // Test 0
        Assert.assertEquals(null, intTree.kthSmallest(1));
        Assert.assertEquals(null, stringTree.kthSmallest(1));

        // Test 1
        intTree.insert(8, 80);
        stringTree.insert("G", "Giraffe");
        Assert.assertEquals(Integer.valueOf(80), intTree.kthSmallest(1));
        Assert.assertEquals("Giraffe", stringTree.kthSmallest(1));
        Assert.assertEquals(null, intTree.kthSmallest(3));
        Assert.assertEquals(null, stringTree.kthSmallest(2));

        // Test many
        for (int i = 2; i < 15; i++) intTree.insert(i, i*10);
        stringTree.insert("A", "Aardvark");
        stringTree.insert("B", "Buffalo");
        stringTree.insert("C", "Cheetah");
        stringTree.insert("D", "Dinosaur");
        stringTree.insert("E", "Elephant");
        stringTree.insert("F", "Frog");
        stringTree.insert("G", "Giant");
        stringTree.insert("H", "Horse");
        stringTree.insert("I", "Ibex");
        stringTree.insert("J", "Jackal");
        stringTree.insert("K", "Kangaroo");
        stringTree.insert("L", "Lemur");
        stringTree.insert("P", "Panda");
        stringTree.insert("Z", "Zebra");
        Assert.assertEquals(Integer.valueOf(20), intTree.kthSmallest(1));
        Assert.assertEquals(Integer.valueOf(40), intTree.kthSmallest(3));
        Assert.assertEquals(Integer.valueOf(130), intTree.kthSmallest(13));
        Assert.assertEquals(null, intTree.kthSmallest(16));
        Assert.assertEquals("Aardvark", stringTree.kthSmallest(1));
        Assert.assertEquals("Ibex", stringTree.kthSmallest(10));
        Assert.assertEquals("Zebra", stringTree.kthSmallest(15));
        Assert.assertEquals(null, stringTree.kthSmallest(16));
    }
}