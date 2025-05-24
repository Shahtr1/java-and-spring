package ds.arrays_strings.max_sub_array;

public class MaximumSubArray {
    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int maxSum = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = max(nums[i], nums[i] + sum);
            maxSum = max(sum, maxSum);
        }

        System.out.println("maxSum " + maxSum);
    }

    public static int max(int i, int j) {
        return i > j ? i : j;
    }

}
