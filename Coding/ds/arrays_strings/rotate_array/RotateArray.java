package ds.arrays_strings.rotate_array;

public class RotateArray {
    static int[] nums = { 1, 2, 3, 4, 5, 6 };
    static int k = 3;

    public static void main(String[] args) {
        int n = nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + (i < nums.length - 1 ? "," : ""));
        }

    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
