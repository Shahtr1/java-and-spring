package ds.arrays_strings.sliding_window_maximim;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximim {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;

        int[] output = new int[nums.length - k + 1];
        int oIndex = 0;

        for (int i = 0; i < nums.length; i++) {

            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            if (i >= k - 1) {
                output[oIndex++] = nums[deque.peek()];
            }

        }

        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }

    }

}
