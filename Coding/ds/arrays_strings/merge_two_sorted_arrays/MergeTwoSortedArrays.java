package ds.arrays_strings.merge_two_sorted_arrays;

public class MergeTwoSortedArrays {
    static int[] nums1 = { 1, 2, 3, 0, 0, 0 };
    static int m = 3;
    static int[] nums2 = { 2, 5, 6 };
    static int n = 3;

    public static void main(String[] args) {
        var tail = m + n - 1;
        var p1 = m - 1;
        var p2 = n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[tail--] = (nums1[p1] > nums2[p2]) ? nums1[p1--] : nums2[p2--];
        }

        while (p2 >= 0) {
            nums1[tail--] = nums2[p2--];
        }

        for (var i = 0; i < m + n; i++) {
            System.out.print(nums1[i] + " ");
        }
    }
}
