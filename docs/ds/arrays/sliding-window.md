# Sliding Window Technique
# Sliding Window Technique

## Problem Statement

The Sliding Window technique addresses the challenge of efficiently processing subarrays or substrings of a given array or string. It's particularly useful for problems that involve finding a subarray that satisfies certain conditions, such as having a specific sum, containing specific elements, or being of a specific length.

## Algorithm Strategy

The Sliding Window technique uses a window that slides through the array or string, expanding or contracting based on specific conditions. The key patterns are:

1. **Fixed Size Window**: The window maintains a constant size as it slides through the array
2. **Variable Size Window**: The window expands or contracts based on certain conditions
3. **Dynamic Window**: The window size changes based on complex criteria related to the elements within it

## Mathematical Foundation

The technique leverages the overlapping nature of consecutive subarrays/substrings. When moving a window, we only need to account for the elements being added and removed, rather than recalculating properties of the entire window.

## Implementation

```java
public class SlidingWindow {
    // Example: Maximum Sum Subarray of Size K (Fixed Window)
    public int maxSumSubarray(int[] arr, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // Add the next element

            // When we hit the window size, calculate max and slide window
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; // Remove element going out
                windowStart++; // Slide window forward
            }
        }

        return maxSum;
    }

    // Example: Longest Substring with K Distinct Characters (Variable Window)
    public int longestSubstringWithKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) {
            return 0;
        }

        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            // Shrink the window until we have at most k distinct characters
            while (charFrequency.size() > k) {
                char leftChar = s.charAt(windowStart);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);

                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }

                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
```

## Testing Methodology

When testing Sliding Window implementations, consider these cases:

1. Empty arrays or strings
2. Window size larger than array/string length
3. Arrays/strings with duplicate elements
4. Edge cases with minimum/maximum values
5. Windows that should expand to include the entire array/string
6. Windows that should contract to zero size

## Unique Properties

- Reduces time complexity from O(n²) or worse to O(n) for many problems
- Utilizes constant or limited extra space
- Efficiently processes subproblems with overlapping elements
- Can handle both fixed and variable-sized windows

## Use Cases

- Maximum/minimum sum subarray of fixed size
- Longest/shortest substring with specific properties
- Finding all anagrams in a string
- Maximum number of fruits in baskets problem
- Minimum window substring
- Maximum points on a line

## Real-World Applications

- Network packet analysis for traffic monitoring
- Stock price analysis over moving time periods
- Image processing (convolution operations)
- Genetic sequence analysis
- Text similarity and plagiarism detection

## Complexity Analysis

| Application | Time Complexity | Space Complexity |
|-------------|-----------------|------------------|
| Fixed Window | O(n)           | O(1) or O(k)     |
| Variable Window | O(n)        | O(k) - size of window or distinct elements |
| String Processing | O(n)      | O(1) to O(k)     |

## Optimizations

- Use appropriate data structures to track window properties efficiently
- Early termination when certain conditions are met
- Preprocessing the array for cumulative properties (e.g., prefix sums)
- Using counters instead of sets/maps when applicable

## Comparison with Other Approaches

| Approach       | Time Complexity | Space Complexity | When to Prefer |
|----------------|-----------------|------------------|----------------|
| Sliding Window | O(n)            | O(1) to O(k)     | Subarray/substring problems with locality |
| Brute Force    | O(n²) or worse  | O(1)             | Never, if Sliding Window can be applied |
| Prefix Sum     | O(n) preprocessing + O(1) queries | O(n) | Range sum queries with many queries |

## Limitations and Considerations

- Not applicable to problems requiring non-contiguous elements
- May require additional data structures for complex conditions
- Implementation can become complex for multi-criteria windows
- Care must be taken to avoid off-by-one errors when expanding/contracting the window
## Problem Statement

The Sliding Window technique is used to solve problems that involve finding a subarray or substring that satisfies certain conditions. It's particularly effective when we need to track a contiguous sequence of elements.

## Algorithm Strategy

The Sliding Window technique maintains a "window" of elements and slides it across the data to process. It comes in two main forms:

1. **Fixed-Size Window**: The window size remains constant throughout the algorithm.
2. **Variable-Size Window**: The window expands or contracts based on certain conditions.

## Mathematical Foundation

The technique reduces the time complexity from potentially O(n²) to O(n) by avoiding redundant computations when the window shifts.

## Implementation Details

### Fixed-Size Window

```java
public int maxSumSubarrayOfSizeK(int[] arr, int k) {
    int maxSum = 0;
    int windowSum = 0;
    int windowStart = 0;

    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
        // Add the next element to the window
        windowSum += arr[windowEnd];

        // When we hit the window size, calculate max and slide the window
        if (windowEnd >= k - 1) {
            maxSum = Math.max(maxSum, windowSum);
            // Remove the element going out of the window
            windowSum -= arr[windowStart];
            // Slide the window
            windowStart++;
        }
    }

    return maxSum;
}
```

### Variable-Size Window

```java
public int minSubArrayLen(int target, int[] nums) {
    int windowSum = 0;
    int minLength = Integer.MAX_VALUE;
    int windowStart = 0;

    for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
        // Add the next element to the window
        windowSum += nums[windowEnd];

        // Shrink the window as small as possible while maintaining the sum >= target
        while (windowSum >= target) {
            minLength = Math.min(minLength, windowEnd - windowStart + 1);
            // Remove the leftmost element
            windowSum -= nums[windowStart];
            windowStart++;
        }
    }

    return minLength == Integer.MAX_VALUE ? 0 : minLength;
}
```

## Testing Methodology

Test with various input arrays and constraints:
- Empty arrays
- Single element arrays
- Arrays where all elements satisfy the condition
- Arrays where no elements satisfy the condition

## Unique Properties

- Linear time complexity O(n) for most implementations
- Constant space complexity O(1) for basic implementations
- Can be extended to use auxiliary data structures for more complex conditions

## Use Cases

1. Maximum/minimum sum subarray of size k
2. Longest substring with k distinct characters
3. Minimum size subarray sum
4. Longest substring without repeating characters
5. String permutation verification

## Real-world Applications

- Network packet analysis
- Stock market analysis (moving averages)
- Text processing and pattern matching
- Image processing (kernel operations)

## Complexity Analysis

| Implementation | Time Complexity | Space Complexity |
|----------------|----------------|------------------|
| Fixed Window   | O(n)           | O(1)             |
| Variable Window| O(n)           | O(1) to O(k)     |

Where:
- n is the size of the input array
- k is the size of the auxiliary data structure (e.g., HashMap for character frequency)

## Optimizations

- Use appropriate data structures for window content tracking (HashMap, HashSet)
- Early termination when conditions cannot be satisfied
- Precompute values where possible (e.g., prefix sums)

## Comparison with Other Algorithms

| Algorithm      | Time Complexity | When to Use                                      |
|----------------|----------------|--------------------------------------------------|
| Sliding Window | O(n)           | Contiguous subarrays/substrings                  |
| Two Pointers   | O(n)           | When elements can be processed from both ends    |
| Binary Search  | O(log n)       | When searching in sorted arrays                  |

## Limitations and Considerations

- Only applicable to problems involving contiguous elements
- May require careful handling of edge cases
- Auxiliary data structures may increase space complexity
