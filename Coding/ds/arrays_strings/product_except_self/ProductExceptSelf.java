package ds.arrays_strings.product_except_self;

public class ProductExceptSelf {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        int[] output = new int[nums.length];

        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = nums[i - 1] * output[i - 1];
        }

        var r = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= r;
            r *= nums[i];
        }

        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
    }
}
