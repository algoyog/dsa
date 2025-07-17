package org.algoyog.algos.algos.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SortingAlgorithmsTest {

    private SortingAlgorithms sorter;

    @BeforeEach
    public void setUp() {
        sorter = new SortingAlgorithms();
    }

    @Test
    public void testMergeSort() {
        // Test with random array
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        int[] expected1 = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(expected1);

        sorter.mergeSort(arr1);
        assertArrayEquals(expected1, arr1);

        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};

        sorter.mergeSort(arr2);
        assertArrayEquals(expected2, arr2);

        // Test with reverse sorted array
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] expected3 = {1, 2, 3, 4, 5};

        sorter.mergeSort(arr3);
        assertArrayEquals(expected3, arr3);

        // Test with array containing duplicates
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected4 = {1, 1, 2, 3, 4, 5, 5, 6, 9};

        sorter.mergeSort(arr4);
        assertArrayEquals(expected4, arr4);

        // Test with empty array
        int[] arr5 = {};
        int[] expected5 = {};

        sorter.mergeSort(arr5);
        assertArrayEquals(expected5, arr5);

        // Test with null array (should not throw exception)
        sorter.mergeSort(null);
    }

    @Test
    public void testQuickSort() {
        // Test with random array
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        int[] expected1 = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(expected1);

        sorter.quickSort(arr1);
        assertArrayEquals(expected1, arr1);

        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};

        sorter.quickSort(arr2);
        assertArrayEquals(expected2, arr2);

        // Test with reverse sorted array
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] expected3 = {1, 2, 3, 4, 5};

        sorter.quickSort(arr3);
        assertArrayEquals(expected3, arr3);

        // Test with array containing duplicates
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected4 = {1, 1, 2, 3, 4, 5, 5, 6, 9};

        sorter.quickSort(arr4);
        assertArrayEquals(expected4, arr4);

        // Test with empty array
        int[] arr5 = {};
        int[] expected5 = {};

        sorter.quickSort(arr5);
        assertArrayEquals(expected5, arr5);

        // Test with null array (should not throw exception)
        sorter.quickSort(null);
    }

    @Test
    public void testHeapSort() {
        // Test with random array
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        int[] expected1 = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(expected1);

        sorter.heapSort(arr1);
        assertArrayEquals(expected1, arr1);

        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};

        sorter.heapSort(arr2);
        assertArrayEquals(expected2, arr2);

        // Test with reverse sorted array
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] expected3 = {1, 2, 3, 4, 5};

        sorter.heapSort(arr3);
        assertArrayEquals(expected3, arr3);

        // Test with array containing duplicates
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected4 = {1, 1, 2, 3, 4, 5, 5, 6, 9};

        sorter.heapSort(arr4);
        assertArrayEquals(expected4, arr4);
    }

    @Test
    public void testCountingSort() {
        // Test with random array of non-negative integers
        int[] arr1 = {4, 2, 2, 8, 3, 3, 1};
        int[] expected1 = {1, 2, 2, 3, 3, 4, 8};

        sorter.countingSort(arr1);
        assertArrayEquals(expected1, arr1);

        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};

        sorter.countingSort(arr2);
        assertArrayEquals(expected2, arr2);

        // Test with reverse sorted array
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] expected3 = {1, 2, 3, 4, 5};

        sorter.countingSort(arr3);
        assertArrayEquals(expected3, arr3);

        // Test with array containing duplicates
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] expected4 = {1, 1, 2, 3, 4, 5, 5, 6, 9};

        sorter.countingSort(arr4);
        assertArrayEquals(expected4, arr4);

        // Test with empty array
        int[] arr5 = {};
        int[] expected5 = {};

        sorter.countingSort(arr5);
        assertArrayEquals(expected5, arr5);
    }

    @Test
    public void testRadixSort() {
        // Test with random array of positive integers
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        int[] expected1 = {2, 24, 45, 66, 75, 90, 170, 802};

        sorter.radixSort(arr1);
        assertArrayEquals(expected1, arr1);

        // Test with already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};

        sorter.radixSort(arr2);
        assertArrayEquals(expected2, arr2);

        // Test with reverse sorted array
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] expected3 = {1, 2, 3, 4, 5};

        sorter.radixSort(arr3);
        assertArrayEquals(expected3, arr3);

        // Test with array containing duplicates
        int[] arr4 = {53, 11, 43, 11, 57, 92, 24, 64, 57};
        int[] expected4 = {11, 11, 24, 43, 53, 57, 57, 64, 92};

        sorter.radixSort(arr4);
        assertArrayEquals(expected4, arr4);

        // Test with empty array
        int[] arr5 = {};
        int[] expected5 = {};

        sorter.radixSort(arr5);
        assertArrayEquals(expected5, arr5);
    }

    @Test
    public void testBucketSort() {
        // Test with random array of floats between 0 and 1
        float[] arr1 = {0.42f, 0.32f, 0.33f, 0.52f, 0.37f, 0.47f, 0.51f};
        float[] expected1 = {0.32f, 0.33f, 0.37f, 0.42f, 0.47f, 0.51f, 0.52f};

        sorter.bucketSort(arr1);
        assertArrayEquals(expected1, arr1, 0.00001f);

        // Test with already sorted array
        float[] arr2 = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f};
        float[] expected2 = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f};

        sorter.bucketSort(arr2);
        assertArrayEquals(expected2, arr2, 0.00001f);

        // Test with reverse sorted array
        float[] arr3 = {0.5f, 0.4f, 0.3f, 0.2f, 0.1f};
        float[] expected3 = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f};

        sorter.bucketSort(arr3);
        assertArrayEquals(expected3, arr3, 0.00001f);

        // Test with array containing duplicates
        float[] arr4 = {0.23f, 0.11f, 0.43f, 0.11f, 0.57f, 0.02f, 0.23f};
        float[] expected4 = {0.02f, 0.11f, 0.11f, 0.23f, 0.23f, 0.43f, 0.57f};

        sorter.bucketSort(arr4);
        assertArrayEquals(expected4, arr4, 0.00001f);

        // Test with empty array
        float[] arr5 = {};
        float[] expected5 = {};

        sorter.bucketSort(arr5);
        assertArrayEquals(expected5, arr5, 0.00001f);
    }
}
