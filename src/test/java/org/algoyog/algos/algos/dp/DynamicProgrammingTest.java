package org.algoyog.algos.algos.dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicProgrammingTest {

    private DynamicProgramming dp;

    @BeforeEach
    public void setUp() {
        dp = new DynamicProgramming();
    }

    @Test
    public void testFibonacci() {
        // Test with small values
        assertEquals(0, dp.fibonacci(0));
        assertEquals(1, dp.fibonacci(1));
        assertEquals(1, dp.fibonacci(2));
        assertEquals(2, dp.fibonacci(3));
        assertEquals(3, dp.fibonacci(4));
        assertEquals(5, dp.fibonacci(5));
        assertEquals(8, dp.fibonacci(6));
        assertEquals(13, dp.fibonacci(7));

        // Test with larger value
        assertEquals(55, dp.fibonacci(10));
    }

    @Test
    public void testFibonacciOptimized() {
        // Test with small values
        assertEquals(0, dp.fibonacciOptimized(0));
        assertEquals(1, dp.fibonacciOptimized(1));
        assertEquals(1, dp.fibonacciOptimized(2));
        assertEquals(2, dp.fibonacciOptimized(3));
        assertEquals(3, dp.fibonacciOptimized(4));
        assertEquals(5, dp.fibonacciOptimized(5));
        assertEquals(8, dp.fibonacciOptimized(6));
        assertEquals(13, dp.fibonacciOptimized(7));

        // Test with larger value
        assertEquals(55, dp.fibonacciOptimized(10));
    }

    @Test
    public void testKnapsack01() {
        // Test case 1
        int[] values1 = {60, 100, 120};
        int[] weights1 = {10, 20, 30};
        int capacity1 = 50;
        assertEquals(220, dp.knapsack01(values1, weights1, capacity1));

        // Test case 2 - When capacity is less than any single item
        int[] values2 = {60, 100, 120};
        int[] weights2 = {10, 20, 30};
        int capacity2 = 5;
        assertEquals(0, dp.knapsack01(values2, weights2, capacity2));

        // Test case 3 - When all items can fit
        int[] values3 = {60, 100, 120};
        int[] weights3 = {10, 20, 30};
        int capacity3 = 60;
        assertEquals(280, dp.knapsack01(values3, weights3, capacity3));

        // Test case 4 - With duplicate weights
        int[] values4 = {10, 40, 30, 50};
        int[] weights4 = {5, 4, 6, 4};
        int capacity4 = 10;
        assertEquals(90, dp.knapsack01(values4, weights4, capacity4));
    }

    @Test
    public void testCoinChangeMinCoins() {
        // Test case 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        assertEquals(3, dp.coinChangeMinCoins(coins1, amount1)); // 5 + 5 + 1 = 11 (3 coins)

        // Test case 2 - No solution
        int[] coins2 = {2};
        int amount2 = 3;
        assertEquals(-1, dp.coinChangeMinCoins(coins2, amount2));

        // Test case 3 - Amount is 0
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        assertEquals(0, dp.coinChangeMinCoins(coins3, amount3));

        // Test case 4 - Multiple solutions
        int[] coins4 = {1, 3, 4, 5};
        int amount4 = 7;
        assertEquals(2, dp.coinChangeMinCoins(coins4, amount4)); // 3 + 4 = 7 (2 coins)
    }

    @Test
    public void testCoinChangeWays() {
        // Test case 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        assertEquals(4, dp.coinChangeWays(coins1, amount1)); // {5}, {1,1,1,1,1}, {1,1,1,2}, {1,2,2}

        // Test case 2 - No solution
        int[] coins2 = {2};
        int amount2 = 3;
        assertEquals(0, dp.coinChangeWays(coins2, amount2));

        // Test case 3 - Amount is 0
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        assertEquals(1, dp.coinChangeWays(coins3, amount3)); // empty set

        // Test case 4 - Multiple ways
        int[] coins4 = {1, 2, 3};
        int amount4 = 4;
        assertEquals(4, dp.coinChangeWays(coins4, amount4)); // {1,1,1,1}, {1,1,2}, {1,3}, {2,2}
    }

    @Test
    public void testLongestCommonSubsequence() {
        // Test case 1
        assertEquals(3, dp.longestCommonSubsequence("abcde", "ace")); // "ace"

        // Test case 2 - No common subsequence
        assertEquals(0, dp.longestCommonSubsequence("abc", "def"));

        // Test case 3 - One string is empty
        assertEquals(0, dp.longestCommonSubsequence("", "abc"));
        assertEquals(0, dp.longestCommonSubsequence("abc", ""));

        // Test case 4 - Same strings
        assertEquals(3, dp.longestCommonSubsequence("abc", "abc")); // "abc"

        // Test case 5 - One string is a subsequence of the other
        assertEquals(3, dp.longestCommonSubsequence("abcdef", "ace")); // "ace"
    }

    @Test
    public void testLengthOfLIS() {
        // Test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, dp.lengthOfLIS(nums1)); // [2, 3, 7, 101] or [2, 5, 7, 101]

        // Test case 2 - All increasing
        int[] nums2 = {1, 2, 3, 4, 5};
        assertEquals(5, dp.lengthOfLIS(nums2)); // [1, 2, 3, 4, 5]

        // Test case 3 - All decreasing
        int[] nums3 = {5, 4, 3, 2, 1};
        assertEquals(1, dp.lengthOfLIS(nums3)); // Any single element

        // Test case 4 - With duplicates
        int[] nums4 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        assertEquals(6, dp.lengthOfLIS(nums4)); // [1, 3, 4, 5, 6, 10]

        // Test case 5 - Empty array
        int[] nums5 = {};
        assertEquals(0, dp.lengthOfLIS(nums5));
    }

    @Test
    public void testLengthOfLISOptimized() {
        // Test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, dp.lengthOfLISOptimized(nums1)); // [2, 3, 7, 101] or [2, 5, 7, 101]

        // Test case 2 - All increasing
        int[] nums2 = {1, 2, 3, 4, 5};
        assertEquals(5, dp.lengthOfLISOptimized(nums2)); // [1, 2, 3, 4, 5]

        // Test case 3 - All decreasing
        int[] nums3 = {5, 4, 3, 2, 1};
        assertEquals(1, dp.lengthOfLISOptimized(nums3)); // Any single element

        // Test case 4 - With duplicates
        int[] nums4 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        assertEquals(6, dp.lengthOfLISOptimized(nums4)); // [1, 3, 4, 5, 6, 10]

        // Test case 5 - Empty array
        int[] nums5 = {};
        assertEquals(0, dp.lengthOfLISOptimized(nums5));
    }

    @Test
    public void testMinDistance() {
        // Test case 1
        assertEquals(3, dp.minDistance("horse", "ros")); // Delete 'h', 'r', 's'

        // Test case 2
        assertEquals(5, dp.minDistance("intention", "execution")); // Replace 'i'->e, 'n'->x, 't'->e, 'n'->c, 'i'->u

        // Test case 3 - One empty string
        assertEquals(5, dp.minDistance("", "hello")); // Insert all characters
        assertEquals(5, dp.minDistance("hello", "")); // Delete all characters

        // Test case 4 - Same strings
        assertEquals(0, dp.minDistance("abc", "abc"));

        // Test case 5 - All operations
        assertEquals(3, dp.minDistance("abc", "def")); // Replace all characters
    }
}
