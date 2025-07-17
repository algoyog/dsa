package org.algoyog.algos.ds.arrays;

import java.util.Arrays;

/**
 * Two Pointer Technique is commonly used for array and string problems
 * Most common patterns: 
 * 1. Start from both ends and move towards the middle
 * 2. Fast and slow pointers moving in the same direction
 */
public class TwoPointers {

    /**
     * Two Sum Problem: Find two numbers that add up to a target
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(1) if we ignore the input array
     */
    public int[] twoSum(int[] nums, int target) {
        // Create a copy to avoid modifying the input array
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);

        int left = 0;
        int right = sortedNums.length - 1;

        while (left < right) {
            int sum = sortedNums[left] + sortedNums[right];

            if (sum == target) {
                // For the original indices, we need to search the original array
                int[] result = new int[2];
                boolean foundFirst = false;

                for (int i = 0; i < nums.length; i++) {
                    if (!foundFirst && nums[i] == sortedNums[left]) {
                        result[0] = i;
                        foundFirst = true;
                    } else if (nums[i] == sortedNums[right]) {
                        result[1] = i;
                    }
                }

                return result;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[0]; // No solution found
    }

    /**
     * Three Sum Problem: Find all unique triplets that sum to zero
     * Time Complexity: O(n²) - One loop O(n) and two pointers O(n)
     * Space Complexity: O(1) if we exclude the output array
     */
    public void threeSum(int[] nums) {
        Arrays.sort(nums);
        System.out.println("Finding triplets that sum to zero:");

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i]; // We want to find pairs that sum to -nums[i]

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    System.out.println(nums[i] + ", " + nums[left] + ", " + nums[right]);

                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    /**
     * Container With Most Water: Find two lines that together with x-axis forms a container that holds the most water
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxArea(int[] height) {
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // Width is right - left
            // Height is the minimum of the two heights
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int water = width * h;

            maxWater = Math.max(maxWater, water);

            // Move the pointer with the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    /**
     * Fast and Slow Pointers: Find the middle of a linked list
     * This is a conceptual example - in a real solution, you'd have a LinkedList implementation
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void findMiddleOfLinkedList() {
        // Conceptual example showing the pattern
        System.out.println("To find middle of linked list:");
        System.out.println("ListNode slow = head;");
        System.out.println("ListNode fast = head;");
        System.out.println("while (fast != null && fast.next != null) {");
        System.out.println("    slow = slow.next;       // Move one step");
        System.out.println("    fast = fast.next.next;  // Move two steps");
        System.out.println("}");
        System.out.println("// slow is now at the middle node");
    }
}
