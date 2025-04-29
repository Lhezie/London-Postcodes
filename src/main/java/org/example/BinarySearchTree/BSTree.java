package org.example.BinarySearchTree;
import org.example.AVLTree.Tree;


import java.util.ArrayList;
import java.util.List;

//  Steps:
//    If the tree is empty, create a new code with the postcode and set it as root.
//    If the new postcode is alphabetically less than the current node-Go left and repeat the process
//    If the new postcode is alphabetically greater than the current node-Go right and repeat the process.
//    If the postcode already exists(equal), do nothing-Ignore duplicates
//    Then increase the count by 1 when a new postcode is added.

public class BSTree extends Tree {
    private int count;

    public BSTree() {
        this.root = null;
        this.count = 0;
    }

    @Override
    public void Insert(String nodeValue) {
        if (!Search(nodeValue)) {
            // root is a treeNode; cast when recursing
            root = insertRec((BSTNode) root, nodeValue);
            count++;
        } else {
            System.out.println(nodeValue + " already exists. No insertion made.");
        }
    }

    private BSTNode insertRec(BSTNode node, String nodeValue) {
        if (node == null) {
            return new BSTNode(nodeValue);
        }
        int cmp = nodeValue.compareTo(node.postcode);
        if (cmp < 0) {
            node.left  = insertRec((BSTNode) node.left, nodeValue);
        } else if (cmp > 0) {
            node.right = insertRec((BSTNode) node.right, nodeValue);
        }
        // duplicate: nothing
        return node;
    }

    @Override
    public boolean Search(String nodeValue) {
        return searchRec((BSTNode) root, nodeValue);
    }

    private boolean searchRec(BSTNode node, String nodeValue) {
        if (node == null) return false;
        if (nodeValue.equals(node.postcode)) return true;
        if (nodeValue.compareTo(node.postcode) < 0) {
            return searchRec((BSTNode) node.left, nodeValue);
        } else {
            return searchRec((BSTNode) node.right, nodeValue);
        }
    }

    @Override
    public boolean Delete(String nodeValue) {
        int before = count;
        root = deleteRec((BSTNode) root, nodeValue);
        return count < before;
    }

    private BSTNode deleteRec(BSTNode node, String nodeValue) {
        if (node == null) return null;
        int cmp = nodeValue.compareTo(node.postcode);
        if (cmp < 0) {
            node.left = deleteRec((BSTNode) node.left, nodeValue);
        } else if (cmp > 0) {
            node.right = deleteRec((BSTNode) node.right, nodeValue);
        } else {
            count--;
            if (node.left == null)  return (BSTNode) node.right;
            if (node.right == null) return (BSTNode) node.left;
            BSTNode successor = findMin((BSTNode) node.right);
            node.postcode = successor.postcode;
            node.right = deleteRec((BSTNode) node.right, successor.postcode);
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = (BSTNode) node.left;
        }
        return node;
    }

    @Override
    public String[] InOrder() {
        List<String> result = new ArrayList<>();
        inOrderRec((BSTNode) root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderRec(BSTNode node, List<String> result) {
        if (node == null) return;
        inOrderRec((BSTNode) node.left, result);
        result.add(node.postcode);
        inOrderRec((BSTNode) node.right, result);
    }

    @Override
    public int Count() {
        return count;
    }

    // convenience wrappers so old code that calls lower-case methods still works
    public void insert(String v)     { Insert(v); }
    public boolean search(String v)  { return Search(v); }
    public boolean delete(String v)  { return Delete(v); }
    public String[] inOrder()        { return InOrder(); }
    public int count()               { return Count(); }
}

