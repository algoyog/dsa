package org.algoyog.algos.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache Implementation
 * 
 * Design a data structure that follows the constraints of an LRU cache:
 * 1. Fixed size: The cache has a fixed capacity
 * 2. Get operation: If the key exists, return its value and mark as recently used
 * 3. Put operation: If the cache is full, remove the least recently used item
 * 
 * Time Complexity: O(1) for both get and put operations
 * Space Complexity: O(capacity) to store at most 'capacity' key-value pairs
 */
public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // Dummy head of doubly linked list
    private final Node tail; // Dummy tail of doubly linked list

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node() {
            this(0, 0); // Constructor for dummy nodes
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        // Initialize the dummy head and tail nodes
        this.head = new Node();
        this.tail = new Node();

        // Connect head and tail
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Get the value for the key if it exists in the cache
     * Time Complexity: O(1)
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key doesn't exist
        }

        // Move the accessed node to the front (most recently used position)
        moveToHead(node);
        return node.value;
    }

    /**
     * Add or update the key-value pair
     * Time Complexity: O(1)
     */
    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node == null) {
            // Key doesn't exist, create a new node
            Node newNode = new Node(key, value);

            // Add to the cache hashmap
            cache.put(key, newNode);

            // Add to the front of the doubly linked list
            addNode(newNode);

            // Check if over capacity
            if (cache.size() > capacity) {
                // Remove the least recently used node (from the tail)
                Node tail = removeTail();
                cache.remove(tail.key);
            }
        } else {
            // Key exists, update the value and move to front
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * Add a node right after the dummy head
     */
    private void addNode(Node node) {
        // Connect new node with head's next
        node.next = head.next;
        node.prev = head;

        // Update head's next connections
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove a node from the doubly linked list
     */
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Move a node to the head (most recently used position)
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Remove and return the tail node (least recently used)
     */
    private Node removeTail() {
        Node res = tail.prev; // Get the node before the dummy tail
        removeNode(res);
        return res;
    }
}
