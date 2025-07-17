# LRU Cache

## Problem Statement

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put, both in O(1) time complexity.

- **get(key)**: Get the value of the key if the key exists in the cache, otherwise return -1.
- **put(key, value)**: Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting a new item.

## Data Structure Design

The LRU Cache requires a combination of two data structures:

1. **Doubly Linked List**: To maintain the order of elements based on their usage (most recently used at the head, least recently used at the tail)
2. **Hash Map**: To provide O(1) access to elements by key

## Implementation Details

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
    private final HashMap<Integer, Node> cache;
    private final Node head; // Most recently used
    private final Node tail; // Least recently used

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get the value of the key if the key exists in the cache, otherwise return -1
     * Time Complexity: O(1)
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Get the node and move it to the front (most recently used)
        Node node = cache.get(key);
        moveToHead(node);

        return node.value;
    }

    /**
     * Set or insert the value if the key is not already present
     * When the cache reaches capacity, invalidate the least recently used item
     * Time Complexity: O(1)
     */
    public void put(int key, int value) {
        // If key exists, update the value and move to head
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }

        // If at capacity, remove the least recently used item (tail)
        if (cache.size() == capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.key);
        }

        // Add new node to head (most recently used)
        Node newNode = new Node(key, value);
        addToHead(newNode);
        cache.put(key, newNode);
    }

    /**
     * Helper method to add a node right after the head (most recently used)
     */
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Helper method to remove a node from the linked list
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Helper method to move a node to the head (most recently used)
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}
```

## Mathematical Foundation

The LRU Cache implements the Least Recently Used replacement policy, which discards the least recently used items first when the cache is full. This approach is based on the principle of temporal locality, which suggests that recently accessed items are likely to be accessed again in the near future.

## Testing Methodology

Test LRU Cache implementations with various scenarios:
- Basic get and put operations
- Capacity limits and eviction behavior
- Updating existing keys
- Mixed sequences of operations
- Edge cases (capacity of 1, repeated access to same key)

## Unique Properties

- **Constant Time Operations**: Both get and put run in O(1) time
- **Ordered Access Tracking**: Maintains the order of access for all elements
- **Fixed Capacity**: Automatically evicts least recently used items when full
- **Self-Organizing**: The data structure reorganizes itself with each access

## Use Cases

1. **Web Page Caching**: Store recently visited web pages
2. **Database Query Caching**: Cache results of expensive queries
3. **File System Caching**: Operating system disk caches
4. **Image Thumbnail Caching**: Store recently accessed thumbnails
5. **API Response Caching**: Cache responses to reduce API calls

## Real-world Applications

- **Web Browsers**: Cache recently visited pages
- **Content Delivery Networks (CDNs)**: Cache popular content closer to users
- **Database Systems**: Buffer pools and query caches
- **Mobile Applications**: Cache network responses to save bandwidth
- **Distributed Systems**: Local caches to reduce network requests

## Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| get       | O(1)           | O(1) per operation |
| put       | O(1)           | O(1) per operation |
| Overall   | O(1) per operation | O(capacity) total |

## Variations and Optimizations

1. **LRU-K Cache**: Considers last K references instead of just the most recent
2. **Time-Aware LRU**: Adds time-based expiration to entries
3. **Segmented LRU**: Divides cache into multiple segments with different policies
4. **Concurrent LRU Cache**: Thread-safe implementation for concurrent access
5. **Distributed LRU Cache**: Implementation across multiple machines

## Comparison with Other Caching Algorithms

| Algorithm | Complexity | Pros                         | Cons                        |
|-----------|------------|------------------------------|-----------------------------|
| LRU       | O(1)       | Simple, efficient, adaptive  | Requires tracking all accesses |
| FIFO      | O(1)       | Very simple implementation   | Doesn't adapt to access patterns |
| LFU       | O(log n)*  | Keeps frequently used items  | Slow to adapt to changing patterns |
| Random    | O(1)       | Very low overhead            | Unpredictable performance   |
| CLOCK     | O(1)       | Approximates LRU efficiently | Slightly more complex       |

*Depends on implementation, can be O(1) with more complex data structures

## Limitations and Considerations

- **Memory Overhead**: Requires additional memory for the linked list nodes and hash map
- **Cold Start**: Performance may be suboptimal when the cache is first populated
- **Bursty Access Patterns**: May perform poorly with certain access patterns
- **No Frequency Consideration**: Doesn't consider how often items are accessed, only recency
- **No Size Awareness**: Treats all cache entries as equal size
- **Scan Resistance**: Vulnerable to cache pollution from one-time scans of many items
