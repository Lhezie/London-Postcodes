package org.example.minheap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumHeapTest {
    @Test
    void insert() {
        MinimumHeap minimumHeap = new MinimumHeap(10);
        String postcode = "NW9 HAA";
        minimumHeap.insert(postcode);
        boolean exists = minimumHeap.search(postcode);
        assertTrue(exists);
    }


}
