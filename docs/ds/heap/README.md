# Heap Data Structure

## Overview

A heap is a specialized tree-based data structure that satisfies the heap property. In a max heap, for any given node, the value of the node is greater than or equal to the values of its children. In a min heap, the value of the node is less than or equal to the values of its children.

## Key Characteristics

- Complete binary tree (all levels are fully filled except possibly the last level, which is filled from left to right)
- Satisfies the heap property (max heap or min heap)
- Efficiently supports basic priority queue operations
- Can be efficiently implemented using an array
- Height of a heap with n elements is ⌊log₂(n)⌋

## Operations

| Operation | Time Complexity | Description |
|-----------|-----------------|-------------|
| Insert    | O(log n)        | Add a new element to the heap |
| Extract Min/Max | O(log n) | Remove and return the minimum (min heap) or maximum (max heap) element |
| Peek     | O(1)            | View the minimum/maximum element without removing it |
| Heapify  | O(n)            | Convert an array into a valid heap |
| Delete   | O(log n)        | Remove a specific element from the heap |
| Decrease Key | O(log n)     | Decrease the value of an element (min heap) |
| Increase Key | O(log n)     | Increase the value of an element (max heap) |
| Merge    | O(n)            | Combine two heaps into one |

## Array Representation

A binary heap is commonly represented as an array where, for any element at index i:
- The parent element is at index ⌊(i-1)/2⌋
- The left child is at index 2i + 1
- The right child is at index 2i + 2

## Implementation

In Java, the `PriorityQueue` class implements a min heap. Here's how you might implement a simple max heap:

```java
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return (2 * i) + 1;
    }

    private int rightChild(int i) {
        return (2 * i) + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int key) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }

        // Insert new key at the end
        size++;
        int i = size - 1;
        heap[i] = key;

        // Fix the max heap property if it's violated
        while (i != 0 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extractMax() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }

        // Store the maximum value and remove it
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;

        // Restore the heap property
        heapify(0);

        return max;
    }

    private void heapify(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        // See if left child is larger than root
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // See if right child is larger than largest so far
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }
}
```

## Applications

- **Priority Queues**: Efficient implementation of priority queues
- **Heap Sort**: Sorting algorithm with O(n log n) time complexity
- **Graph Algorithms**: Dijkstra's shortest path, Prim's minimum spanning tree
- **K-th Largest Element**: Finding kth smallest/largest element in an array
- **Median Maintenance**: Finding median in a stream of integers
- **Merge K Sorted Lists**: Efficiently merge multiple sorted arrays/lists

## Java's Built-in Implementation

Java provides the `PriorityQueue` class, which is an implementation of a min heap:

```java
// Min heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

## Considerations and Limitations

- Not suitable for searching for arbitrary elements (O(n) time)
- Not as cache-friendly as arrays due to indirect access patterns
- Doesn't support efficient merging of two heaps (without rebuilding)
- Binary heap is not the only heap implementation (Fibonacci heap, binomial heap offer different trade-offs)

## When to Use Heaps

- When you need to repeatedly find the minimum or maximum element
- When you need to efficiently handle inserting elements and extracting the min/max
- For implementing efficient priority-based algorithms
- When implementing adaptive sorting algorithms

Heaps strike a balance between the fast insertion of unsorted arrays and the fast searching of sorted arrays, making them ideal for scenarios where both operations are frequently performed.
