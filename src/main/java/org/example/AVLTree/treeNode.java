package org.example.AVLTree;

public class treeNode {

    public String Val;
    treeNode Left;
    treeNode Right;
    treeNode Parent;

    public treeNode(String value){
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

    public treeNode getLeft(){
        return this.Left;
    }
    public treeNode getRight(){
        return this.Right;
    }
    public treeNode getParent(){
        return this.Parent;
    }

}



