package org.algoyog.algos.ds.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Sliding Window Technique is commonly used for array and string problems
 * that involve contiguous subarrays or substrings.
 * Two main patterns:
 * 1. Fixed-size window
 * 2. Variable-size window with constraints
 */
public class SlidingWindow {

    /**
     * Maximum Sum Subarray of Size K - Fixed Window
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
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

    /**
     * Longest Substring with K Distinct Characters - Variable Window
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(k) for the HashMap storing at most k characters
     */
    public int longestSubstringWithKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        Map<Character, Integer> charFrequency = new HashMap<>();
        int windowStart = 0;
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            // Add the current character to the frequency map
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            // Shrink the window if we have more than k distinct characters
            while (charFrequency.size() > k) {
                char leftChar = s.charAt(windowStart);
                // Decrement the frequency of the character going out of the window
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }
                windowStart++;
            }

            // At this point, the window has at most k distinct characters
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Minimum Size Subarray Sum - Variable Window
     * Find the minimum length subarray with a sum at least equal to the target
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
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

    /**
     * Longest Substring Without Repeating Characters - Variable Window
     * Time Complexity: O(n)
     * Space Complexity: O(min(m, n)) where m is the size of the character set
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);

            // If we've seen this character before and it's inside the current window
            if (charIndexMap.containsKey(rightChar)) {
                // Move the window start to the right of the previous occurrence
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }

            // Update the index of the current character
            charIndexMap.put(rightChar, windowEnd);

            // Update the maximum length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
