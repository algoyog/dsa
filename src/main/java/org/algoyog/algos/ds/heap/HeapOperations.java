package org.algoyog.algos.ds.heap;

import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Heap Operations showcase with PriorityQueue
 * Java's PriorityQueue is implemented as a Min Heap by default
 * For Max Heap functionality, we can use PriorityQueue with reversed comparator
 */
public class HeapOperations {

    /**
     * K Largest Elements in an Array
     * Time Complexity: O(n log k) - We push all elements to heap of max size k
     * Space Complexity: O(k) for the heap
     */
    public List<Integer> findKLargestElements(int[] nums, int k) {
        // Using a Min Heap to find K largest elements
        // We'll maintain a heap of size k with the smallest element at the top
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Convert the heap to a list (not in sorted order)
        List<Integer> result = new ArrayList<>(minHeap);
        return result;
    }

    /**
     * Merge K Sorted Lists
     * This is a conceptual implementation - in a real interview, you'd use a ListNode class
     * Time Complexity: O(N log k) where N is the total number of elements and k is the number of lists
     * Space Complexity: O(k) for the heap
     */
    public void mergeKSortedLists() {
        System.out.println("Merge K Sorted Lists Implementation (conceptual):");
        System.out.println("1. Create a min-heap (PriorityQueue) to store one node from each list");
        System.out.println("2. Initialize the heap with the first node from each list");
        System.out.println("3. While the heap is not empty:");
        System.out.println("   a. Extract the minimum element (this is the next element in merged result)");
        System.out.println("   b. Add the node's next element to the heap (if exists)");
        System.out.println("4. Time Complexity: O(N log k) where N is total number of nodes and k is number of lists");
    }

    /**
     * Find Median from Data Stream
     * Time Complexity: O(log n) for insertions, O(1) for finding median
     * Space Complexity: O(n) for storing all elements
     */
    public static class MedianFinder {
        // Max heap for the lower half of numbers
        private PriorityQueue<Integer> maxHeap;
        // Min heap for the upper half of numbers
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            // Creating a max heap (using reversed order)
            this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
            // Min heap is default in Java
            this.minHeap = new PriorityQueue<>();
        }

        /**
         * Adds a number to the data structure
         * Time Complexity: O(log n)
         */
        public void addNum(int num) {
            // First, add to the appropriate heap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance the heaps
            // Ensure maxHeap has n/2 or n/2+1 elements (for odd n)
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        /**
         * Returns the median of all numbers seen so far
         * Time Complexity: O(1)
         */
        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                // Even number of elements
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                // Odd number of elements, maxHeap has one more
                return maxHeap.peek();
            }
        }
    }

    /**
     * Kth Smallest Element in a Sorted Matrix
     * Time Complexity: O(min(k, n) + k log(min(k, n)))
     * Space Complexity: O(min(k, n))
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Create a min heap with entries [value, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add the first element from each row to the heap
        for (int r = 0; r < Math.min(n, k); r++) {
            minHeap.offer(new int[]{matrix[r][0], r, 0});
        }

        // Extract kth smallest element
        int count = 0;
        int result = 0;

        while (!minHeap.isEmpty() && count < k) {
            int[] current = minHeap.poll();
            result = current[0];
            count++;

            int r = current[1];
            int c = current[2];

            // Add the next element from the same row if exists
            if (c + 1 < n) {
                minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
            }
        }

        return result;
    }
}
