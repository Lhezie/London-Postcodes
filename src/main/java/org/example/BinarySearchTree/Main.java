package org.example.BinarySearchTree;
import java.util.Scanner;

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

//       Use TreeFactory to create the BSTree
        BSTree tree = new BSTree();

        while (true) {
            System.out.println("\nPostcode BST Menu");
            System.out.println("1. Insert a postcode: ");
            System.out.println("2. Search for a postcode: ");
            System.out.println("3. Delete a postcode: ");
            System.out.println("4. Display all postcodes (inorder)");
            System.out.println("5. Count total postcodes ");
            System.out.println("6. Load postcodes from file");
            System.out.println("7. Benchmark using file");
            System.out.println("8. Exit: ");
            System.out.println("Choose an option: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Enter postcode to insert: ");
                    String postcode = scanner.nextLine();
                    tree.insert(postcode);
                }
                case 2: {
                    System.out.print("Enter postcode to search: ");
                    String postcode = scanner.nextLine();
                    boolean found = tree.search(postcode);
                    System.out.println(found ? "Postcode Found" : "Postcode Not found");
                }
                case 3: {
                    System.out.print("Enter postcode to delete: ");
                    String postcode = scanner.nextLine();
                    boolean deleted = tree.delete(postcode);
                    System.out.println(deleted ? "Postcode Deleted" : "Postcode Not deleted");
                }
                case 4: {
                    String[] postcode = tree.inOrder();
                    System.out.println("\nPostcode displayed in order: ");
                    for (String pc: postcode) {
                        System.out.println(pc);
                    }
                }
                case 5: {
                    System.out.println(" Total postcodes:" + tree.count() );

                }
                case 6: {
                    System.out.println(" Enter file path to load postcodes: ");
                    String filename = scanner.nextLine();
                    PostcodeLoader.loadFromFile(filename, tree);
                }
                case 7: {
                    System.out.println("Enter file path for benchmarking: ");
                    String filename = scanner.nextLine();
                    Benchmark.runBenchmark(filename, 100, 100);
                }
                case 8: {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default: System.out.println("Invalid option: Please try again");
            }

        }
    }
}



