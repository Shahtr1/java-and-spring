package Producer_Consumer_Problem.BlockingQueue;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(q));
        producer.start();
        for (int i = 1; i <= 5; i++) {
            new Thread(new Consumer(q), "Consumer " + i).start();
        }
    }

}
