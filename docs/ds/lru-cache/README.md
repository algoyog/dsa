# LRU Cache

## Overview

An LRU (Least Recently Used) Cache is a data structure that maintains a fixed-size collection of items, discarding the least recently used item when the cache reaches capacity and a new item needs to be added. This caching strategy is based on the principle that recently accessed items are likely to be accessed again in the near future.

## Problem Statement

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache:

1. Fixed capacity: The cache can hold up to a specified number of items
2. Get operation: Retrieve the value of a key if it exists in the cache (and mark as recently used)
3. Put operation: Add or update a key-value pair in the cache (and mark as recently used)
4. When capacity is reached: Remove the least recently used item
5. All operations must run in O(1) time complexity

## Data Structure Design

The LRU Cache combines two data structures:

1. **HashMap**: Provides O(1) lookup by key
2. **Doubly Linked List**: Maintains the order of usage and allows O(1) removal/insertion at any position

The key insight is using the linked list to track the access order while using the hash map for fast retrieval.

## Implementation

```java
public class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private Node head; // Most recently used
    private Node tail; // Least recently used

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        // Initialize dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key doesn't exist
        }

        // Move accessed node to front (most recently used position)
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Update existing node
            node.value = value;
            moveToHead(node);
        } else {
            // Create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);

            // Check if over capacity
            if (cache.size() > capacity) {
                // Remove the least recently used node (tail)
                Node removed = removeTail();
                cache.remove(removed.key);
            }
        }
    }

    private void addNode(Node node) {
        // Always add node right after head (most recently used position)
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        // Remove node from the doubly linked list
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(Node node) {
        // Move a node to the front of the list (most recently used position)
        removeNode(node);
        addNode(node);
    }

    private Node removeTail() {
        // Remove the node right before the tail (least recently used)
        Node removed = tail.prev;
        removeNode(removed);
        return removed;
    }
}
```

## Time and Space Complexity

| Operation | Time Complexity | Description |
|-----------|-----------------|-------------|
| get       | O(1)            | Constant time lookup and list update |
| put       | O(1)            | Constant time insertion and potential removal |

**Space Complexity**: O(capacity) for storing up to 'capacity' number of key-value pairs

## Use Cases

- **Web Browsers**: Caching recently visited web pages
- **Database Query Caching**: Storing results of frequent queries
- **Operating Systems**: Managing page tables and memory pages
- **Image Thumbnails**: Caching recently viewed images
- **API Rate Limiting**: Tracking recently made API calls
- **DNS Caching**: Storing recently resolved domain names

## Variants and Extensions

- **LFU Cache** (Least Frequently Used): Tracks frequency of use rather than recency
- **Time-Based Cache**: Items expire after a specified time
- **Weighted Cache**: Items have different sizes/weights for capacity calculations
- **Segmented LRU**: Maintains multiple LRU queues with different priorities
- **Concurrent LRU Cache**: Thread-safe implementation for concurrent access

## Implementation Considerations

1. **Thread Safety**: The implementation shown is not thread-safe. For concurrent access, consider using synchronization or concurrent data structures.
2. **Eviction Policies**: LRU is one of many possible eviction policies (others include FIFO, LFU, Random)
3. **Monitoring**: Real-world implementations often track metrics like hit/miss rates
4. **Size Limits**: Consider memory usage per item when calculating capacity
5. **Optimizations**: High-performance implementations may use specialized data structures like LinkedHashMap in Java

## Java's Built-in Solution

Java provides `LinkedHashMap` with access-order mode that can be used for LRU Cache implementation:

```java
public class LRUCacheWithLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        // Initialize with load factor 0.75 and access-order mode (true)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Remove the eldest entry when size exceeds capacity
        return size() > capacity;
    }
}
```

This implementation is simpler but less customizable than the manual approach with separate HashMap and doubly linked list.
