package io.iamminster.coding.leetcode.concurrency.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

public class FooAtomic {
    private AtomicInteger anchor = new AtomicInteger(1);

    public FooAtomic() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        anchor.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        while (anchor.get() != 2) ;
        printSecond.run();
        anchor.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (anchor.get() != 3) ;
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
