package org.algoyog.algos.ds.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class HeapOperationsTest {

    private HeapOperations heapOps;

    @BeforeEach
    public void setUp() {
        heapOps = new HeapOperations();
    }

    @Test
    public void testFindKLargestElements() {
        // Test with random array
        int[] nums1 = {3, 1, 5, 12, 2, 11};
        List<Integer> result1 = heapOps.findKLargestElements(nums1, 3);
        assertEquals(3, result1.size());
        assertTrue(result1.containsAll(Arrays.asList(5, 11, 12)));

        // Test with array where all elements are the same
        int[] nums2 = {5, 5, 5, 5, 5};
        List<Integer> result2 = heapOps.findKLargestElements(nums2, 2);
        assertEquals(2, result2.size());
        assertTrue(result2.containsAll(Arrays.asList(5, 5)));

        // Test with k = array length
        int[] nums3 = {1, 2, 3, 4, 5};
        List<Integer> result3 = heapOps.findKLargestElements(nums3, 5);
        assertEquals(5, result3.size());
        assertTrue(result3.containsAll(Arrays.asList(1, 2, 3, 4, 5)));

        // Test with k > array length
        int[] nums4 = {1, 2, 3};
        List<Integer> result4 = heapOps.findKLargestElements(nums4, 5);
        assertEquals(3, result4.size());
        assertTrue(result4.containsAll(Arrays.asList(1, 2, 3)));

        // Test with empty array
        int[] nums5 = {};
        List<Integer> result5 = heapOps.findKLargestElements(nums5, 3);
        assertTrue(result5.isEmpty());
    }

    @Test
    public void testMedianFinder() {
        HeapOperations.MedianFinder medianFinder = new HeapOperations.MedianFinder();

        // Test with one element
        medianFinder.addNum(1);
        assertEquals(1.0, medianFinder.findMedian(), 0.00001);

        // Test with two elements
        medianFinder.addNum(2);
        assertEquals(1.5, medianFinder.findMedian(), 0.00001);

        // Test with three elements
        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian(), 0.00001);

        // Test with four elements
        medianFinder.addNum(4);
        assertEquals(2.5, medianFinder.findMedian(), 0.00001);

        // Test with added elements in random order
        HeapOperations.MedianFinder medianFinder2 = new HeapOperations.MedianFinder();
        medianFinder2.addNum(5);
        assertEquals(5.0, medianFinder2.findMedian(), 0.00001);

        medianFinder2.addNum(2);
        assertEquals(3.5, medianFinder2.findMedian(), 0.00001);

        medianFinder2.addNum(7);
        assertEquals(5.0, medianFinder2.findMedian(), 0.00001);

        medianFinder2.addNum(1);
        assertEquals(3.5, medianFinder2.findMedian(), 0.00001);

        medianFinder2.addNum(9);
        assertEquals(5.0, medianFinder2.findMedian(), 0.00001);
    }

    @Test
    public void testKthSmallest() {
        // Test with simple matrix
        int[][] matrix1 = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };
        assertEquals(1, heapOps.kthSmallest(matrix1, 1));
        assertEquals(5, heapOps.kthSmallest(matrix1, 2));
        assertEquals(9, heapOps.kthSmallest(matrix1, 3));
        assertEquals(10, heapOps.kthSmallest(matrix1, 4));
        assertEquals(15, heapOps.kthSmallest(matrix1, 9));

        // Test with single element matrix
        int[][] matrix2 = {{5}};
        assertEquals(5, heapOps.kthSmallest(matrix2, 1));

        // Test with 2x2 matrix
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        assertEquals(1, heapOps.kthSmallest(matrix3, 1));
        assertEquals(2, heapOps.kthSmallest(matrix3, 2));
        assertEquals(3, heapOps.kthSmallest(matrix3, 3));
        assertEquals(4, heapOps.kthSmallest(matrix3, 4));
    }
}
