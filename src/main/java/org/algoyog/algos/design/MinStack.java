package org.algoyog.algos.design;

import java.util.Stack;

/**
 * Min Stack Implementation
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is the number of elements in the stack
 */
public class MinStack {

    private final Stack<Integer> stack; // Main stack to store values
    private final Stack<Integer> minStack; // Stack to track minimum values

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

    /**
     * Alternative implementation using a single stack with pair values
     */
    public static class MinStackSingleStack {
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
}
