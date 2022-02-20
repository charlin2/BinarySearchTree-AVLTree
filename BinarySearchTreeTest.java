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
        for (int i = 2; i < 8; i++) intTree.insert(i, i*10); 

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

        intTree.delete(7);
        stringTree.delete("E");
        Assert.assertEquals(null, intTree.search(7));
        Assert.assertEquals(null, stringTree.search("E"));
    }
}