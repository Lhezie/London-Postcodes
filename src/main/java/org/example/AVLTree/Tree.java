package org.example.AVLTree;

public abstract class Tree {
    public treeNode root;

    public abstract int Count();

    public abstract void Insert(String nodeValue);

    public abstract boolean Delete(String nodeValue);

    public abstract boolean Search(String nodeValue);

    public abstract String[] InOrder();

}
