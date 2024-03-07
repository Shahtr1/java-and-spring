package Producer_Consumer_Problem;

public class Producer implements Runnable {

    final String[] messages = { "Hi!!", "How are you!!", "I love you!", "What's going on?!!", "That's really funny!!" };

    Data data;

    public Producer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (true) {
                Thread.sleep(1000);
                data.publish(messages[i]);
                i = (i + 1) % messages.length;
            }
        } catch (InterruptedException e) {
        }
    }
}
