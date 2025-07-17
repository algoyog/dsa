# Min Stack

## Problem Statement

Design a stack data structure that supports all the standard stack operations (push, pop, top) and an additional operation getMin() which returns the minimum element in the stack. All operations must perform in constant time O(1).

## Data Structure Design

The key challenge is to maintain the minimum value efficiently without having to search through the entire stack each time getMin() is called. Two main approaches exist:

1. **Two-Stack Approach**: Use a main stack for all values and an auxiliary stack that tracks minimum values
2. **Single-Stack with Pairs**: Store each value along with the current minimum at that point

## Implementation Details

### Two-Stack Approach

```java
public class MinStack {

    private final Stack<Integer> stack;      // Main stack to store values
    private final Stack<Integer> minStack;   // Stack to track minimum values

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Push element onto the stack
     * Time Complexity: O(1)
     */
    public void push(int val) {
        stack.push(val);

        // If minStack is empty or val is less than or equal to current min, push to minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Pop element from the top of the stack
     * Time Complexity: O(1)
     */
    public void pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        // If the popped element is the current minimum, also pop from minStack
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        stack.pop();
    }

    /**
     * Get the top element of the stack
     * Time Complexity: O(1)
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return stack.peek();
    }

    /**
     * Get the minimum element in the stack
     * Time Complexity: O(1)
     */
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return minStack.peek();
    }
}
```

### Single-Stack with Pairs Approach

```java
public class MinStackSingleStack {
    private static class Pair {
        int value;
        int min;

        Pair(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    private final Stack<Pair> stack;

    public MinStackSingleStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int min = stack.isEmpty() ? val : Math.min(val, stack.peek().min);
        stack.push(new Pair(val, min));
    }

    public void pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        stack.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek().value;
    }

    public int getMin() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek().min;
    }
}
```

## Mathematical Foundation

Both implementations ensure O(1) time complexity for all operations by maintaining the minimum value at each step, either in a separate stack or as part of the element's metadata. The space complexity is O(n) where n is the number of elements in the stack.

## Testing Methodology

Test MinStack implementations with various scenarios:
- Push and pop operations in different orders
- Multiple elements with the same value
- Decreasing sequence of values
- Increasing sequence of values
- Random sequence of values
- Edge cases (empty stack, single element)

## Unique Properties

- **Constant Time Operations**: All operations (push, pop, top, getMin) run in O(1) time
- **Space-Time Tradeoff**: Uses additional space to achieve constant time minimum retrieval
- **Stateful**: The minimum value depends on the current state of the stack

## Use Cases

1. **Stack with Minimum Tracking**: Applications requiring both LIFO behavior and minimum value tracking
2. **Expression Evaluation**: Parse and evaluate expressions while tracking minimum values
3. **Undo Mechanisms**: Systems that need to track minimum states for optimization
4. **Algorithmic Problems**: Used in problems like finding the largest rectangle in histogram
5. **Game Development**: Tracking minimum scores or resources in game state stacks

## Real-world Applications

- **Financial Applications**: Track minimum stock prices in time series
- **Resource Allocation**: Monitor minimum available resources in nested scopes
- **Text Editors**: Implement undo functionality with minimum state tracking
- **Compiler Design**: Track minimum register usage in nested scopes
- **Network Packet Processing**: Process packets while tracking minimum latency

## Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| push      | O(1)           | O(1) per operation |
| pop       | O(1)           | O(1) per operation |
| top       | O(1)           | O(1)             |
| getMin    | O(1)           | O(1)             |
| Overall   | O(1) per operation | O(n) for n elements |

## Variations and Optimizations

1. **Min-Max Stack**: Track both minimum and maximum values
2. **Space Optimization**: Only push to minStack when a new minimum is encountered, with a count
3. **Custom Comparator**: Allow custom comparison for non-numeric types
4. **Thread-Safe Implementation**: Add synchronization for concurrent usage
5. **Optimistic Minimum Tracking**: Use a single value instead of a stack when elements are known to be in a certain pattern

## Comparison with Other Data Structures

| Data Structure | push    | pop     | top     | getMin  | Space   | Notes                    |
|----------------|---------|---------|---------|---------|---------|--------------------------||
| MinStack (Two-Stack) | O(1)    | O(1)    | O(1)    | O(1)    | O(n)    | Additional min stack     |
| MinStack (Pair)      | O(1)    | O(1)    | O(1)    | O(1)    | O(n)    | Value+min pairs          |
| Standard Stack       | O(1)    | O(1)    | O(1)    | O(n)    | O(n)    | Linear search for min    |
| PriorityQueue + Stack| O(log n)| O(log n)| O(1)    | O(1)    | O(n)    | Complex synchronization  |
| Balanced BST + Stack | O(log n)| O(log n)| O(1)    | O(log n)| O(n)    | Ordered elements         |

## Limitations and Considerations

- **Memory Overhead**: Both implementations use additional memory
- **Limited to Stack Operations**: Only supports standard stack operations plus getMin
- **No Random Access**: Cannot access or modify elements in the middle
- **Duplicate Handling**: Care needed when handling duplicate minimum values
- **Overflow/Underflow**: Integer overflow/underflow in extreme cases
- **No Iteration**: Standard implementations don't support iterating over elements
