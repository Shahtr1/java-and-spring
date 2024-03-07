package Producer_Consumer_Problem;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(5);
        Thread producer = new Thread(new Producer(data), "producer");
        Thread consumer = new Thread(new Consumer(data), "consumer");
        producer.start();
        consumer.start();
    }
}