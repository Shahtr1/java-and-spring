package Producer_Consumer_Problem.BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    BlockingQueue<String> q;
    final String[] messages = { "Hi!!", "How are you!!", "I love you!", "What's going on?!!", "That's really funny!!" };

    public Producer(BlockingQueue<String> q) {
        this.q = q;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (true) {
                Thread.sleep(500);

                q.put(messages[i]);

                System.out.println("Message published:: " + messages[i]);
                i = (i + 1) % messages.length;
            }
        } catch (InterruptedException e) {
        }
    }

}
