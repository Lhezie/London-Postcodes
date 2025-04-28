package org.example.BinarySearchTree;

import org.example.Util.PostcodeTreeInterface;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

// Algorithm:
// 1. Open the given postcode file and read all postcodes into a list.
// 2. Shuffle the list randomly to simulate real-world data insertion.
// 3. Measure and record the time to insert all postcodes into the tree.
// 4. Measure and record the time to search for a sample of postcodes.
// 5. Measure and record the time to delete a sample of postcodes.
// 6. Print the results showing how long each operation took.

public class Benchmark {
    public static void runBenchmark(String filename, int numLookups, int numDeletes) {
        PostcodeTreeInterface tree = TreeFactory.createTree("BST");

        List<String> allPostcodes = loadPostcodes(filename);
        Collections.shuffle(allPostcodes);

        List<Runnable> inserts = new ArrayList<>();
        List<Supplier<Boolean>> searches = new ArrayList<>();
        List<Supplier<Boolean>> deletes = new ArrayList<>();

        for (String postcode : allPostcodes) {
            inserts.add(() -> tree.insert(postcode));
            searches.add(() -> tree.search(postcode));
            deletes.add(() -> tree.delete(postcode));
        }

        long timeInsert = timeRunnables(inserts);
        long timeSearch = timeSuppliers(searches, numLookups);
        long timeDelete = timeSuppliers(deletes, numDeletes);

        System.out.printf("Insert: %.3f ms, Search: %.3f ms, Delete: %.3f ms%n",
                timeInsert/1_000_000.0,
                timeSearch/1_000_000.0,
                timeDelete/1_000_000.0);
    }

    private static <T> long timeSuppliers(List<Supplier<T>> ops, int limit) {
        long start = System.nanoTime();
        for (int i = 0; i < Math.min(limit, ops.size()); i++) {
            ops.get(i).get();
        }
        return System.nanoTime() - start;
    }

    private static long timeRunnables(List<Runnable> tasks) {
        long start = System.nanoTime();
        for (Runnable r : tasks) {
            r.run();
        }
        return System.nanoTime() - start;
    }

    private static List<String> loadPostcodes(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return list;
    }
}
