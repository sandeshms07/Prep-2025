package threads;

/**
 Possible outputs
 1)      END of main thread
         0 2 4 6 8 10

 2)      0 2 4 6 8 10
         END of main thread
  Our goal is to have one possible output irrespective of how many times we run it


 Thread lifecycle
    1. new {@code EvenNumbers t1 = new EvenNumbers();}
    2. runnable {@code t1.start(); }
        a. Thread can start after this point depending on hardware availability
        b. JVM creates a pool of runnable threads and picks them up for execution.
    3. running
        a. A thread repeatedly moves from tunable to running until it is dead
    4. Blocked
        a. sleep() will move thread to blocked pool
        b. Thread moves to runnable state after sleep executed
    5. Dead
        a. Thread has completed its execution
 */
public class ThreadsBasics {
    // Starting point of main thread
    public static void main(String[] args) {

        EvenNumbers t1 = new EvenNumbers(); // thread state - new
        t1.start(); // thread starts after this is executed - thread state - runnable
        System.out.println("END of main thread " + "  -> " + Thread.currentThread().getName());

        //-----------ODD number thread-------------

        OddNumbers oddNumbers = new OddNumbers();
        Thread oddNumberThread = new Thread(oddNumbers, "Odd Number thread");
        Thread oddNumberThread2 = new Thread(oddNumbers, "Odd Number thread2");
        oddNumberThread.start();
        oddNumberThread2.start();
    }
}

/**
 * One way to create thread is to extend Thread class and implement run method.
 * Not recommend as we cannot extend any other class like Serializable or Cloneable.
 */
class EvenNumbers extends Thread {

    /**
     * Thread.sleep has throws InterruptedException in its signature
     * But run method does not have this, so we have to surround sleep method inside try catch
     */
    @Override
    public void run() { // starting point of thread
        Thread.currentThread().setName("EVEN numbers thread");
        try {
            for (int i = 0; i <= 10; i = i + 2) {
                System.out.println(i + " ->  " + Thread.currentThread().getName());
                Thread.sleep(1000); // Blocked state
            }
        } catch (InterruptedException _) {

        }
    } // thread state - DEAD
}

/**
 * Implementing Runnable interface is recommended way to create thread
 */
class OddNumbers implements Runnable {

    @Override
    public void run() {
        for (int i=1;i<11;i=i+2) {
            System.out.println(i + " -> " + Thread.currentThread().getName());
        }
    }
}


