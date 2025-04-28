package org.example.BinarySearchTree;

import java.io.*;
import java.util.*;

// Algorithm:
// 1. Open the given postcode file and read all postcodes into a list.
// 2. Shuffle the list randomly to simulate real-world data insertion.
// 3. Measure and record the time to insert all postcodes into the tree.
// 4. Measure and record the time to search for a sample of postcodes.
// 5. Measure and record the time to delete a sample of postcodes.
// 6. Print the results showing how long each operation took.

public class Benchmark {
    public static void runBenchmark(String filename, int numLookups, int numDeletes) {
        BSTree tree = TreeFactory.createTree("BST");

        List<String> allPostcodes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                allPostcodes.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        Collections.shuffle(allPostcodes);

        long startInsert = System.nanoTime();
        for (String postcode : allPostcodes) {
            tree.insert(postcode);
        }
        long endInsert = System.nanoTime();

        long startSearch = System.nanoTime();
        for (int j = 0; j < Math.min(numLookups, allPostcodes.size()); j++) {
            tree.search(allPostcodes.get(j));
        }
        long endSearch = System.nanoTime();

        long startDelete = System.nanoTime();
        for (int k = 0; k < Math.min(numDeletes, allPostcodes.size()); k++) {
            tree.delete(allPostcodes.get(k));
        }
        long endDelete = System.nanoTime();


        System.out.println("\n--- Benchmark Results for " + filename + " ---");
        System.out.printf("Insertion Time: %.3f ms\n", (endInsert - startInsert) / 1_000_000.0);
        System.out.printf("Search Time: %.3f ms\n", (endSearch - startSearch) / 1_000_000.0);
        System.out.printf("Deletion Time: %.3f ms\n", (endDelete - startDelete) / 1_000_000.0);
    }
}
