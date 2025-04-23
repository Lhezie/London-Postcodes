package org.example.AVLTree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    int LeftHeight = 0;
    int RightHeight = 0;
    public treeNode root;

    public AVLTree(int rootVal) {
        root = new treeNode(rootVal);
    }

    void PrintTree(){

        treeNode root = this.root;
        List<treeNode> NodeQueue = new ArrayList<>();
        List<treeNode> Visited = new ArrayList<>();

        StringBuilder Tree = new StringBuilder();
        String leftbranchprim = "/ ";
        String rightbranchprim = "\\";
        String Space = " ";

        NodeQueue.add(root);
        while(!NodeQueue.isEmpty()){
            if(root.Left != null){
                NodeQueue.add(root.Left);
            }
            if(root.Right != null){
                NodeQueue.add(root.Right);
            }
            Visited.add(root);
            NodeQueue.removeFirst();
            root = NodeQueue.getFirst();
        }
        DFS(this.root, this.LeftHeight);

        int MaxDepth = this.LeftHeight;

        this.LeftHeight = 0;
        int currentdepth = 0;

        for(treeNode node : Visited){
            treeNode temp = node;
            int depth = 0;
            while(node.getParent() != this.root || node.getParent() != null){
                temp = temp.getParent();
                depth ++;
            }
            for(int i = 0; i < MaxDepth - depth; i++){
                Tree.append(Space);
            }
            Tree.append(node.Val);
            Tree.append("\n" + leftbranchprim + rightbranchprim);
            System.out.println(Tree);
            Tree = new StringBuilder();
        }



    }

    void DFS(treeNode root, int increment) {
        System.out.println(root.Val);
        increment++;
        if (root.getLeft() != null) {
            DFS(root.getLeft(), increment);
        }

        if (root.getRight() != null) {
            DFS(root.getRight(), increment);
        }
    }

    int calculateBalance(treeNode root) {

        treeNode BranchLeft = root.getLeft();
        treeNode BranchRight = root.getRight();

        this.DFS(BranchLeft, this.LeftHeight);
        this.DFS(BranchRight, this.RightHeight);

        int rh = this.RightHeight;
        int lh = this.LeftHeight;

        this.RightHeight = 1;
        this.LeftHeight = 1;

        return rh - lh;
    }

    public treeNode Find(int nodeValue) {
        treeNode CurrentNode = this.root;

        while (CurrentNode.Val != nodeValue) {

            if (nodeValue < CurrentNode.Val) {
                if (CurrentNode.getLeft() != null) {
                    CurrentNode = CurrentNode.getLeft();
                }
                else{
                    return CurrentNode;
                }
            }
            if (nodeValue > CurrentNode.Val) {
                if (CurrentNode.getRight() != null) {
                    CurrentNode = CurrentNode.getRight();
                }
                else{
                    return CurrentNode;
                }
            }
            if (CurrentNode.getLeft() != null && CurrentNode.getRight() != null) {
                return CurrentNode;
            }
        }
        return CurrentNode;
    }




    public void Insert(int nodeValue) {
        treeNode findPosition = this.Find(nodeValue);
        if (findPosition.Val != nodeValue) {
            if (nodeValue < findPosition.Val) {
                treeNode NewNode = new treeNode(nodeValue);
                NewNode.setParent(findPosition);
                findPosition.setLeft(NewNode);
                System.out.println(findPosition.Val);
                System.out.println(findPosition.getLeft().Val);
            } else {
                treeNode NewNode = new treeNode(nodeValue);
                NewNode.setParent(findPosition);
                findPosition.setRight(NewNode);
                System.out.println(findPosition.Val);
                System.out.println(findPosition.getRight().Val);
            }// BalanceTree(findPosition);
        }

    }

    public void Delete(int nodeValue) {

        treeNode findPosition = this.Find(nodeValue);

        if (findPosition.Val == nodeValue) {
            treeNode parent = findPosition.getParent();
            treeNode rightBranch = findPosition.getRight();
            treeNode leftBranch = findPosition.getLeft();

            if (findPosition.getRight() != null && findPosition.getLeft() != null) {

                treeNode replacement;
                treeNode replacementLbranch;
                treeNode replacementRbranch;
                treeNode replacementParent;

                if (findPosition.getRight().getLeft() != null) {

                    replacement = findPosition.getRight().getLeft();
                    replacementLbranch = replacement.getLeft();
                    replacementRbranch = replacement.getRight();
                    replacementParent = replacement.getParent();

                    replacementParent.setLeft(replacementLbranch);
                    replacementParent.getLeft().setRight(replacementRbranch);

                    findPosition.Val = replacement.Val;

                } else if (findPosition.getRight().getRight() != null) {

                    replacement = findPosition.getRight().getRight();
                    replacementLbranch = replacement.getLeft();
                    replacementRbranch = replacement.getRight();
                    replacementParent = replacement.getParent();
                    replacementParent.setRight(replacementRbranch);
                    replacementParent.getRight().setLeft(replacementLbranch);

                    findPosition.Val = replacement.Val;

                }else{
                    if(findPosition.Parent.getRight() == findPosition){
                        findPosition.Parent.setRight(findPosition.getRight());
                    }
                    if(findPosition.Parent.getLeft() == findPosition){
                        findPosition.Parent.setLeft(findPosition.getRight());
                    }
                }
                BalanceTree(findPosition);
            } else if (findPosition.getLeft() != null || findPosition.getRight() != null) {
                if (findPosition.getRight() == null && findPosition.getLeft() != null) {
                    if (parent.getRight() == findPosition) {
                        parent.setRight(leftBranch);
                        System.out.println("Deleted" + " " + parent.Val + " " + leftBranch.Val);
                    }
                    if (parent.getLeft() == findPosition) {
                        parent.setLeft(leftBranch);
                        System.out.println("Deleted" + " " + parent.Val + " " + leftBranch.Val);
                    }
                }
                if (findPosition.getLeft() == null && findPosition.getRight() != null) {
                    if (parent.getLeft() == findPosition) {
                        parent.setLeft(rightBranch);
                        System.out.println("Deleted" + " " + parent.Val + " " + rightBranch.Val);
                        //BalanceTree(parent);
                    }
                    if (parent.getRight() == findPosition) {
                        parent.setRight(rightBranch);
                        System.out.println("Deleted" + " " + parent.Val + " " + rightBranch.Val);
                    }
                }
            } else {
                if (parent.getLeft() == findPosition) {
                    parent.setLeft(null);
                    }
                if (parent.getRight() == findPosition) {
                    parent.setRight(null);
                    }
                    //BalanceTree(parent);
                }
            }
        }

    void RightRotation(treeNode target) {
        treeNode Parent = target.getParent();
        treeNode RightBranch = target.getRight();

        target.setRight(Parent);
        target.getRight().setLeft(RightBranch);

        if (Parent.getParent() != null) {
            target.setParent(Parent.getParent());
            Parent.setParent(target);
        }
    }

    void LeftRotation(treeNode target) {
        treeNode Parent = target.getParent();
        treeNode LeftBranch = target.getLeft();

        target.setLeft(Parent);
        target.getLeft().setRight(LeftBranch);

        if (Parent.getParent() != null) {
            target.setParent(Parent.getParent());
            Parent.setParent(target);
        }
    }

    void BalanceNode(treeNode root) {
        int currentBalance = this.calculateBalance(root);
        while(currentBalance > 1 || currentBalance < -1){
            if (currentBalance < -1) {
                this.RightRotation(root);
                currentBalance = this.calculateBalance(root);
            }
            if(currentBalance > 1){
                this.LeftRotation(root);
                currentBalance = this.calculateBalance(root);
            }
        }
    }
    void BalanceTree(treeNode root) {
        while (root.getParent() != null) {
            BalanceNode(root);
        }
    }
}
