# Sorting Algorithms

This directory contains documentation and implementations of various sorting algorithms, each with its own characteristics, advantages, and use cases.

## Algorithms Covered

### Comparison-Based Sorting Algorithms

- [Merge Sort](./merge-sort.md) - A stable, divide-and-conquer algorithm with O(n log n) time complexity
- [Quick Sort](./quick-sort.md) - A fast, in-place sorting algorithm with average O(n log n) time complexity
- [Heap Sort](./heap-sort.md) - An in-place algorithm with guaranteed O(n log n) time complexity

### Non-Comparison Sorting Algorithms

- [Counting Sort](./counting-sort.md) - For sorting integers within a small range with O(n + k) time complexity
- [Radix Sort](./radix-sort.md) - For sorting integers by processing individual digits
- [Bucket Sort](./bucket-sort.md) - For sorting uniformly distributed values with potential O(n) time complexity

## Comparison Table

| Algorithm | Time Complexity |  | | Space Complexity | Stable | In-Place |
|-----------|----------------|----------------|----------------|------------------|--------|----------|
| | Best | Average | Worst | | | |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | No |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No | Yes |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No | Yes |
| Counting Sort | O(n + k) | O(n + k) | O(n + k) | O(n + k) | Yes | No |
| Radix Sort | O(d * (n + k)) | O(d * (n + k)) | O(d * (n + k)) | O(n + k) | Yes | No |
| Bucket Sort | O(n) | O(n) | O(n²) | O(n + k) | Depends | No |

Where:
- n is the number of elements
- k is the range of elements or number of buckets
- d is the number of digits in the largest number

## Choosing the Right Algorithm

- **Merge Sort**: When stability is important, or for external sorting with limited memory
- **Quick Sort**: For general-purpose internal sorting when average performance matters most
- **Heap Sort**: When guaranteed worst-case performance is required with minimal space
- **Counting Sort**: For sorting integers with a small, known range
- **Radix Sort**: For sorting integers or strings with fixed-length keys
- **Bucket Sort**: For sorting uniformly distributed data efficiently

## Implementation Notes

All algorithms are implemented in Java in the `org.algoyog.algos.sort` package. Each algorithm includes:

- Detailed documentation with time and space complexity analysis
- Handling of edge cases (null arrays, empty arrays, etc.)
- Unit tests covering various scenarios

The implementation prioritizes clarity and correctness over micro-optimizations to serve as educational references.
