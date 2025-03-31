package threads;

/**
  problem: Get data from 2 domain API parallel and process them once both
  APIs are returned

  Thread which executes join will wait hte thread to execute before moving foreword
  Here main thread will wait for both thread to complete before processing
  Join is one way to achieve synchronization
 */
public class JoinThreadExample {
    public static void main(String[] args) {
        API1 api1 = new API1();
        API2 api2 = new API2();

        Thread api1Thread = new Thread(api1, "API1");
        Thread api2Thread = new Thread(api2, "API2");


        api1Thread.start();
        api2Thread.start();


        try {
            api1Thread.join();
            api2Thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Processing data from API1 and API2");

    }
}

/**
 * MIMICS API1 call
 */
class API1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(" Retrieving Data from API1");
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * MIMICS API2 call
 */
class API2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(" Retrieving Data from API2");
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
