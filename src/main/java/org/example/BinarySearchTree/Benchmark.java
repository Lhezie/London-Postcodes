package org.example.BinarySearchTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        // Directly use your BSTree implementation
        BSTree tree = new BSTree();

        // Read all postcodes into a list
        List<String> allPostcodes = loadPostcodes(filename);
        Collections.shuffle(allPostcodes);

        // Prepare three sequences of operations
        List<Runnable> inserts   = new ArrayList<>();
        List<Supplier<Boolean>> searches = new ArrayList<>();
        List<Supplier<Boolean>> removals = new ArrayList<>();

        for (String pc : allPostcodes) {
            inserts.add(() -> tree.insert(pc));
            searches.add(() -> tree.search(pc));
            removals.add(() -> tree.delete(pc));
        }

        // Time each batch
        long tInsert = timeRunnables(inserts);
        long tSearch = timeSuppliers(searches, numLookups);
        long tDelete = timeSuppliers(removals, numDeletes);

        System.out.printf(
                "Insert: %.3f ms, Search: %.3f ms, Delete: %.3f ms%n",
                tInsert  / 1_000_000.0,
                tSearch  / 1_000_000.0,
                tDelete  / 1_000_000.0
        );
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

    private static long timeRunnables(List<Runnable> tasks) {
        long start = System.nanoTime();
        for (Runnable r : tasks) {
            r.run();
        }
        return System.nanoTime() - start;
    }

    private static <T> long timeSuppliers(List<Supplier<T>> ops, int limit) {
        long start = System.nanoTime();
        int runs = Math.min(limit, ops.size());
        for (int i = 0; i < runs; i++) {
            ops.get(i).get();
        }
        return System.nanoTime() - start;
    }
}
