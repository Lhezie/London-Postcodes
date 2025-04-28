package org.example.Util;

public interface PostcodeTreeInterface {

//    to insert postcode
    void insert(String postcode);
//    to delete a postcode
    boolean delete(String postcode);

//    to search for a postcode
    boolean search(String postcode);

//    to return all sorted postcode in-order
    String[] inOrder();

//    return total number of postcode
    int count();
}
