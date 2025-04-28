package org.example.minheap;
import org.example.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumHeap implements Tree {
    private HeapNode root;
    private int count;
    private final int capacity;

    // Node class for the heap
    private static class HeapNode {
        String value;
        HeapNode left;
        HeapNode right;
        HeapNode parent;

        public HeapNode(String value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    /**
     * Constructor - creates an empty minimum-heap that stores up to maxSize postcodes.
     * @param maxSize The maximum number of postcodes the heap can store
     */
    public MinimumHeap(int maxSize) {
        this.capacity = maxSize;
        this.root = null;
        this.count = 0;
    }

    /**
     * Returns the number of postcodes currently in the heap.
     * @return The number of postcodes in the heap
     */
    public int count() {
        return count;
    }

    /**
     * Inserts a postcode into the heap and maintains the min-heap property.
     * @param postcode The postcode to insert
     */
    public void insert(String postcode) {
        if (count >= capacity) {
            throw new IllegalStateException("Heap is full");
        }

        HeapNode newNode = new HeapNode(postcode);
        count++;

        // If the heap is empty, the new node becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        // Find the insertion point using level order traversal
        HeapNode parent = findInsertionPoint();

        // Attach the new node
        if (parent.left == null) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.parent = parent;

        // Heapify up from the new node
        siftUp(newNode);
    }

    /**
     * Extracts and returns the minimum (alphabetically first) postcode from the heap.
     * @return The minimum postcode, or null if the heap is empty
     */
    public String extractMinimum() {
        if (root == null) {
            return null;
        }

        String minPostcode = root.value;

        // If there's only one node, remove it
        if (count == 1) {
            root = null;
            count = 0;
            return minPostcode;
        }

        // Find the last node using level order traversal
        HeapNode lastNode = findLastNode();
        HeapNode lastNodeParent = lastNode.parent;

        // Replace root value with the last node's value
        root.value = lastNode.value;

        // Remove the last node
        if (lastNodeParent.right == lastNode) {
            lastNodeParent.right = null;
        } else {
            lastNodeParent.left = null;
        }

        count--;

        // Rebuild the heap
        siftDown(root);

        return minPostcode;
    }

    /**
     * Searches for a postcode in the heap.
     * @param postcode The postcode to search for
     * @return true if the postcode is found, false otherwise
     */
    public boolean search(String postcode) {
        if (root == null) {
            return false;
        }

        // Use level order traversal to search the heap
        Queue<HeapNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            HeapNode current = queue.poll();

            if (current.value.equals(postcode)) {
                return true;
            }

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return false;
    }

    /**
     * Returns an array containing all postcodes in ascending order.
     * Note: This empties the heap.
     * @return Array of postcodes in ascending order
     */
    public String[] inOrder() {
        String[] result = new String[count];
        int originalCount = count;

        // Extract minimum repeatedly to get sorted order
        for (int i = 0; i < originalCount; i++) {
            result[i] = extractMinimum();
        }

        return result;
    }

    /**
     * Find the next parent node where we should insert a child.
     * Uses level order traversal to find the first node that doesn't have two children.
     */
    private HeapNode findInsertionPoint() {
        Queue<HeapNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            HeapNode current = queue.poll();

            // If this node doesn't have both children, it's our insertion point
            if (current.left == null || current.right == null) {
                return current;
            }

            // Add children to the queue
            queue.add(current.left);
            queue.add(current.right);
        }

        // This shouldn't happen in a proper implementation
        throw new IllegalStateException("Failed to find insertion point");
    }

    /**
     * Find the last node in the heap (rightmost node at the deepest level).
     * Uses level order traversal to find it.
     */
    private HeapNode findLastNode() {
        if (root == null) {
            return null;
        }

        Queue<HeapNode> queue = new LinkedList<>();
        queue.add(root);
        HeapNode lastNode = root;

        while (!queue.isEmpty()) {
            HeapNode current = queue.poll();
            lastNode = current;

            // Add children to the queue (right first, so that left becomes last)
            if (current.right != null) {
                queue.add(current.right);
            }

            if (current.left != null) {
                queue.add(current.left);
            }
        }

        return lastNode;
    }

    /**
     * Moves a node up the heap to maintain the min-heap property.
     * @param node The node to sift up
     */
    private void siftUp(HeapNode node) {
        HeapNode current = node;

        //compares value of the current node to parent node
        while (current.parent != null && current.value.compareTo(current.parent.value) < 0) {
            // Swap values
            String temp = current.value;
            current.value = current.parent.value;
            current.parent.value = temp;

            // Move up
            current = current.parent;
        }
    }

    /**
     * Moves a node down the heap to maintain the min-heap property.
     * @param node The node to sift down
     */
    private void siftDown(HeapNode node) {
        if (node == null) return;

        HeapNode current = node;

        while (true) {
            HeapNode smallest = current;

            // Check if left child is smaller
            if (current.left != null && current.left.value.compareTo(smallest.value) < 0) {
                smallest = current.left;
            }

            // Check if right child is smaller
            if (current.right != null && current.right.value.compareTo(smallest.value) < 0) {
                smallest = current.right;
            }

            // If current is already the smallest, we're done
            if (smallest == current) {
                break;
            }

            // Swap values
            String temp = current.value;
            current.value = smallest.value;
            smallest.value = temp;

            // Move down
            current = smallest;
        }
    }

    /**
     * Main method for testing the MinimumHeap implementation
     */
    public static void main(String[] args) {
        // Create a new minimum heap
        MinimumHeap postcodeHeap = new MinimumHeap(10);

        // Insert some UK postcodes
        postcodeHeap.insert("SW1A 1AA");  // Buckingham Palace
        postcodeHeap.insert("EC1A 1BB");  // St. Paul's Cathedral
        postcodeHeap.insert("W1J 7NT");   // The Ritz London
        postcodeHeap.insert("NW1 4RY");   // London Zoo
        postcodeHeap.insert("SE1 7PB");   // The Shard

        // Display the count
        System.out.println("Number of postcodes in heap: " + postcodeHeap.count());

        // Search for postcodes
        System.out.println("Searching for 'EC1A 1BB': " + postcodeHeap.search("EC1A 1BB"));
        System.out.println("Searching for 'AB1 2CD': " + postcodeHeap.search("AB1 2CD"));

        // Extract the minimum postcode
        System.out.println("Minimum postcode: " + postcodeHeap.extractMinimum());
        System.out.println("After extraction, count: " + postcodeHeap.count());

        // Get all postcodes in order
        MinimumHeap copyHeap = new MinimumHeap(10);
        copyHeap.insert("SW1A 1AA");
        copyHeap.insert("EC1A 1BB");
        copyHeap.insert("W1J 7NT");
        copyHeap.insert("NW1 4RY");
        copyHeap.insert("SE1 7PB");

        System.out.println("\nAll postcodes in ascending order:");
        String[] orderedPostcodes = copyHeap.inOrder();
        for (String postcode : orderedPostcodes) {
            System.out.println(postcode);
        }

        // The heap should be empty after inOrder
        System.out.println("\nCount after inOrder: " + copyHeap.count());
    }
}