package threads;

/**
 * Deadlock - Thread acquires lock resource A and waits for resource B,
 * at the same time another thread acquires lock resource B and waits for resource A.
 */
public class DeadLockExample {

    final Object lock1 =  new Object();
    final Object lock2 =  new Object();

    public void m1() {
        // Thread 1 acquires lock for lock1
        synchronized (lock1) {
            // Thread 1 continuously wait for lock1 which is help by m2()
            synchronized (lock2) {

            }
        }
    }

    public void m2() {
        // Thread 2 acquires lock for lock1
        synchronized (lock2) {
            // Thread 2 continuously wait for lock2 which is held by m1()
            synchronized (lock1) {

            }
        }
    }
}

