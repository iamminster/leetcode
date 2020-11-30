package io.iamminster.coding.leetcode.concurrency.print_in_order;

import java.util.concurrent.CountDownLatch;

public class FooCountDownLatch {

    CountDownLatch secondCount = new CountDownLatch(1);
    CountDownLatch thirdCount = new CountDownLatch(1);

    public FooCountDownLatch() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.

        printFirst.run();
        secondCount.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        secondCount.await();
        printSecond.run();
        thirdCount.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        thirdCount.await();
        printThird.run();
    }
}
