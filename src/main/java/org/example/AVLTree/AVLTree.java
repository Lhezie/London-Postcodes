package org.example.AVLTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree {
    int LeftHeight = 0;
    int RightHeight = 0;
    public treeNode root;

    public AVLTree(int rootVal) {
        root = new treeNode(rootVal);
    }


    public List<Integer> ReturnSortedElements() {
        List<Integer> SortedElements = new ArrayList<>();
        List<treeNode> SortingStack = new ArrayList<>();
        treeNode Root = this.root;
        int RootVal = Root.Val;
        SortingStack.addFirst(Root);

        while (!SortingStack.isEmpty()) {
            if(Root.Val != RootVal){
                SortingStack.addFirst(Root);
            }else{
                SortedElements.add(Root.Val);
                SortingStack.addFirst(root.getRight());
            }
            if (Root.getLeft() != null) {
                Root = Root.getLeft();
            }else if (Root.getLeft() == null) {
                Root = SortingStack.getFirst();
                treeNode Value = SortingStack.removeFirst();
                SortedElements.add(Value.Val);
            }
            if (Root.getRight() != null) {
                Root = Root.getRight();
            }
            else if(Root.getRight() == null) {
                Root = SortingStack.getFirst();
                treeNode Value = SortingStack.removeFirst();
                SortedElements.add(Value.Val);
            }
        }
        for(var k: SortedElements){
            System.out.print(k + ", ");
        }
        return SortedElements;
    }

    int DFS(treeNode root, int increment) {
        increment++;
        if (root.getLeft() != null) {
            DFS(root.getLeft(), increment);
        }

        if (root.getRight() != null) {
            DFS(root.getRight(), increment);
        }
        return increment;
    }

    public int calculateBalance(treeNode root) {

        treeNode BranchRight;
        treeNode BranchLeft;
        int LeftHeight = 0;
        int RightHeight = 0;

        if(root.getLeft() != null) {
             BranchLeft = root.getLeft();
             LeftHeight = GetHeight(BranchLeft);
        }
        if(root.getRight() != null) {
             BranchRight = root.getRight();
             RightHeight = GetHeight(BranchRight);
        }

        return RightHeight - LeftHeight;
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

    public int GetHeight(treeNode root) {

        treeNode CurrentNode = this.root;
        int height = 0;

        while (CurrentNode.getLeft() != null && CurrentNode.getRight() != null) {

                if (CurrentNode.getLeft() != null) {
                    CurrentNode = CurrentNode.getLeft();
                    height++;
                    continue;
                }

                if (CurrentNode.getRight() != null) {
                    CurrentNode = CurrentNode.getRight();
                    height++;
                }
            }
        return height;
    }


    public void Insert(int nodeValue) {
        treeNode findPosition = this.Find(nodeValue);
        if (findPosition.Val != nodeValue) {
            if (nodeValue < findPosition.Val) {
                treeNode NewNode = new treeNode(nodeValue);
                NewNode.setParent(findPosition);
                findPosition.setLeft(NewNode);
                BalanceTree(findPosition.getLeft());
            } else {
                treeNode NewNode = new treeNode(nodeValue);
                NewNode.setParent(findPosition);
                findPosition.setRight(NewNode);
                BalanceTree(findPosition.getRight());
            }
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
                    }
                    if (parent.getLeft() == findPosition) {
                        parent.setLeft(leftBranch);
                    }

                    BalanceTree(parent);

                }
                if (findPosition.getLeft() == null && findPosition.getRight() != null) {
                    if (parent.getLeft() == findPosition) {
                        parent.setLeft(rightBranch);

                    }
                    if (parent.getRight() == findPosition) {
                        parent.setRight(rightBranch);
                    }

                    BalanceTree(parent);
                }
            } else {
                if (parent.getLeft() == findPosition) {
                    parent.setLeft(null);
                    }
                if (parent.getRight() == findPosition) {
                    parent.setRight(null);
                    }

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
        treeNode current = root;

        while (current.getParent() != null ) {
            if(calculateBalance(current) < -1 || calculateBalance(current) > 1 ) {
                BalanceNode(root);
            }
            current = current.getParent();

            }
        }
    }

