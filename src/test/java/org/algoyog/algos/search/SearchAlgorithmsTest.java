package org.algoyog.algos.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchAlgorithmsTest {

    private SearchAlgorithms searcher;

    @BeforeEach
    public void setUp() {
        searcher = new SearchAlgorithms();
    }

    @Test
    public void testLinearSearch() {
        // Test with array containing the target
        int[] arr1 = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.linearSearch(arr1, 5));
        assertEquals(0, searcher.linearSearch(arr1, 1));
        assertEquals(4, searcher.linearSearch(arr1, 9));

        // Test with array not containing the target
        assertEquals(-1, searcher.linearSearch(arr1, 4));
        assertEquals(-1, searcher.linearSearch(arr1, 10));

        // Test with empty array
        int[] arr2 = {};
        assertEquals(-1, searcher.linearSearch(arr2, 5));
    }

    @Test
    public void testBinarySearch() {
        // Test with sorted array containing the target
        int[] arr1 = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.binarySearch(arr1, 5));
        assertEquals(0, searcher.binarySearch(arr1, 1));
        assertEquals(4, searcher.binarySearch(arr1, 9));

        // Test with sorted array not containing the target
        assertEquals(-1, searcher.binarySearch(arr1, 4));
        assertEquals(-1, searcher.binarySearch(arr1, 10));

        // Test with empty array
        int[] arr2 = {};
        assertEquals(-1, searcher.binarySearch(arr2, 5));

        // Test with array of one element
        int[] arr3 = {5};
        assertEquals(0, searcher.binarySearch(arr3, 5));
        assertEquals(-1, searcher.binarySearch(arr3, 1));
    }

    @Test
    public void testBinarySearchRecursive() {
        // Test with sorted array containing the target
        int[] arr1 = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.binarySearchRecursive(arr1, 5));
        assertEquals(0, searcher.binarySearchRecursive(arr1, 1));
        assertEquals(4, searcher.binarySearchRecursive(arr1, 9));

        // Test with sorted array not containing the target
        assertEquals(-1, searcher.binarySearchRecursive(arr1, 4));
        assertEquals(-1, searcher.binarySearchRecursive(arr1, 10));

        // Test with empty array
        int[] arr2 = {};
        assertEquals(-1, searcher.binarySearchRecursive(arr2, 5));

        // Test with array of one element
        int[] arr3 = {5};
        assertEquals(0, searcher.binarySearchRecursive(arr3, 5));
        assertEquals(-1, searcher.binarySearchRecursive(arr3, 1));
    }

    @Test
    public void testBinarySearchFirstOccurrence() {
        // Test with array containing one occurrence of target
        int[] arr1 = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.binarySearchFirstOccurrence(arr1, 5));

        // Test with array containing multiple occurrences of target
        int[] arr2 = {1, 3, 5, 5, 5, 7, 9};
        assertEquals(2, searcher.binarySearchFirstOccurrence(arr2, 5));

        // Test with array not containing the target
        assertEquals(-1, searcher.binarySearchFirstOccurrence(arr1, 4));

        // Test with array where target is at the beginning
        int[] arr3 = {5, 5, 5, 7, 9};
        assertEquals(0, searcher.binarySearchFirstOccurrence(arr3, 5));

        // Test with array where target is at the end
        int[] arr4 = {1, 3, 5, 5, 5};
        assertEquals(2, searcher.binarySearchFirstOccurrence(arr4, 5));
    }

    @Test
    public void testBinarySearchLastOccurrence() {
        // Test with array containing one occurrence of target
        int[] arr1 = {1, 3, 5, 7, 9};
        assertEquals(2, searcher.binarySearchLastOccurrence(arr1, 5));

        // Test with array containing multiple occurrences of target
        int[] arr2 = {1, 3, 5, 5, 5, 7, 9};
        assertEquals(4, searcher.binarySearchLastOccurrence(arr2, 5));

        // Test with array not containing the target
        assertEquals(-1, searcher.binarySearchLastOccurrence(arr1, 4));

        // Test with array where target is at the beginning
        int[] arr3 = {5, 5, 5, 7, 9};
        assertEquals(2, searcher.binarySearchLastOccurrence(arr3, 5));

        // Test with array where target is at the end
        int[] arr4 = {1, 3, 5, 5, 5};
        assertEquals(4, searcher.binarySearchLastOccurrence(arr4, 5));
    }

    @Test
    public void testSquareRoot() {
        // Test with perfect squares
        assertEquals(2.0, searcher.squareRoot(4, 0.00001), 0.00001);
        assertEquals(3.0, searcher.squareRoot(9, 0.00001), 0.00001);
        assertEquals(10.0, searcher.squareRoot(100, 0.00001), 0.00001);

        // Test with non-perfect squares
        assertEquals(1.414213, searcher.squareRoot(2, 0.00001), 0.00001);
        assertEquals(1.732050, searcher.squareRoot(3, 0.00001), 0.00001);
        assertEquals(2.236067, searcher.squareRoot(5, 0.00001), 0.00001);

        // Test with 0 and 1
        assertEquals(0.0, searcher.squareRoot(0, 0.00001), 0.00001);
        assertEquals(1.0, searcher.squareRoot(1, 0.00001), 0.00001);

        // Test with negative number (should return -1)
        assertEquals(-1.0, searcher.squareRoot(-4, 0.00001), 0.00001);
    }

    @Test
    public void testSearchInRotatedSortedArray() {
        // Test with rotated array containing the target
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(0, searcher.searchInRotatedSortedArray(arr1, 4));
        assertEquals(3, searcher.searchInRotatedSortedArray(arr1, 7));
        assertEquals(4, searcher.searchInRotatedSortedArray(arr1, 0));
        assertEquals(6, searcher.searchInRotatedSortedArray(arr1, 2));

        // Test with rotated array not containing the target
        assertEquals(-1, searcher.searchInRotatedSortedArray(arr1, 3));
        assertEquals(-1, searcher.searchInRotatedSortedArray(arr1, 8));

        // Test with non-rotated array
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        assertEquals(0, searcher.searchInRotatedSortedArray(arr2, 1));
        assertEquals(6, searcher.searchInRotatedSortedArray(arr2, 7));

        // Test with empty array
        int[] arr3 = {};
        assertEquals(-1, searcher.searchInRotatedSortedArray(arr3, 1));

        // Test with array of one element
        int[] arr4 = {1};
        assertEquals(0, searcher.searchInRotatedSortedArray(arr4, 1));
        assertEquals(-1, searcher.searchInRotatedSortedArray(arr4, 2));
    }

    @Test
    public void testFindPeakElement() {
        // Test with array having one peak
        int[] arr1 = {1, 2, 3, 1};
        int peak1 = searcher.findPeakElement(arr1);
        assertEquals(2, peak1);
        assertTrue(arr1[peak1] > arr1[peak1 - 1] && (peak1 == arr1.length - 1 || arr1[peak1] > arr1[peak1 + 1]));

        // Test with array having multiple peaks (should return any one)
        int[] arr2 = {1, 3, 2, 4, 1};
        int peak2 = searcher.findPeakElement(arr2);
        assertTrue(peak2 == 1 || peak2 == 3);
        assertTrue(arr2[peak2] > (peak2 == 0 ? Integer.MIN_VALUE : arr2[peak2 - 1]) && 
                   arr2[peak2] > (peak2 == arr2.length - 1 ? Integer.MIN_VALUE : arr2[peak2 + 1]));

        // Test with strictly increasing array (peak is at the end)
        int[] arr3 = {1, 2, 3, 4, 5};
        assertEquals(4, searcher.findPeakElement(arr3));

        // Test with strictly decreasing array (peak is at the beginning)
        int[] arr4 = {5, 4, 3, 2, 1};
        assertEquals(0, searcher.findPeakElement(arr4));

        // Test with array of one element (peak is the only element)
        int[] arr5 = {1};
        assertEquals(0, searcher.findPeakElement(arr5));
    }
}
