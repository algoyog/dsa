# Binary Search

## Problem Statement

Binary Search is an efficient algorithm for finding a target value within a sorted array. Given a sorted array of elements and a target value, binary search determines whether the target exists in the array and returns its position.

## Algorithm Strategy

Binary Search employs a divide-and-conquer approach, repeatedly dividing the search space in half:

1. Begin with the entire array as the initial search space
2. Compare the target value with the middle element of the array
3. If the target matches the middle element, the search is complete
4. If the target is less than the middle element, narrow the search to the lower half
5. If the target is greater than the middle element, narrow the search to the upper half
6. Repeat steps 2-5 until the target is found or the search space is empty

This process continuously halves the search space, allowing binary search to quickly eliminate large portions of the array with each comparison.

## Mathematical Foundation

The key mathematical insight behind binary search is the logarithmic reduction of the search space:

- With each comparison, the algorithm eliminates approximately half of the remaining elements
- For an array of size n, the maximum number of comparisons needed is ⌊log₂(n)⌋ + 1
- This logarithmic behavior is what gives binary search its efficiency

The recurrence relation for binary search can be expressed as:

T(n) = T(n/2) + O(1)

Solving this recurrence relation yields a time complexity of O(log n).

## Implementation Guide

Binary Search can be implemented both iteratively and recursively:

### Iterative Approach

```java
public int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2; // Avoid overflow

        if (arr[mid] == target) {
            return mid;  // Target found
        } else if (arr[mid] < target) {
            left = mid + 1;  // Search right half
        } else {
            right = mid - 1;  // Search left half
        }
    }

    return -1;  // Target not found
}
```

### Recursive Approach

```java
public int binarySearchRecursive(int[] arr, int target) {
    return binarySearchHelper(arr, target, 0, arr.length - 1);
}

private int binarySearchHelper(int[] arr, int target, int left, int right) {
    if (left > right) return -1;  // Base case: not found

    int mid = left + (right - left) / 2;

    if (arr[mid] == target) {
        return mid;  // Target found
    } else if (arr[mid] < target) {
        return binarySearchHelper(arr, target, mid + 1, right);  // Search right
    } else {
        return binarySearchHelper(arr, target, left, mid - 1);  // Search left
    }
}
```

## Testing Theory

Effective testing for binary search should cover:

1. **Basic scenarios**:
   - Target found in the middle of array
   - Target found at the beginning
   - Target found at the end

2. **Edge cases**:
   - Empty array
   - Single-element array
   - Target not in array
   - Duplicates in array

3. **Boundary conditions**:
   - Search near array boundaries
   - Very large arrays
   - Arrays with repeated elements

## Unique Properties

- **Early termination**: Binary search can terminate early when the target is found
- **Middle calculation**: Using `mid = left + (right - left) / 2` prevents integer overflow
- **Requires sorted data**: Binary search only works on sorted arrays
- **Variants**: Many specialized variants exist for finding insertion points, ranges, etc.

## When to Use Binary Search

Binary search is ideal when:

- The data is already sorted or the cost of sorting is justified
- Random access to elements is available (e.g., arrays)
- The dataset is too large for linear search
- You need to repeatedly search the same dataset
- Memory usage needs to be minimal

## Real-world Applications

1. **Database Systems**: Indexes in databases often use binary search tree variants

2. **Search Engines**: For quickly finding terms in sorted dictionaries

3. **Machine Learning**: In algorithms like bisection method for finding roots

4. **Network Routing**: For efficient lookup in routing tables

5. **Computational Biology**: For searching in genomic data

6. **Computer Graphics**: In ray tracing algorithms for intersection testing

7. **Version Control**: Git uses binary search to efficiently find commits

8. **Debug Tools**: Binary search debugging to find the source of a regression

## Variations

Binary Search has several important variations:

1. **First Occurrence**: Find the first occurrence of a target in a sorted array with duplicates
2. **Last Occurrence**: Find the last occurrence of a target
3. **Bounds Finding**: Determine lower and upper bounds for a value
4. **Rotated Arrays**: Binary search adapted for rotated sorted arrays
5. **Search Answer Space**: Using binary search on a range of possible answers

## Complexity Analysis

- **Time Complexity**: O(log n) - Each step eliminates half the remaining elements
- **Space Complexity**: O(1) for iterative, O(log n) for recursive implementation due to call stack
- **Best Case**: O(1) if the middle element is the target
- **Worst Case**: O(log n) when the target is not present or at the extremes

## Common Pitfalls

1. Using binary search on an unsorted array
2. Incorrect boundary conditions (using < vs <=)
3. Integer overflow in the mid-point calculation
4. Off-by-one errors in the recursion or loop conditions
5. Not handling duplicates correctly when finding first/last occurrences
