# Dynamic Programming

## Overview

Dynamic Programming (DP) is a powerful algorithmic paradigm for solving complex problems by breaking them down into simpler overlapping subproblems. It combines the efficiency of recursion with memoization or tabulation to avoid redundant calculations, making it especially powerful for optimization problems.

## Key Concepts

1. **Optimal Substructure**: A problem has optimal substructure if an optimal solution can be constructed from optimal solutions of its subproblems.

2. **Overlapping Subproblems**: The same subproblems are solved multiple times when finding a solution to the original problem.

3. **Memoization**: Top-down approach where results of subproblems are stored to avoid recomputation.

4. **Tabulation**: Bottom-up approach where solutions are built iteratively from smaller to larger subproblems.

## Common DP Patterns

### 1. Linear DP
- [Fibonacci Numbers](fibonacci.md)
- Climbing Stairs
- House Robber

### 2. Multi-dimensional DP
- [Knapsack Problem](knapsack.md)
- [Coin Change](coin-change.md)
- Grid Traversal Problems

### 3. Interval DP
- Matrix Chain Multiplication
- Palindrome Problems

### 4. Subsequence and String Problems
- [Longest Common Subsequence](longest-common-subsequence.md)
- [Longest Increasing Subsequence](longest-increasing-subsequence.md)
- [Edit Distance](edit-distance.md)

### 5. Decision Making
- Stock Buying and Selling
- Game Theory Problems

## General Problem-Solving Approach

1. **Identify if the problem can be solved with DP**:
   - Check for overlapping subproblems and optimal substructure

2. **Define the state**:
   - What information do we need to solve a subproblem?
   - How can we represent this state efficiently?

3. **Define the recurrence relation**:
   - How does the solution to the current state relate to solutions of smaller states?

4. **Define the base cases**:
   - What are the simplest subproblems that can be solved directly?

5. **Implement using memoization or tabulation**:
   - Top-down (recursion with memoization) or bottom-up (iterative with tabulation)

6. **Optimize space complexity** (if needed):
   - Many DP solutions can be optimized to use O(1) or O(n) space instead of O(n²)

## Implementation Approaches

### Top-down (Memoization)

```java
public int fibTopDown(int n) {
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return fibMemoized(n, memo);
}

private int fibMemoized(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];

    memo[n] = fibMemoized(n - 1, memo) + fibMemoized(n - 2, memo);
    return memo[n];
}
```

### Bottom-up (Tabulation)

```java
public int fibBottomUp(int n) {
    if (n <= 1) return n;

    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
}
```

### Space-Optimized

```java
public int fibOptimized(int n) {
    if (n <= 1) return n;

    int prev2 = 0;
    int prev1 = 1;
    int current = 0;

    for (int i = 2; i <= n; i++) {
        current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }

    return current;
}
```

## Time and Space Complexity

- **Time Complexity**: Typically O(n) to O(n²) depending on the problem and number of states
- **Space Complexity**: 
  - Memoization: O(n) to O(n²) + recursion stack space
  - Tabulation: O(n) to O(n²) without recursion overhead
  - Optimized: Can often be reduced to O(1) to O(n)

## When to Use Dynamic Programming

- When a problem can be broken down into overlapping subproblems
- When the problem asks for optimization (maximum/minimum) or counting solutions
- When a greedy approach doesn't work because local optimum doesn't lead to global optimum
- Common indicators in problem statements:
  - "Find the maximum/minimum..."
  - "Count the number of ways..."
  - "What is the optimal strategy..."

## Common Pitfalls

1. Incorrect state definition leading to missing information
2. Wrong recurrence relation or transition logic
3. Missing base cases or handling them incorrectly
4. Integer overflow in counting problems
5. Not considering constraints that affect the solution

## Real-World Applications

- Resource allocation and scheduling
- Sequence alignment in bioinformatics
- Natural language processing
- Financial modeling and option pricing
- Robotics and path planning
- Computer graphics (e.g., seam carving)

Dynamic Programming provides elegant solutions to many computationally intensive problems by trading space for time, making previously intractable problems solvable efficiently.
