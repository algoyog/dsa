package org.algoyog.algos.algos.sort;

import java.util.Arrays;

/**
 * Implementation of common sorting algorithms
 * Understanding sorting algorithms is crucial for technical interviews
 */
public class SortingAlgorithms {

    /**
     * Merge Sort
     * Divide and conquer algorithm
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Stable: Yes
     */
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // Calculate sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays back into arr[left...right]
        int i = 0, j = 0; // Initial indices of first and second subarrays
        int k = left; // Initial index of merged subarray

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Quick Sort
     * Divide and conquer algorithm
     * Time Complexity: Average O(n log n), Worst O(n²)
     * Space Complexity: O(log n) for recursion stack
     * Stable: No
     */
    public void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);

            // Sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = arr[high];

        // Index of smaller element
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap arr[i+1] and arr[high] (put the pivot in its correct position)
        swap(arr, i + 1, high);

        // Return the position of the pivot
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Heap Sort
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     * Stable: No
     */
    public void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum) to the end
            swap(arr, 0, i);

            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * Counting Sort - For small range of integers
     * Time Complexity: O(n + k) where k is the range of input
     * Space Complexity: O(n + k)
     * Stable: Yes
     */
    public void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        // Find the maximum value in the array
        int max = Arrays.stream(arr).max().getAsInt();

        // Create a count array to store count of each number
        int[] count = new int[max + 1];

        // Store count of each element
        for (int value : arr) {
            count[value]++;
        }

        // Change count[i] so that it contains the position of the element in output array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy the output array to the original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    /**
     * Radix Sort - For numbers
     * Time Complexity: O(d * (n + k)) where d is number of digits and k is the base
     * Space Complexity: O(n + k)
     * Stable: Yes
     */
    public void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        // Find the maximum number to know number of digits
        int max = Arrays.stream(arr).max().getAsInt();

        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 digits

        // Store count of occurrences of current digit
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }

        // Change count[i] so that it contains the position of the digit in output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to the original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Bucket Sort - For uniformly distributed values over a range
     * Time Complexity: Average O(n + k), Worst O(n²) if all elements in one bucket
     * Space Complexity: O(n + k)
     * Stable: Depends on the sorting algorithm used for the buckets
     */
    public void bucketSort(float[] arr) {
        if (arr == null || arr.length <= 1) return;

        int n = arr.length;

        // Create n empty buckets
        @SuppressWarnings("unchecked")
        java.util.ArrayList<Float>[] buckets = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new java.util.ArrayList<>();
        }

        // Put array elements in different buckets
        for (float value : arr) {
            int bucketIndex = (int) (n * value);
            buckets[bucketIndex].add(value);
        }

        // Sort individual buckets
        for (java.util.ArrayList<Float> bucket : buckets) {
            java.util.Collections.sort(bucket);
        }

        // Concatenate all buckets into the original array
        int index = 0;
        for (java.util.ArrayList<Float> bucket : buckets) {
            for (float value : bucket) {
                arr[index++] = value;
            }
        }
    }
}
