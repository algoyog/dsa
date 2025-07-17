# Fibonacci Search

## Problem Statement

Fibonacci Search is a comparison-based searching algorithm that finds the position of a target value within a sorted array. It addresses the need for an efficient search algorithm that:

1. Minimizes the number of comparisons needed
2. Works effectively on sorted arrays
3. Is particularly useful for arrays stored in external storage where accessing elements is expensive
4. Divides the array into unequal parts using Fibonacci numbers

The algorithm is designed to be more efficient than linear search while avoiding some of the computational complexities of binary search in certain contexts.

## Algorithm Strategy

Fibonacci Search divides the array using Fibonacci numbers, following these steps:

1. Find the smallest Fibonacci number greater than or equal to the array length
2. Let this number be F(k), and define the initial search range using F(k-2)
3. Compare the target with the element at index F(k-2)
4. Based on the comparison:
   - If equal, return the index
   - If the target is smaller, search in the front part of the array [0 to F(k-2)-1]
   - If the target is larger, search in the back part of the array [F(k-2)+1 to n-1]
5. Adjust the Fibonacci numbers accordingly for the new search range
6. Repeat until the target is found or the search space is exhausted

The key insight is that Fibonacci Search divides the array into parts whose sizes are consecutive Fibonacci numbers, which provides certain advantages for specific types of storage systems.

## Mathematical Foundation

Fibonacci Search is built on the properties of the Fibonacci sequence:

- The Fibonacci sequence is defined as F(0) = 0, F(1) = 1, and F(n) = F(n-1) + F(n-2) for n > 1
- Fibonacci numbers grow approximately exponentially, with the ratio between consecutive numbers approaching the golden ratio (≈ 1.618)
- The division of the array closely follows the golden ratio division, which is considered optimal in some search contexts

The recurrence relation for Fibonacci Search can be expressed as:

T(n) = T(F(k-2)) + O(1) or T(F(k-1)) + O(1)

Where F(k) is the smallest Fibonacci number greater than or equal to n. This resolves to O(log n) time complexity.

## Implementation Guide

```java
public int fibonacciSearch(int[] arr, int target) {
    int n = arr.length;

    // Initialize Fibonacci numbers
    int fibM2 = 0; // (m-2)'th Fibonacci number
    int fibM1 = 1; // (m-1)'th Fibonacci number
    int fibM = fibM1 + fibM2; // m'th Fibonacci number

    // Find the smallest Fibonacci number >= n
    while (fibM < n) {
        fibM2 = fibM1;
        fibM1 = fibM;
        fibM = fibM1 + fibM2;
    }

    // Marks the eliminated range from front
    int offset = -1;

    // While there are elements to be inspected
    while (fibM > 1) {
        // Check if fibM2 is a valid index
        int i = Math.min(offset + fibM2, n - 1);

        // If target is greater than the value at index i, eliminate the subarray from offset to i
        if (arr[i] < target) {
            fibM = fibM1;
            fibM1 = fibM2;
            fibM2 = fibM - fibM1;
            offset = i;
        }
        // If target is less than the value at index i, eliminate the subarray after i+1
        else if (arr[i] > target) {
            fibM = fibM2;
            fibM1 = fibM1 - fibM2;
            fibM2 = fibM - fibM1;
        }
        // Element found
        else {
            return i;
        }
    }

    // Check for the last element
    if (fibM1 == 1 && arr[offset + 1] == target) {
        return offset + 1;
    }

    return -1; // Element not found
}
```

## Testing Theory

Comprehensive testing for Fibonacci Search should include:

1. **Standard scenarios**:
   - Target present at various positions
   - Target not present in the array

2. **Edge cases**:
   - Empty array
   - Single-element array
   - Target at first or last position
   - Array size exactly matches a Fibonacci number

3. **Performance verification**:
   - Arrays of different sizes
   - Comparison with binary search and other algorithms
   - Testing with simulated external storage access patterns

## Unique Properties

- **Fibonacci division**: Uses Fibonacci numbers to divide the array, which can be advantageous for certain storage systems
- **Fewer multiplications/divisions**: Primarily uses additions and subtractions for calculating indices
- **Uneven partitioning**: Unlike binary search, divides the array into unequal parts
- **Predictable access pattern**: The sequence of accessed elements follows a pattern that can be beneficial for certain storage systems

## When to Use Fibonacci Search

Fibonacci Search is particularly useful when:

- The array is sorted
- The array is stored in external memory or magnetic tape where sequential access is preferred
- Multiplication or division operations are expensive or should be avoided
- Binary search's division by 2 is not optimal for the storage medium
- The access time to array elements is non-uniform (e.g., increases with distance)
- The dataset is large and stored in a system where Fibonacci divisions offer an advantage

## Real-world Applications

1. **Database Systems**: For searching through indexed records stored on disk

2. **External Storage**: When searching through data on storage media with non-uniform access times

3. **Embedded Systems**: In resource-constrained environments where multiplication/division is costly

4. **Computational Geometry**: For certain line-search algorithms

5. **Network Packet Processing**: When searching through sequentially stored packet information

6. **Signal Processing**: For certain types of peak finding in signals

## Complexity Analysis

- **Time Complexity**: O(log n) - Comparable to binary search in asymptotic complexity
- **Space Complexity**: O(1) - Uses only a constant amount of extra space
- **Best Case**: O(1) if the target is found in the first comparison
- **Average Case**: O(log n)
- **Worst Case**: O(log n)

While the asymptotic time complexity is the same as binary search, the constant factors and the actual number of comparisons may differ depending on the array size and target position.

## Comparison with Other Search Algorithms

| Algorithm | Time Complexity | Space Complexity | Advantages | Disadvantages |
|-----------|-----------------|-------------------|------------|---------------|
| Fibonacci Search | O(log n) | O(1) | No multiplication/division, good for external storage | More complex implementation |
| Binary Search | O(log n) | O(1) | Simpler implementation, consistent division | Uses division, may not be optimal for all storage types |
| Interpolation Search | O(log log n) average | O(1) | Very fast for uniform distribution | Complex, requires distribution knowledge |
| Jump Search | O(√n) | O(1) | Simpler than Fibonacci/binary search | Less efficient for large arrays |

## Implementation Considerations

1. **Boundary Handling**: Careful management of array indices to prevent out-of-bounds access

2. **Fibonacci Calculation**: Efficient generation of Fibonacci numbers needed for the search

3. **Offset Management**: Properly tracking the offset as the search range shifts

4. **Equality Check**: Special handling for the case when only one element remains

5. **Empty Array Handling**: Properly handling the case of an empty input array

## Optimizations

1. **Precomputed Fibonacci Numbers**: For frequently searched arrays of known size, precomputing the necessary Fibonacci numbers

2. **Early Termination**: Adding checks to exit early when the target is found during initialization

3. **Hybrid Approach**: Combining with other search algorithms for certain ranges or conditions

## Historical Context

Fibonacci Search was developed to optimize searching on systems where accessing memory locations had different costs depending on how far they were from the current position (like magnetic tapes or early disk systems). The algorithm minimizes the expected access cost by using Fibonacci numbers to determine search points.

Today, while modern storage systems have more uniform access patterns, Fibonacci Search remains relevant for specialized applications and as an interesting algorithmic technique that demonstrates how mathematical sequences can be applied to computer science problems.
