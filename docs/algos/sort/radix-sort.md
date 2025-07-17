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
# Radix Sort

## Problem Statement

Radix Sort is a non-comparison-based sorting algorithm that sorts data with integer keys by grouping keys by individual digits that share the same significant position and value. It processes the digits of the numbers from the least significant digit (LSD) to the most significant digit (MSD), or vice versa.

## Algorithm Strategy

Radix Sort follows these steps:

1. Find the maximum number to know the number of digits.
2. For each digit position (starting from the least significant digit):
   a. Sort the numbers based on the current digit position using a stable sort (typically Counting Sort).
3. Repeat until all digit positions are processed.

## Mathematical Foundation

Radix Sort works on the mathematical principle that we can sort numbers by their individual digits, starting from the least significant position and moving toward the most significant position. The stable sorting property ensures that the relative order of elements with equal digits at the current position is preserved.

The time complexity is O(d*(n+k)) where:
- n is the number of elements
- k is the range of input (typically the base of the number system, e.g., 10 for decimal)
- d is the number of digits in the maximum element

## Implementation Details

```java
public void radixSort(int[] arr) {
    if (arr == null || arr.length <= 1) return;

    // Find the maximum number to know the number of digits
    int max = getMax(arr);

    // Do counting sort for every digit
    // exp is 10^i where i is the current digit position
    for (int exp = 1; max / exp > 0; exp *= 10) {
        countingSortByDigit(arr, exp);
    }
}

private int getMax(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;
}

private void countingSortByDigit(int[] arr, int exp) {
    int n = arr.length;
    int[] output = new int[n];
    int[] count = new int[10]; // Counts for digits 0-9

    // Count occurrences of each digit
    for (int i = 0; i < n; i++) {
        int digit = (arr[i] / exp) % 10;
        count[digit]++;
    }

    // Change count[i] so that it contains the position of this digit in output[]
    for (int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // Build the output array
    for (int i = n - 1; i >= 0; i--) {
        int digit = (arr[i] / exp) % 10;
        output[count[digit] - 1] = arr[i];
        count[digit]--;
    }

    // Copy the output array back to the original array
    System.arraycopy(output, 0, arr, 0, n);
}
```

## Testing Methodology

Radix Sort should be tested with various input types:
- Arrays with different numbers of digits
- Arrays with all elements having the same number of digits
- Arrays with some zero values
- Very large arrays to test efficiency
- Arrays with negative numbers (requiring special handling)

## Unique Properties

- **Non-comparison sort**: Sorts without comparing elements directly.
- **Stable sort**: Preserves the relative order of elements with equal keys.
- **Linear time complexity**: O(d*(n+k)) can be better than O(n log n) when d is small.
- **Digit-by-digit processing**: Handles each digit position independently.

## Use Cases

1. Sorting large integers with a bounded number of digits
2. Sorting strings of equal length
3. Sorting decimal or fixed-point numbers
4. Applications where stability is required
5. Card sorting machines (the original inspiration for the algorithm)

## Real-world Applications

- Database indexing and sorting
- Numerical simulations with fixed-precision numbers
- Network routing tables sorted by IP addresses
- Telephone directory sorting
- Postal/mail sorting systems

## Complexity Analysis

| Case      | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Best      | O(d*(n+k))     | O(n+k)           |
| Average   | O(d*(n+k))     | O(n+k)           |
| Worst     | O(d*(n+k))     | O(n+k)           |

Where:
- n is the number of elements
- k is the range of a digit (typically 10 for decimal numbers)
- d is the number of digits in the maximum element

## Optimizations

1. **MSD (Most Significant Digit) Radix Sort**: Start sorting from the most significant digit; can be more efficient for string sorting.
2. **Hybrid approaches**: Combine with other algorithms for different digit positions or array sizes.
3. **Parallel processing**: The digit-by-digit sorting can be parallelized for large datasets.
4. **In-place implementation**: Reduce memory usage by implementing an in-place version.
5. **Adaptive digit handling**: Process only the necessary digit positions based on the data distribution.

## Comparison with Other Sorting Algorithms

| Algorithm      | Time Complexity | Space Complexity | Stable | Best For                   |
|----------------|----------------|-----------------|--------|----------------------------|
| Radix Sort     | O(d*(n+k))     | O(n+k)          | Yes    | Fixed-length integers     |
| Counting Sort  | O(n+k)         | O(n+k)          | Yes    | Small range integers       |
| Quick Sort     | O(n log n)     | O(log n)        | No     | General-purpose sorting    |
| Merge Sort     | O(n log n)     | O(n)            | Yes    | Stable, guaranteed performance |
| Bucket Sort    | O(n+k)         | O(n+k)          | Yes    | Uniformly distributed data |

## Limitations and Considerations

- **Digit dependency**: Performance depends on the number of digits in the maximum value.
- **Extra space**: Requires additional memory proportional to the input size.
- **Limited applicability**: Primarily designed for integers or strings; requires preprocessing for other data types.
- **Handling negative numbers**: Standard implementation typically requires special handling for negative numbers.
- **Variable-length keys**: Basic implementation assumes fixed-length keys or pads shorter keys.
- **Floating-point numbers**: Requires special handling for floating-point values.
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
