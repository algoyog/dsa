package org.algoyog.algos.ds.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlidingWindowTest {

    @Test
    public void testMaxSumSubarrayOfSizeK() {
        SlidingWindow slidingWindow = new SlidingWindow();

        // Test case 1: Regular array with positive integers
        int[] arr1 = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        assertEquals(24, slidingWindow.maxSumSubarrayOfSizeK(arr1, 4)); // 10+2+3+1 = 16, 2+3+1+0 = 6, 3+1+0+20 = 24

        // Test case 2: Array with the same integers
        int[] arr2 = {5, 5, 5, 5, 5};
        assertEquals(15, slidingWindow.maxSumSubarrayOfSizeK(arr2, 3)); // 5+5+5 = 15

        // Test case 3: Array with negative integers
        int[] arr3 = {-1, -2, -3, -4, -5};
        assertEquals(-6, slidingWindow.maxSumSubarrayOfSizeK(arr3, 3)); // -1+-2+-3 = -6

        // Test case 4: Window size equals array length
        int[] arr4 = {1, 2, 3};
        assertEquals(6, slidingWindow.maxSumSubarrayOfSizeK(arr4, 3)); // 1+2+3 = 6

        // Test case 5: Empty array
        int[] arr5 = {};
        assertEquals(0, slidingWindow.maxSumSubarrayOfSizeK(arr5, 3)); // Empty array, returns 0
    }

    @Test
    public void testLongestSubstringWithKDistinct() {
        SlidingWindow slidingWindow = new SlidingWindow();

        // Test case 1: Regular string with more than k distinct characters
        assertEquals(5, slidingWindow.longestSubstringWithKDistinct("eceba", 2)); // "eceb" has length 4

        // Test case 2: Regular string with exactly k distinct characters
        assertEquals(7, slidingWindow.longestSubstringWithKDistinct("aaabccc", 2)); // "aaabccc" has 2 distinct

        // Test case 3: String with only one character
        assertEquals(5, slidingWindow.longestSubstringWithKDistinct("aaaaa", 1)); // "aaaaa" has 1 distinct

        // Test case 4: Empty string
        assertEquals(0, slidingWindow.longestSubstringWithKDistinct("", 2)); // Empty string

        // Test case 5: Null string
        assertEquals(0, slidingWindow.longestSubstringWithKDistinct(null, 2)); // Null string

        // Test case 6: k = 0
        assertEquals(0, slidingWindow.longestSubstringWithKDistinct("abc", 0)); // k = 0
    }

    @Test
    public void testMinSubArrayLen() {
        SlidingWindow slidingWindow = new SlidingWindow();

        // Test case 1: Regular array with sum >= target
        int[] arr1 = {2, 3, 1, 2, 4, 3};
        assertEquals(2, slidingWindow.minSubArrayLen(7, arr1)); // [4, 3] has sum 7

        // Test case 2: Array where all elements sum < target
        int[] arr2 = {1, 2, 3, 4, 5};
        assertEquals(0, slidingWindow.minSubArrayLen(20, arr2)); // No subarray sums to 20

        // Test case 3: Array with one element >= target
        int[] arr3 = {1, 4, 4};
        assertEquals(1, slidingWindow.minSubArrayLen(4, arr3)); // Single element 4 meets target

        // Test case 4: Empty array
        int[] arr4 = {};
        assertEquals(0, slidingWindow.minSubArrayLen(1, arr4)); // Empty array
    }

    @Test
    public void testLengthOfLongestSubstring() {
        SlidingWindow slidingWindow = new SlidingWindow();

        // Test case 1: String with repeating characters
        assertEquals(3, slidingWindow.lengthOfLongestSubstring("abcabcbb")); // "abc" has length 3

        // Test case 2: String with all same characters
        assertEquals(1, slidingWindow.lengthOfLongestSubstring("bbbbb")); // "b" has length 1

        // Test case 3: String with no repeating characters
        assertEquals(3, slidingWindow.lengthOfLongestSubstring("pwwkew")); // "wke" has length 3

        // Test case 4: Empty string
        assertEquals(0, slidingWindow.lengthOfLongestSubstring(""));

        // Test case 5: String with spaces and special characters
        assertEquals(7, slidingWindow.lengthOfLongestSubstring("ab c@1!x")); // "ab c@1!x" has length 7
    }
}
