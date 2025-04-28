package org.example.BenchmarkTool;

import org.example.AVLTree.Tree;

import java.util.function.Supplier;
import java.util.function.Consumer;

public class BenchMarker {


    private Tree tree;

    private Supplier<Integer> Count;
    private Supplier<Boolean> Delete;
    private Supplier<Boolean> Search;
    private Consumer<String> Insert;
    private Supplier<String[]> inOrder;


    public BenchMarker(Tree Tree) {
        this.tree = Tree;
    }

    public void setTree(Tree Tree) {
        this.tree = Tree;
    }


    private void InitFuncsWithArgs(String InsertDeleteArg, String operation) {

        Consumer<String> Insert = (_) -> this.tree.Insert(InsertDeleteArg);
        Supplier<Boolean> Delete = () -> this.tree.Delete(InsertDeleteArg);
        Supplier<Boolean> Search = () -> this.tree.Search(InsertDeleteArg);
        Supplier<String[]> inOrder = () -> this.tree.InOrder();


        switch (operation) {
            case "insert":
                this.Insert = Insert;
                break;
            case "delete":
                this.Delete = Delete;
                break;
            case "search":
                this.Search = Search;
                break;
            case "inorder":
                this.inOrder = inOrder;
                break;

        }
    }

    private void InitFuncsNoArgs(String operation) {
        Supplier<String[]> inOrder = () -> this.tree.InOrder();
        Supplier<Integer> Count = () -> this.tree.Count();
        switch (operation) {
            case "inorder":
                this.inOrder = inOrder;
                break;
            case "count":
                this.Count = Count;
                break;
        }

    }

    public long RuntimeWithArgs(String arg, String operation) {

        long runtime;

        if (operation.equals("delete")) {
            InitFuncsWithArgs(arg, "delete");
            long start = System.nanoTime();
            this.Delete.get();
            long end = System.nanoTime();
            runtime = end - start / 1000000;
            return runtime;
        }
        if (operation.equals("search")) {
            InitFuncsWithArgs(arg, "search");
            long start = System.nanoTime();
            this.Search.get();
            long end = System.nanoTime();
            runtime = end - start / 1000000;
            return runtime;
        }
        if (operation.equals("insert")) {
            InitFuncsWithArgs(arg, "insert");
            long start = System.nanoTime();
            this.Insert.accept(arg);
            long end = System.nanoTime();
            runtime = end - start / 1000000;
            return runtime;
        }

        runtime = 0;

        return runtime;

    }

    public long RunTimeNoArgs(String operation) {
        long runtime;
        if (operation.equals("count")) {
            InitFuncsNoArgs(operation);
            long start = System.nanoTime();
            this.Count.get();
            long end = System.nanoTime();
            runtime = end - start / 1000000;
            return runtime;
        }
        if (operation.equals("inorder")) {
            InitFuncsNoArgs(operation);
            long start = System.nanoTime();
            this.inOrder.get();
            long end = System.nanoTime();
            runtime = end - start / 1000000;
            return runtime;
        }
        return runtime = 0;
    }

    public long TreeConstructionRuntime(String[] values) {
        long runtime;

        InitFuncsWithArgs(values[0], "insert");

        long start = System.nanoTime();

        for (int i = 1; i < values.length; i++) {
            this.Insert.accept(values[i]);
        }
        long end = System.nanoTime();

        runtime = end - start / 1000000;
        return runtime;
    }
}

