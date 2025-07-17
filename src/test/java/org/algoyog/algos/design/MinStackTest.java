package org.algoyog.algos.design;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinStackTest {

    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();

        // Test push and top
        minStack.push(3);
        assertEquals(3, minStack.top());
        assertEquals(3, minStack.getMin());

        // Test with multiple elements
        minStack.push(5);
        assertEquals(5, minStack.top());
        assertEquals(3, minStack.getMin());

        minStack.push(2);
        assertEquals(2, minStack.top());
        assertEquals(2, minStack.getMin());

        minStack.push(1);
        assertEquals(1, minStack.top());
        assertEquals(1, minStack.getMin());

        // Test pop and min updates
        minStack.pop();
        assertEquals(2, minStack.top());
        assertEquals(2, minStack.getMin());

        minStack.pop();
        assertEquals(5, minStack.top());
        assertEquals(3, minStack.getMin());

        minStack.pop();
        assertEquals(3, minStack.top());
        assertEquals(3, minStack.getMin());
    }

    @Test
    public void testMinStackWithDuplicateMin() {
        MinStack minStack = new MinStack();

        minStack.push(1);
        minStack.push(2);
        minStack.push(1);

        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
    }

    @Test
    public void testMinStackEmptyExceptions() {
        MinStack minStack = new MinStack();

        // Test operations on empty stack
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            minStack.top();
        });
        assertEquals("Stack is empty", exception.getMessage());

        exception = assertThrows(IllegalStateException.class, () -> {
            minStack.getMin();
        });
        assertEquals("Stack is empty", exception.getMessage());

        exception = assertThrows(IllegalStateException.class, () -> {
            minStack.pop();
        });
        assertEquals("Stack is empty", exception.getMessage());
    }

    @Test
    public void testMinStackSingleStack() {
        MinStack.MinStackSingleStack minStack = new MinStack.MinStackSingleStack();

        // Test push and top
        minStack.push(3);
        assertEquals(3, minStack.top());
        assertEquals(3, minStack.getMin());

        // Test with multiple elements
        minStack.push(5);
        assertEquals(5, minStack.top());
        assertEquals(3, minStack.getMin());

        minStack.push(2);
        assertEquals(2, minStack.top());
        assertEquals(2, minStack.getMin());

        // Test pop and min updates
        minStack.pop();
        assertEquals(5, minStack.top());
        assertEquals(3, minStack.getMin());

        // Test with duplicate min values
        minStack.push(3);
        assertEquals(3, minStack.top());
        assertEquals(3, minStack.getMin());

        minStack.push(1);
        assertEquals(1, minStack.top());
        assertEquals(1, minStack.getMin());

        minStack.pop();
        assertEquals(3, minStack.top());
        assertEquals(3, minStack.getMin());
    }
}
