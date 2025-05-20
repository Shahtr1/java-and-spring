package threads.deadlock;

public class Main {
    private static String firstName = "Shahrukh";
    private static String lastName = "Tramboo";

    private class InnerMain1 extends Thread {
        public void run() {
            synchronized (firstName) {
                System.out.println("Thread1 locked firstName");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread1 trying to lock lastName...");

                synchronized (lastName) {
                    Main.firstName = "Thread1_firstName";
                    Main.lastName = "Thread1_lastName";

                    System.out.println("firstName from Thread1 " + Main.firstName);
                    System.out.println("lastName from Thread1 " + Main.lastName);
                }
            }

        }
    }

    private class InnerMain2 extends Thread {
        public void run() {
            synchronized (lastName) {
                System.out.println("Thread2 locked lastName");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread2 trying to lock firstName...");
                synchronized (firstName) {
                    Main.firstName = "Thread2_firstName";
                    Main.lastName = "Thread2_lastName";

                    System.out.println("firstName from Thread2 " + Main.firstName);
                    System.out.println("lastName from Thread2 " + Main.lastName);
                }
            }

        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        InnerMain1 thread1 = main.new InnerMain1();
        InnerMain2 thread2 = main.new InnerMain2();

        thread1.start();
        thread2.start();
    }

}
