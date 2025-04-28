package org.example.BinarySearchTree;

import org.example.Util.PostcodeTreeInterface;

import java.util.ArrayList;
import java.util.List;

//  Steps:
//    If the tree is empty, create a new code with the postcode and set it as root.
//    If the new postcode is alphabetically less than the current node-Go left and repeat the process
//    If the new postcode is alphabetically greater than the current node-Go right and repeat the process.
//    If the postcode already exists(equal), do nothing-Ignore duplicates
//    Then increase the count by 1 when a new postcode is added.




public class BSTree implements PostcodeTreeInterface {
    private BSTNode root;
    private int count;


//    develops an empty BSTree
    public BSTree() {
        root = null;
        count = 0;
    }



//    insert a postcode if it doesn't exist
    @Override
    public void insert(String postcode) {
        if (!search(postcode)) {
            root = insertRec(root, postcode);
            count++;
//            System.out.println("Postcode inserted successfully!");
        } else {
            System.out.println( postcode + " already exists. No insertion made.");
        }
    }


//    insert a new node recursively into the subtree
    private BSTNode insertRec(BSTNode node, String postcode) {
        if (node == null) {
            return new BSTNode(postcode);
        }
        int comparisonResult = postcode.compareTo(node.postcode);
        if (comparisonResult < 0) {
            node.left  = insertRec(node.left,  postcode);
        } else if (comparisonResult > 0) {
            node.right = insertRec(node.right, postcode);
        }
        // duplicate case: do nothing
        return node;
    }


//search for postcode in the tree
    @Override
    public boolean search(String postcode) {
        return searchRec(root, postcode);
    }

    private boolean searchRec(BSTNode node, String postcode) {
        if (node == null)
            return false;
        if (postcode.equals(node.postcode))
            return true;
        return postcode.compareTo(node.postcode) < 0
                ? searchRec(node.left, postcode)
                : searchRec(node.right, postcode);
    }


//    deletes postcode
    @Override
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

    //    return the minimum node in the subtree
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

//remembers the total number of nodes in the tree
    @Override
    public int count() {
        return count;
    }

//    return an array of all postcodes in ascending order
    @Override
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




