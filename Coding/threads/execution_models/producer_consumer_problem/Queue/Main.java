package threads.execution_models.producer_consumer_problem.Queue;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(5);
        Thread producer = new Thread(new Producer(data), "producer");
        producer.start();

        // Thread consumer = new Thread(new Consumer(data), "consumer");
        // consumer.start();

        for (int i = 1; i <= 5; i++) {
            new Thread(new Consumer(data), "Consumer " + i).start();
        }
    }
}