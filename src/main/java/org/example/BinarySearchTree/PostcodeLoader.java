package org.example.BinarySearchTree;


import java.io.*;

// Algorithm
// 1. Open the given file path for reading using BufferedReader.
// 2. Read each line of the file one by one.
// 3. For each line, insert the postcode into the binary search tree.
// 4. After all lines are processed, close the file.
// 5. If an error occurs (e.g., file not found), catch the exception and display an error message.


public class PostcodeLoader {
    public static void loadFromFile(String filename, BSTree tree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                tree.insert(line);
            }
            System.out.println("Loaded postcodes from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

