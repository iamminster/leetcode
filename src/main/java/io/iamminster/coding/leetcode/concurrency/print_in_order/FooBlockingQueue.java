package io.iamminster.coding.leetcode.concurrency.print_in_order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class FooBlockingQueue {

    private BlockingQueue<Integer> secondQueue = new LinkedBlockingQueue<Integer>();
    private BlockingQueue<Integer> thirdQueue = new LinkedBlockingQueue<Integer>();

    public FooBlockingQueue() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondQueue.put(2);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondQueue.take();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        thirdQueue.put(3);
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        thirdQueue.take();
        printThird.run();
    }
}
