package org.algoyog.algos.search;

/**
 * Implementation of common search algorithms
 * These are fundamental for many interview problems
 */
public class SearchAlgorithms {

    /**
     * Linear Search
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found target at index i
            }
        }
        return -1; // Target not found
    }

    /**
     * Binary Search (Iterative)
     * Only works on sorted arrays
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Avoid potential overflow with large numbers
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;
            }

            // If target is greater, ignore left half
            if (arr[mid] < target) {
                left = mid + 1;
            } 
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }

    /**
     * Binary Search (Recursive)
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack
     */
    public int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    private int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1; // Base case: target not found

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid; // Found target at mid
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right); // Search right half
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1); // Search left half
        }
    }

    /**
     * Binary Search for first occurrence of target
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int binarySearchFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid; // Found target, but continue searching left side
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * Binary Search for last occurrence of target
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int binarySearchLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid; // Found target, but continue searching right side
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * Binary Search on Answer (Bisection Method)
     * Used when the search space is continuous (e.g., floats)
     * or when searching for an optimal value that satisfies a condition
     * 
     * Example: Find the square root of a number using binary search
     * Time Complexity: O(log n) where n depends on the precision required
     * Space Complexity: O(1)
     */
    public double squareRoot(int x, double precision) {
        if (x < 0) return -1; // Error for negative numbers
        if (x == 0 || x == 1) return x;

        double left = 0;
        double right = x;
        double result = 0;

        while (left <= right) {
            double mid = left + (right - left) / 2;
            double square = mid * mid;

            // If square is within precision of x, return mid
            if (Math.abs(square - x) < precision) {
                return mid;
            }

            if (square < x) {
                left = mid + precision;
                result = mid; // Update result to the current mid
            } else {
                right = mid - precision;
            }
        }

        return result;
    }

    /**
     * Binary Search on Rotated Sorted Array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int searchInRotatedSortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target is in the left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // Right half is sorted
            else {
                // Check if target is in the right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    /**
     * Find Peak Element
     * A peak element is an element that is strictly greater than its neighbors.
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Peak is in the left half, including mid
                right = mid;
            } else {
                // Peak is in the right half
                left = mid + 1;
            }
        }

        // When left == right, we've found the peak
        return left;
    }
}
