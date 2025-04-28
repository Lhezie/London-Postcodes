package org.example.BinarySearchTree;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
import java.util.InputMismatchException;




//my algorithm
// Display a menu with options.
// Read user choice.
// If inserting, prompt for postcode and insert into tree.
// If searching, prompt for postcode and search in tree.
//  If deleting, prompt for postcode and delete from tree.
//  If displaying, print postcodes in in-order traversal.
//  If counting, show the total number of postcodes.
//  If loading from file, read postcodes and insert them.
//  If benchmarking, run timed insert, search, and delete operations.
//  Exit the program if the user chooses to exit.


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BSTree tree = new BSTree();

        while (true) {
            System.out.println("\nPostcode BST Menu");
            System.out.println("1. Load postcodes from file");
            System.out.println("2. Insert a postcode");
            System.out.println("3. Search for a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Display all postcodes (in-order)");
            System.out.println("6. Count total postcodes");
            System.out.println("7. Benchmark using file");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        try {
                            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception e) {
                            // Ignore theme errors
                        }
                        JFileChooser chooser = new JFileChooser();
                        chooser.setDialogTitle("Select a postcode file to load");
                        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

                        int result = chooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String filename = chooser.getSelectedFile().getAbsolutePath();
                            PostcodeLoader.loadFromFile(filename, tree);
                        } else {
                            System.out.println("File loading cancelled.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter postcode to insert: ");
                        String postcode = scanner.nextLine();
                        tree.insert(postcode);
                    }
                    case 3 -> {
                        System.out.print("Enter postcode to search: ");
                        String postcode = scanner.nextLine();
                        boolean found = tree.search(postcode);
                        System.out.println(found ? "Postcode Found" : "Postcode Not Found");
                    }
                    case 4 -> {
                        System.out.print("Enter postcode to delete: ");
                        String postcode = scanner.nextLine();
                        boolean deleted = tree.delete(postcode);
                        System.out.println(deleted ? "Postcode Deleted" : "Postcode Not Found");
                    }
                    case 5 -> {
                        String[] postcodes = tree.inOrder();
                        System.out.println("\nPostcodes displayed in order:");
                        for (String pc : postcodes) {
                            System.out.println(pc);
                        }
                    }
                    case 6 -> System.out.println("Total postcodes: " + tree.count());
                    case 7 -> {
                        try {
                            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception e) {
                            // Ignore theme errors
                        }
                        JFileChooser chooser = new JFileChooser();
                        chooser.setDialogTitle("Select a postcode file for benchmarking");
                        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

                        int result = chooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String filename = chooser.getSelectedFile().getAbsolutePath();
                            Benchmark.runBenchmark(filename, 100, 100);
                        } else {
                            System.out.println("Benchmarking cancelled.");
                        }
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 8.");
                scanner.nextLine(); // Clear wrong input
            }
        }
    }
}





