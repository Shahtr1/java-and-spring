package Producer_Consumer_Problem;

public class Consumer implements Runnable {
    Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                data.consume();
            }
        } catch (InterruptedException e) {
        }
    }
}
