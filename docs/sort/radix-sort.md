# Radix Sort

## Problem Statement

Radix Sort addresses the challenge of efficiently sorting integers or strings without direct element-to-element comparisons. It processes individual digits or characters of the elements, sorting them position by position, from the least significant digit to the most significant digit (LSD variant) or vice versa (MSD variant).

## Algorithm Strategy

The LSD (Least Significant Digit) Radix Sort, which is the most common implementation, follows these steps:

1. **Identify the maximum element** to determine the number of digits in the largest value

2. **Process each digit position**, from least significant to most significant:
   - For each position, sort all elements based only on that digit
   - Use a stable sort (like Counting Sort) for each digit position

3. **Repeat until all positions are processed**

The key insight is that by using a stable sort for each digit, the relative order of elements with the same digit is preserved across iterations.

## Mathematical Foundation

The mathematical basis for Radix Sort comes from the positional number system:

- For a number in base b with d digits, we perform d passes
- Each pass sorts the numbers by their digit at position i, using a stable O(n + b) algorithm
- After all passes, the array is fully sorted

The time complexity is O(d * (n + b)), where n is the number of elements, b is the base (typically 10 for decimal), and d is the number of digits in the maximum element.

## Implementation Guide

```java
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
```

## Testing Theory

A comprehensive test suite for Radix Sort should include:

1. **Standard cases**:
   - Random arrays of positive integers
   - Arrays with distinct elements
   - Arrays with duplicate elements

2. **Edge cases**:
   - Empty array
   - Single-element array
   - Already sorted array
   - Reverse-sorted array

3. **Special scenarios**:
   - Numbers with different digit counts
   - Numbers with leading zeros
   - Large arrays to test performance
   - Numbers with many digits

4. **Property verification**:
   - Stability (equal elements maintain their relative order)
   - Correctness (elements are properly sorted)
   - Time complexity verification

## Unique Properties

- **Non-comparison based**: Does not use element-to-element comparisons
- **Stable**: Equal elements maintain their relative order
- **Linear time possible**: Under certain constraints, can achieve O(n) time complexity
- **Works with fixed-length keys**: Particularly efficient for fixed-width integers or strings
- **Digit-by-digit processing**: Can start yielding partially sorted results early (especially with MSD variant)

## When to Use Radix Sort

Radix Sort is particularly well-suited for:

- Sorting large collections of integers with bounded digit length
- Sorting strings of fixed or bounded length
- When stability is required
- When the distribution of values allows for efficient digit-based sorting
- When comparison-based sorting is a bottleneck

## Real-world Applications

1. **String Processing**: Sorting large collections of strings like dictionaries or URLs

2. **Numerical Processing**: Sorting large sets of numerical data with bounded ranges

3. **Database Systems**: For indexing and organizing records with numeric keys

4. **Network Routing**: For IP address sorting and classification

5. **Computer Graphics**: For depth sorting in rendering pipelines

6. **Genomic Sequence Analysis**: For sorting and aligning genetic sequences

## Complexity Analysis

- **Time Complexity**:
  - Best case: O(d * (n + k))
  - Average case: O(d * (n + k))
  - Worst case: O(d * (n + k))
  - Where d is the number of digits, n is the number of elements, and k is the range of each digit (typically 10)

- **Space Complexity**: O(n + k)

## Optimizations

Several optimizations can improve Radix Sort's performance:

1. **Parallel digit processing**: Distributing the work across multiple processors

2. **Hybrid approaches**: Combining with other sorting algorithms for specific cases

3. **Base selection**: Choosing an optimal base to minimize the number of passes

4. **MSD variant**: Using Most Significant Digit first for certain applications

5. **In-place variations**: Reducing space complexity in specific scenarios

6. **Specialized digit extraction**: For non-standard number representations

## Comparison with Other Sorting Algorithms

| Algorithm | Time Complexity (Avg) | Space Complexity | Stable | In-Place | Notes |
|-----------|------------------------|------------------|--------|----------|-------|
| Radix Sort | O(d * (n + k)) | O(n + k) | Yes | No | Linear time for fixed-length keys |
| Quick Sort | O(n log n) | O(log n) | No | Yes | More versatile, generally faster for variable-length keys |
| Counting Sort | O(n + k) | O(n + k) | Yes | No | More efficient for small ranges |
| Merge Sort | O(n log n) | O(n) | Yes | No | Works for any comparable elements |

## Limitations and Considerations

- Only directly applicable to integers and strings (requires adaptation for other types)
- Performance depends on the number of digits in the maximum value
- Less efficient for wide-ranging values with few elements
- Additional space requirements can be significant
- May not perform well with variable-length keys without specific adaptations
- Implementation complexity is higher than simple comparison-based sorts

Despite these limitations, Radix Sort offers impressive performance for its applicable domains, providing a valuable non-comparison-based alternative in the sorting algorithm toolkit.
