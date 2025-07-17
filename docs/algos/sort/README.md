# Sorting Algorithms
# Sorting Algorithms

## Overview

Sorting algorithms are fundamental in computer science and are used to rearrange elements in a specific order (typically ascending or descending). This section covers a comprehensive set of sorting algorithms, each with unique properties, advantages, and use cases.

## Algorithms Covered

1. [Merge Sort](merge-sort.md) - Divide and conquer, stable, O(n log n)
2. [Quick Sort](quick-sort.md) - Divide and conquer, in-place, typically O(n log n)
3. [Heap Sort](heap-sort.md) - In-place, guaranteed O(n log n)
4. [Counting Sort](counting-sort.md) - Non-comparison based, O(n + k)
5. [Radix Sort](radix-sort.md) - Digit-by-digit sorting, O(d * (n + k))
6. [Bucket Sort](bucket-sort.md) - Distribution-based, O(n + k) for uniform distribution

## Comparative Analysis

| Algorithm    | Best       | Average    | Worst      | Space      | Stable | In-Place | Adaptive | Notes                                  |
|--------------|------------|------------|------------|------------|--------|----------|----------|----------------------------------------|
| Merge Sort   | O(n log n) | O(n log n) | O(n log n) | O(n)       | Yes    | No       | No       | Consistent performance, external sort  |
| Quick Sort   | O(n log n) | O(n log n) | O(n²)      | O(log n)   | No     | Yes      | No       | Fast in practice, vulnerable to pathological inputs |
| Heap Sort    | O(n log n) | O(n log n) | O(n log n) | O(1)       | No     | Yes      | No       | Guaranteed performance, poor cache locality |
| Counting Sort| O(n + k)   | O(n + k)   | O(n + k)   | O(n + k)   | Yes    | No       | No       | Limited to small range integers        |
| Radix Sort   | O(d*(n+k)) | O(d*(n+k)) | O(d*(n+k)) | O(n + k)   | Yes    | No       | No       | Efficient for fixed-length integers    |
| Bucket Sort  | O(n + k)   | O(n + k)   | O(n²)      | O(n + k)   | Yes*   | No       | No       | Best for uniform distributions         |

*Stable if the underlying bucket sorting algorithm is stable

## Key Terminology

- **Stable Sort**: Preserves the relative order of equal elements
- **In-Place Sort**: Uses only a constant amount of extra space
- **Adaptive Sort**: Performance improves for partially sorted input
- **Comparison Sort**: Based on comparing elements
- **Distribution Sort**: Based on distributing elements to buckets

## Selection Guidelines

### When to use Merge Sort
- Need stable sorting
- Guaranteed O(n log n) performance is required
- External sorting with limited memory
- Linked list sorting

### When to use Quick Sort
- General-purpose sorting needs
- In-place sorting is preferred
- Average-case performance is more important than worst-case
- Memory usage is a concern

### When to use Heap Sort
- Guaranteed worst-case performance is critical
- Memory usage is severely constrained
- Stability is not required

### When to use Counting Sort
- Sorting integers with a small range
- Range of elements is known in advance
- Stable sorting is needed

### When to use Radix Sort
- Sorting integers with bounded number of digits
- Sorting strings of equal length
- When stability is required

### When to use Bucket Sort
- Data is uniformly distributed
- Elements can be easily mapped to buckets
- Parallelization can be leveraged

## Implementations

All sorting algorithms in this section include:
- Detailed step-by-step algorithm explanation
- Java implementation with comments
- Time and space complexity analysis
- Practical considerations and optimizations
- Use cases and limitations

## Advanced Topics

- Hybrid sorting algorithms (TimSort, IntroSort)
- Parallel sorting algorithms
- External sorting for large datasets
- Sorting in distributed systems
- Sorting networks
This directory contains documentation and implementations of various sorting algorithms, each with its own characteristics, advantages, and use cases.

## Algorithms Covered

### Comparison-Based Sorting Algorithms

- [Merge Sort](merge-sort.md) - A stable, divide-and-conquer algorithm with O(n log n) time complexity
- [Quick Sort](quick-sort.md) - A fast, in-place sorting algorithm with average O(n log n) time complexity
- [Heap Sort](heap-sort.md) - An in-place algorithm with guaranteed O(n log n) time complexity

### Non-Comparison Sorting Algorithms

- [Counting Sort](counting-sort.md) - For sorting integers within a small range with O(n + k) time complexity
- [Radix Sort](radix-sort.md) - For sorting integers by processing individual digits
- [Bucket Sort](bucket-sort.md) - For sorting uniformly distributed values with potential O(n) time complexity

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
