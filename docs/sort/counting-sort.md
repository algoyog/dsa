# Counting Sort

## Problem Statement

Counting Sort addresses the problem of sorting a collection of elements efficiently when those elements are integers within a specific, limited range. Unlike comparison-based sorting algorithms, Counting Sort uses the values of the elements themselves to determine their positions in the sorted output.

## Algorithm Strategy

Counting Sort follows these steps:

1. **Count Occurrences**: Count the frequency of each distinct element in the input array

2. **Cumulative Count**: Calculate the cumulative sum of these frequencies to determine the position of each element in the sorted output

3. **Build Output Array**: Place each element in its correct position in the output array, working backward through the input to maintain stability

4. **Copy Back**: Transfer the sorted elements from the output array back to the original array (if needed)

## Mathematical Foundation

The mathematical basis for Counting Sort is straightforward:

- For each element x in the input array, calculate its correct position in the sorted output based on the number of elements less than or equal to x
- This calculation gives a direct mapping from input positions to output positions

The time complexity is O(n + k), where n is the number of elements and k is the range of possible values.

## Implementation Guide

```java
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
```

## Testing Theory

A comprehensive test suite for Counting Sort should include:

1. **Standard cases**:
   - Random arrays of non-negative integers
   - Arrays with distinct elements
   - Arrays with duplicate elements

2. **Edge cases**:
   - Empty array
   - Single-element array
   - Already sorted array
   - Reverse-sorted array

3. **Special scenarios**:
   - Array with all identical elements
   - Arrays with large gaps between values
   - Arrays with small range of values but many elements

4. **Property verification**:
   - Stability (equal elements maintain their relative order)
   - Correctness (elements are properly sorted)
   - Time complexity verification

## Unique Properties

- **Non-comparison based**: Does not use element comparisons to determine sort order
- **Linear time complexity**: O(n + k) where k is the range of input values
- **Stable**: Equal elements maintain their relative order
- **Limited applicability**: Works efficiently only for discrete, bounded integer ranges
- **Not in-place**: Requires additional memory proportional to the range of values

## When to Use Counting Sort

Counting Sort is particularly well-suited for:

- Sorting integers with a small range (when k is small)
- Applications where elements are uniformly distributed over a known range
- When stability is required
- As a subroutine in more complex algorithms (like Radix Sort)
- When optimal linear time is needed and space is available

## Real-world Applications

1. **Numeric Data Processing**: For sorting datasets with limited distinct values

2. **Educational Grading Systems**: Sorting test scores within a fixed range

3. **Network Traffic Analysis**: Classifying packets by size or priority levels

4. **Image Processing**: Sorting pixels by intensity values (0-255)

5. **Algorithm Components**: As a subroutine in Radix Sort and Bucket Sort

6. **Data Sanitization**: Sorting and identifying frequency patterns

## Complexity Analysis

- **Time Complexity**:
  - Best case: O(n + k)
  - Average case: O(n + k)
  - Worst case: O(n + k)

- **Space Complexity**: O(n + k)

## Optimizations
# Counting Sort

## Problem Statement

Counting Sort is a non-comparison-based sorting algorithm that works efficiently when the range of input values is known and relatively small compared to the number of elements. It counts the occurrences of each distinct element and uses this information to place elements in their correct sorted positions.

## Algorithm Strategy

Counting Sort follows these steps:

1. **Find the range**: Determine the minimum and maximum values in the input array.
2. **Count occurrences**: Count how many times each value appears in the input array.
3. **Calculate cumulative counts**: Transform the count array to represent the position of each element in the output array.
4. **Build output array**: Place each element in its correct position in the output array.
5. **Copy back**: If needed, copy the sorted output back to the original array.

## Mathematical Foundation

Counting Sort operates on the principle of counting occurrences and using this information to determine the exact position of each element in the sorted output. The algorithm is stable, meaning it preserves the relative order of equal elements.

The key insight is that if we know there are x elements less than a given element E, then E should be placed at position x in the sorted array.

## Implementation Details

```java
public void countingSort(int[] arr) {
    if (arr == null || arr.length <= 1) return;

    // Find the range of input values
    int max = arr[0];
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) max = arr[i];
        if (arr[i] < min) min = arr[i];
    }

    int range = max - min + 1;

    // Create counting array and output array
    int[] count = new int[range];
    int[] output = new int[arr.length];

    // Count occurrences of each element
    for (int i = 0; i < arr.length; i++) {
        count[arr[i] - min]++;
    }

    // Calculate cumulative counts
    for (int i = 1; i < range; i++) {
        count[i] += count[i - 1];
    }

    // Build the output array
    for (int i = arr.length - 1; i >= 0; i--) {
        output[count[arr[i] - min] - 1] = arr[i];
        count[arr[i] - min]--;
    }

    // Copy the output array back to the original array
    System.arraycopy(output, 0, arr, 0, arr.length);
}
```

## Testing Methodology

Counting Sort should be tested with various input types:
- Arrays with values in a small range
- Arrays with negative numbers
- Arrays with duplicates
- Arrays with a single element or empty arrays
- Arrays with values close to the minimum or maximum of the data type range

## Unique Properties

- **Non-comparison sort**: Unlike most sorting algorithms, Counting Sort doesn't compare elements directly.
- **Linear time complexity**: O(n + k) where k is the range of input values.
- **Stable sort**: Preserves the relative order of equal elements.
- **Not in-place**: Requires additional memory proportional to the range of input values.

## Use Cases

1. Sorting integers with a small range (like ages, scores, or small IDs)
2. Sorting characters or strings of fixed length
3. As a subroutine in Radix Sort
4. When stability is required and the value range is limited
5. Applications where the number of distinct values is small compared to the number of elements

## Real-world Applications

- Sorting student exam scores (0-100 range)
- Character frequency analysis in strings
- Sorting postal codes or ZIP codes
- Census data processing for categorical attributes
- Network packet prioritization based on discrete priority levels

## Complexity Analysis

| Case      | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Best      | O(n + k)       | O(n + k)         |
| Average   | O(n + k)       | O(n + k)         |
| Worst     | O(n + k)       | O(n + k)         |

Where:
- n is the number of elements in the input array
- k is the range of input values (max - min + 1)

## Optimizations

1. **Range compression**: If the range is sparse, map the values to a smaller range.
2. **In-place counting**: For certain applications, the counting can be done in-place.
3. **Parallel counting**: The counting phase can be parallelized for large datasets.
4. **Memory optimization**: If the range is known in advance, allocate only the necessary memory.
5. **Combine with other algorithms**: For very large ranges, combine with other algorithms or use a modified approach.

## Comparison with Other Sorting Algorithms

| Algorithm      | Time Complexity | Space Complexity | Stable | Best For                   |
|----------------|----------------|-----------------|--------|----------------------------|
| Counting Sort  | O(n + k)       | O(n + k)        | Yes    | Small range integers       |
| Quick Sort     | O(n log n)     | O(log n)        | No     | General-purpose sorting    |
| Merge Sort     | O(n log n)     | O(n)            | Yes    | Stable, guaranteed performance |
| Radix Sort     | O(d*(n + k))   | O(n + k)        | Yes    | Fixed-length integers     |
| Bucket Sort    | O(n + k)       | O(n + k)        | Yes    | Uniformly distributed data |

## Limitations and Considerations

- **Range dependency**: Performance degrades if the range of values is very large.
- **Extra space**: Requires additional memory proportional to the range of values.
- **Limited applicability**: Not suitable for sorting non-integer data without preprocessing.
- **Inefficient for large ranges**: If k >> n, the algorithm becomes inefficient compared to O(n log n) algorithms.
- **Overflow risk**: Care must be taken when handling large values to avoid integer overflow in the counting array.
- **Fixed value range**: Works best when the range of possible values is known in advance.
Several optimizations can improve Counting Sort's performance:

1. **Range Reduction**: Find the minimum value and shift the range to reduce memory usage

2. **In-place Variation**: Modifications to reduce space complexity in specific scenarios

3. **Parallel Counting**: Distributing the counting phase across multiple processors

4. **Memory-efficient Implementations**: For large ranges with sparse distributions

5. **Adaptive Range Detection**: Dynamically determine the range to optimize space usage

## Comparison with Other Sorting Algorithms

| Algorithm | Time Complexity (Avg) | Space Complexity | Stable | In-Place | Notes |
|-----------|------------------------|------------------|--------|----------|-------|
| Counting Sort | O(n + k) | O(n + k) | Yes | No | Linear time for small ranges |
| Quick Sort | O(n log n) | O(log n) | No | Yes | More versatile, comparison-based |
| Merge Sort | O(n log n) | O(n) | Yes | No | Works for any comparable elements |
| Radix Sort | O(d * (n + k)) | O(n + k) | Yes | No | Uses Counting Sort as subroutine |

## Limitations and Considerations

- Inefficient for large ranges of values (large k)
- Not suitable for sorting non-integer data without mapping
- Memory usage can be prohibitive when the range is large
- Only directly applicable to non-negative integers (requires adaptation for other values)
- Doesn't take advantage of partially sorted inputs

Despite these limitations, Counting Sort's linear time complexity makes it an important algorithm in the sorting toolkit when the input constraints align with its strengths.
