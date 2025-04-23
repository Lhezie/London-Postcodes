package org.example.AVLTree;

public class treeNode {

    int Val;
    treeNode Left;
    treeNode Right;
    treeNode Parent;

    public treeNode(int value){
        this.Val = value;
    }
    public void setLeft(treeNode left){
        this.Left = left;
    }
    public void setRight(treeNode right){
        this.Right = right;
    }
    void setParent(treeNode parent){
        this.Parent = parent;
    }

    treeNode getLeft(){
        return this.Left;
    }
    treeNode getRight(){
        return this.Right;
    }
    treeNode getParent(){
        return this.Parent;
    }

}



