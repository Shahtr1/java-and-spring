package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int findDuplicate(int[] nums) {
        // Phase 1: Detect intersection point in the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow]; // one step
            fast = nums[nums[fast]]; // two steps
        } while (slow != fast);

        // Phase 2: Find the entrance to the cycle (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow; // the duplicate number
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 4, 0, 2, 14, 13, 14 }; // You can change this array
        System.out.println("Duplicate: " + findDuplicate(nums));
    }
}
