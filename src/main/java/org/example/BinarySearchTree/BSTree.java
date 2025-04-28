package org.example.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

//  Steps:
//    If the tree is empty, create a new code with the postcode and set it as root.
//    If the new postcode is alphabetically less than the current node-Go left and repeat the process
//    If the new postcode is alphabetically greater than the current node-Go right and repeat the process.
//    If the postcode already exists(equal), do nothing-Ignore duplicates
//    Then increase the count by 1 when a new postcode is added.


public class BSTree {
    private BSTNode root;
    private int count = 0;

    public void insert(String postcode) {
        root = insertRec(root, postcode);
    }

    private BSTNode insertRec(BSTNode root, String postcode) {
        if (root == null) {
            count++;
            return new BSTNode(postcode);
        }
        if (postcode.compareTo(root.postcode) < 0) {
            root.left = insertRec(root.left, postcode);
        } else if (postcode.compareTo(root.postcode) > 0) {
            root.right = insertRec(root.right, postcode);
        } else {
            System.out.println(postcode + " has already been inserted.");
        }
        return root;
    }

    public boolean search(String postcode) {
        return searchRec(root, postcode);
    }

    private boolean searchRec(BSTNode root, String postcode) {
        if (root == null) return false;
        if (postcode.equals(root.postcode)) return true;
        return postcode.compareTo(root.postcode) < 0
                ? searchRec(root.left, postcode)
                : searchRec(root.right, postcode);
    }

    public boolean delete(String postcode) {
        int before = count;
        root = deleteRec(root, postcode);
        return count < before;
    }

    private BSTNode deleteRec(BSTNode node, String postcode) {
        if (node == null) return null;
        if (postcode.compareTo(node.postcode) < 0) {
            node.left = deleteRec(node.left, postcode);
        } else if (postcode.compareTo(node.postcode) > 0) {
            node.right = deleteRec(node.right, postcode);
        } else {
            count--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode successor = findMin(node.right);
            node.postcode = successor.postcode;
            node.right = deleteRec(node.right, successor.postcode);
        }
        return node;
    }

    private BSTNode findMin(BSTNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public int count() {
        return count;
    }

    public String[] inOrder() {
        List<String> result = new ArrayList<>();
        inOrderRec(root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderRec(BSTNode node, List<String> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.postcode);
            inOrderRec(node.right, result);
        }
    }
}



