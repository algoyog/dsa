package org.algoyog.algos.algos.search;

import java.util.Arrays;

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

    /**
     * Jump Search
     * Works on sorted arrays
     * Time Complexity: O(√n)
     * Space Complexity: O(1)
     * Finds optimal jump step size to minimize comparisons
     */
    public int jumpSearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int n = arr.length;
        // Find optimal block size to jump
        int step = (int) Math.floor(Math.sqrt(n));

        // Find the block where the element is present (if it is present)
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return -1; // Element not in array
        }

        // Linear search for target in the block
        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) return -1; // Element not in block
        }

        // If element is found
        if (arr[prev] == target) return prev;

        return -1; // Element not in array
    }

    /**
     * Interpolation Search
     * Works best on uniformly distributed sorted arrays
     * Time Complexity: O(log log n) for uniform distribution, O(n) worst case
     * Space Complexity: O(1)
     * Uses value-based probing rather than the middle (like binary search)
     */
    public int interpolationSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right && target >= arr[left] && target <= arr[right]) {
            // Formula for probing position (weighted by value)
            int pos;
            // Avoid division by zero
            if (arr[right] == arr[left]) {
                pos = left;
            } else {
                pos = left + ((target - arr[left]) * (right - left)) / (arr[right] - arr[left]);
            }

            if (arr[pos] == target) {
                return pos; // Found target
            }

            if (arr[pos] < target) {
                left = pos + 1; // Search right
            } else {
                right = pos - 1; // Search left
            }
        }

        return -1; // Target not found
    }

    /**
     * Exponential Search
     * Works on sorted arrays
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Good for unbounded/infinite arrays as it quickly finds a range
     */
    public int exponentialSearch(int[] arr, int target) {
        int n = arr.length;

        // If target is the first element
        if (arr[0] == target) {
            return 0;
        }

        // Find the range for binary search by repeated doubling
        int i = 1;
        while (i < n && arr[i] <= target) {
            i = i * 2;
        }

        // Call binary search on the range found
        return binarySearchInRange(arr, target, i / 2, Math.min(i, n - 1));
    }

    private int binarySearchInRange(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Ternary Search
     * Divides array into three parts instead of two
     * Time Complexity: O(log3 n) which is worse than binary search in practice
     * Space Complexity: O(1) iterative, O(log3 n) recursive
     * Useful for unimodal functions
     */
    public int ternarySearch(int[] arr, int target) {
        return ternarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    private int ternarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        // Find two mid points that divide array into three parts
        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;

        // Check if target is at either mid point
        if (arr[mid1] == target) {
            return mid1;
        }
        if (arr[mid2] == target) {
            return mid2;
        }

        // Recursively search in one of the three parts
        if (target < arr[mid1]) {
            // Search in first part
            return ternarySearchRecursive(arr, target, left, mid1 - 1);
        } else if (target > arr[mid2]) {
            // Search in third part
            return ternarySearchRecursive(arr, target, mid2 + 1, right);
        } else {
            // Search in middle part
            return ternarySearchRecursive(arr, target, mid1 + 1, mid2 - 1);
        }
    }

    /**
     * Fibonacci Search
     * Uses Fibonacci numbers to divide the array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Particularly useful for arrays that are stored on disk
     */
    public int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;

        // Initialize Fibonacci numbers
        int fibM2 = 0; // (m-2)'th Fibonacci number
        int fibM1 = 1; // (m-1)'th Fibonacci number
        int fibM = fibM1 + fibM2; // m'th Fibonacci number

        // Find the smallest Fibonacci number >= n
        while (fibM < n) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM1 + fibM2;
        }

        // Marks the eliminated range from front
        int offset = -1;

        // While there are elements to be inspected
        while (fibM > 1) {
            // Check if fibM2 is a valid index
            int i = Math.min(offset + fibM2, n - 1);

            // If target is greater than the value at index i, eliminate the subarray from offset to i
            if (arr[i] < target) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            }
            // If target is less than the value at index i, eliminate the subarray after i+1
            else if (arr[i] > target) {
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            }
            // Element found
            else {
                return i;
            }
        }

        // Check for the last element
        if (fibM1 == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }

        return -1; // Element not found
    }

    /**
     * Binary Search with Lower and Upper Bound
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Used to find insertion points or closest elements
     */
    public int[] binarySearchBounds(int[] arr, int target) {
        int[] bounds = new int[2];
        bounds[0] = lowerBound(arr, target); // Index of first element >= target
        bounds[1] = upperBound(arr, target); // Index of first element > target
        return bounds;
    }

    // Returns index of first element >= target (or array length if all elements are < target)
    private int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Returns index of first element > target (or array length if all elements are <= target)
    private int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * Meta Binary Search (One-sided Binary Search)
     * Time Complexity: O(log log n)
     * Space Complexity: O(1)
     * Uses bit manipulation to search faster in some cases
     */
    public int metaBinarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int n = arr.length;
        int power = 1;
        while (power * 2 < n) power *= 2; // Find largest power of 2 less than n

        int pos = 0;
        for (int currentPower = power; currentPower > 0; currentPower /= 2) {
            int newPos = pos + currentPower;
            if (newPos < n && arr[newPos] <= target) {
                pos = newPos;
            }
        }

        if (arr[pos] == target) return pos;
        return -1;
    }
}
