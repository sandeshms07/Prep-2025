package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Problem,
 * 1. Consumer threads will remove one element at a time from the bucket
 *    infinitely when bucket is not empty
 * 2. Producer threads will add an element into a bucket when no of elements in the thread is less than 10
 *
 * synchronised is a block of code that is executed only by a single thread at any given point of time
 * synchronised block need a Lock-key for any thread to enter into the block, that key should be
 * the resource that we are trying to protect.
 *
 * In the below example, We are using bucket as a lock-key for both Producer and Consumer thread.
 * Hence, among all thread we created, at any given point of time, only one thread will be inside
 * the synchronised block
 *
 */
public class ProducerConsumerProblem {

    public static final List<Integer> bucket = new ArrayList<>();

    public static void main(String[] args) {
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        Thread producer1Thread  = new Thread(producer1, "Producer1");
        Thread producer2Thread  = new Thread(producer2, "Producer2");
        Thread consumer1Thread  = new Thread(consumer1, "Consumer");
        Thread consumer2Thread  = new Thread(consumer2, "Consumer2");

        producer1Thread.start();
        producer2Thread.start();
        consumer1Thread.start();
        consumer2Thread.start();
    }

}

class Consumer implements Runnable {
    @Override
    public void run() {
            while (true) {
                synchronized (ProducerConsumerProblem.bucket) {
                if (!ProducerConsumerProblem.bucket.isEmpty()) {
                    int n = ProducerConsumerProblem.bucket.getFirst();
                    ProducerConsumerProblem.bucket.removeFirst();
                    System.out.println(Thread.currentThread().getName() + " removed " + n +
                            " " + "from the bucket");
                }
            }
        }
    }
}

class Producer implements Runnable {
    Random r  = new Random();
    @Override
    public void run() {
        while (true) {
            synchronized (ProducerConsumerProblem.bucket) {
                if (ProducerConsumerProblem.bucket.size() < 10) {
                    int n = r.nextInt();
                    ProducerConsumerProblem.bucket.add(n);
                    System.out.println(Thread.currentThread().getName() + " added " + n +
                            " " + "to the bucket");
                }
            }
        }
    }
}

