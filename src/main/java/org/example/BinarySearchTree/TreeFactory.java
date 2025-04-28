package org.example.BinarySearchTree;

// Algorithm for TreeFactory:
//1.Receive a requested tree type as a string input ( "BST", "AVL", "MinHeap").
//2.If type is "BST", create and return a new BSTree object.
//3.If type is "AVL", create and return a new AVLTree object.
//4.If type is "MinHeap", create and return a new MinHeapTree object.
//5.If the input does not match any known type:
//6.Print a warning message to the user.
//7.Default to creating and returning a BSTree object.
//8.Return the created tree back to the caller (e.g., Main program) to be used normally.

public class TreeFactory {
    public static BSTree createTree(String type) {
        if (type.equalsIgnoreCase("BST")){
            return new BSTree();
        }
        else if (type.equalsIgnoreCase(" AVL")){
//            return new AVLTree();
        }
        else if (type.equalsIgnoreCase(" MinHeap")){
//            return new MinHeapTree();
        }
        else {
            System.out.println(type + " is an Unknown Tree Type. Defaulting to BST");
        return new BSTree();
        }
        return null;
    }
}
