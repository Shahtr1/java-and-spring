package threads.execution_models.producer_consumer_problem.BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    BlockingQueue<String> q;

    public Consumer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1500);

                String msg = q.take();

                String name = Thread.currentThread().getName();
                System.out.println(name + " has consumed msg:: " + msg);
            }
        } catch (InterruptedException e) {
        }
    }
}
