package org.algoyog.algos.algos.dp;

import java.util.Arrays;

/**
 * Dynamic Programming (DP) is an algorithmic technique for solving complex problems
 * by breaking them down into simpler subproblems. It solves each subproblem only once
 * and stores its solution to avoid redundant calculations.
 */
public class DynamicProgramming {

    /**
     * Fibonacci Numbers using DP (Bottom-up)
     * Time Complexity: O(n)
     * Space Complexity: O(n) - can be optimized to O(1)
     */
    public int fibonacci(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Fibonacci with Space Optimization
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int fibonacciOptimized(int n) {
        if (n <= 1) return n;

        int prev1 = 1; // f(1)
        int prev2 = 0; // f(0)
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * 0/1 Knapsack Problem
     * Given weights and values of n items, put items in a knapsack of capacity W
     * to get the maximum total value.
     * Time Complexity: O(n * W) where n is number of items and W is capacity
     * Space Complexity: O(n * W)
     */
    public int knapsack01(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or no capacity
                } else if (weights[i - 1] <= w) {
                    // Max of (including the current item, excluding the current item)
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    // Current item is too heavy, can't include it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * Coin Change - Minimum number of coins to make a given amount
     * Time Complexity: O(amount * n) where n is the number of coin denominations
     * Space Complexity: O(amount)
     */
    public int coinChangeMinCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with a value greater than any possible solution
        dp[0] = 0; // Base case: 0 coins needed to make amount 0

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Coin Change - Number of ways to make a given amount with given coins
     * Time Complexity: O(amount * n)
     * Space Complexity: O(amount)
     */
    public int coinChangeWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // Base case: 1 way to make amount 0 (use no coins)

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    /**
     * Longest Common Subsequence
     * Time Complexity: O(m * n) where m, n are lengths of the strings
     * Space Complexity: O(m * n)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // If characters match, add 1 to the diagonal value
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // If characters don't match, take the max of left or top
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Longest Increasing Subsequence
     * Time Complexity: O(n²) where n is the length of the array
     * Space Complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each element is at least a subsequence of length 1
        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * Longest Increasing Subsequence - Optimized with Binary Search
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public int lengthOfLISOptimized(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // tails[i] stores the smallest tail of all increasing subsequences of length i+1
        int[] tails = new int[n];
        int len = 0;

        for (int num : nums) {
            // Binary search to find the position to insert or update
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = num;
            if (left == len) len++;
        }

        return len;
    }

    /**
     * Edit Distance
     * Calculate minimum operations (insert, delete, replace) to convert string1 to string2
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = min operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: convert to empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Delete all characters
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Insert all characters
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // No operation needed for this character
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Min of three operations:
                    // 1. Replace (dp[i-1][j-1] + 1)
                    // 2. Delete (dp[i-1][j] + 1)
                    // 3. Insert (dp[i][j-1] + 1)
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                  Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }
}
