package org.example;

import org.example.AVLTree.AVLTree;
import org.example.AVLTree.treeNode;


public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree(4);
        int[] vals = {7, 5, 9, 8, 13, 12, 17, 2};
        tree.root.setRight(new treeNode(5));
        tree.root.setLeft(new treeNode(1));
        tree.Insert(6);
        tree.Insert(7);
        tree.Insert(2);
        tree.Insert(8);
        tree.Insert(13);
        tree.Insert(3);
        tree.Insert(14);
        tree.Delete(13);






    }

        // should get 195
    }


