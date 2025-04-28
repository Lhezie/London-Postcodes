package org.example.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class BSTreeTest {

    private BSTree tree;

    @BeforeEach
    public void setUp() {
        tree = new BSTree(); // Create a new tree before each test
    }

    @AfterEach
    public void tearDown() {
        tree = null; // Help clean memory after each test (optional)
    }

    @Test
    public void testInsertAndSearch() {
        tree.insert("E14 9QL");

        assertTrue(tree.search("E14 9QL"), "Postcode should be found after insertion.");
        assertFalse(tree.search("W1A 1AA"), "Postcode should not be found if not inserted.");
    }

    @Test
    public void testDelete() {
        tree.insert("E14 9QL");
        tree.insert("W1A 1AA");

        assertTrue(tree.delete("E14 9QL"), "Postcode should be deleted successfully.");
        assertFalse(tree.search("E14 9QL"), "Deleted postcode should not be found.");
        assertTrue(tree.search("W1A 1AA"), "Other postcodes should still exist.");
    }

    @Test
    public void testInOrderTraversal() {
        tree.insert("W1A 1AA");
        tree.insert("E14 9QL");
        tree.insert("C1A 1AA");

        String[] expectedOrder = {"C1A 1AA", "E14 9QL", "W1A 1AA"};
        assertArrayEquals(expectedOrder, tree.inOrder(), "In-order traversal should return sorted postcodes.");
    }

    @Test
    public void testCount() {
        assertEquals(0, tree.count(), "Initial count should be 0.");

        tree.insert("E14 9QL");
        assertEquals(1, tree.count(), "Count should increase after insertion.");

        tree.insert("W1A 1AA");
        assertEquals(2, tree.count(), "Count should reflect number of postcodes.");
    }
}
