package org.example.Util;

import java.util.*;

public class PostcodeEncoder {

    HashMap<String, Integer> EncodingTable = new HashMap<String, Integer>();

    PostcodeEncoder() {
        EncodingTable.put("E", 5);
        EncodingTable.put("C", 3);
        EncodingTable.put("N", 14);
        EncodingTable.put("S", 19);
        EncodingTable.put("W", 23);
        EncodingTable.put("B", 2);
        EncodingTable.put("A", 1);
        EncodingTable.put("R", 18);
        EncodingTable.put("D", 4);
        EncodingTable.put("H", 8);
        EncodingTable.put("I", 9);
        EncodingTable.put("G", 7);
        EncodingTable.put("K", 11);
        EncodingTable.put("T", 20);
        EncodingTable.put("M", 13);
        EncodingTable.put("U", 21);
        EncodingTable.put("P", 16);
        EncodingTable.put("L", 12);
        EncodingTable.put("Z", 26);
        EncodingTable.put("Y", 25);
        EncodingTable.put("O", 15);
        EncodingTable.put("Q", 17);
        EncodingTable.put("V", 22);
        EncodingTable.put("X", 24);
        EncodingTable.put("F", 6);
        EncodingTable.put("J", 10);



    }
    /* function simply unifies the array elements generated in the main function into a string that can be converted
        into a number
     */

    private String Flatten (List<Integer> digits) {

        StringBuilder FlattenedPostCode = new StringBuilder();


        for (var num : digits) {

            FlattenedPostCode.append(num);
        }

        return FlattenedPostCode.toString();
    }

    public long EncodePostcode(String postcode){

        // We convert the posctode string into a character array so we can iterate over it
        char[] PostcodeArray = postcode.toCharArray();

        //this will contain each character converted into a number
        List<Integer> EncodedValues = new ArrayList<>();

        //Now we iterate over the individual characters of the postcode
        for(int i = 0; i < PostcodeArray.length; i++) {
            // this if-clause just ignores the space in the postcode
            if (PostcodeArray[i] != ' ') {
                // try block just lets us check if the character is in the hashmap above
                // when it encounters a number it will go to the catch clause below
                try {
                    // retrieves the value from the key in the hashmap that is the same as the character
                    int EncodedValue = EncodingTable.get(String.valueOf(PostcodeArray[i]));
                    /*the value gets added to the list which will be converted into the long returned at the end
                    of the function
                     */
                    EncodedValues.add(EncodedValue);
                    // null pointer exception because there a no numbers as keys in the hashmap
                } catch (NullPointerException e) {
                    /* catches number and shifts up 26 as that is the number of letters in the alphabet
                        so the numbers will always be accounted for after sorting the digits of our postcodes
                     */
                    try {
                        EncodedValues.add(Integer.parseInt(String.valueOf(PostcodeArray[i])) + 26);
                    } catch (NumberFormatException e2) {
                        // in the case that the postcode includes a non-numeric or alphabetic character
                        System.out.println(e2.getMessage() + "Make sure you have a valid postcode format");
                    }
                }
            }
        }
        String EncodedString = this.Flatten(EncodedValues);

        return Long.parseLong(EncodedString);
    }
}
