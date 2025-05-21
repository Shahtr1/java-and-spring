package fork_join;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

    static class SumTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 20;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // Small enough, compute directly
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Split task
                int middle = start + (end - start) / 2;
                SumTask task1 = new SumTask(array, start, middle);
                SumTask task2 = new SumTask(array, middle, end);

                // Fork the subtasks
                task1.fork();
                task2.fork();

                // Join the results
                return task1.join() + task2.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        System.out.println("Sum: " + result);
    }
}