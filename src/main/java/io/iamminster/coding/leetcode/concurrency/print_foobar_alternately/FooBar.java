package io.iamminster.coding.leetcode.concurrency.print_foobar_alternately;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;
    private volatile int switcher = 1; // 1: foo, 2: bar
    private Lock fbLock;
    private Condition fbCondition;

    public FooBar(int n) {
        this.n = n;
        fbLock = new ReentrantLock();
        fbCondition = fbLock.newCondition();

    }

    public void foo(Runnable printFoo) throws InterruptedException {
        fbLock.lock();
        try {
            for (int i = 0; i < n; i++) {
                while (switcher == 2) {
                    fbCondition.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                switcher = 2;
                fbCondition.signalAll();
            }
        } finally {
            fbLock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        fbLock.lock();
        try {
            for (int i = 0; i < n; i++) {
                while (switcher == 1)
                    fbCondition.await();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                switcher = 1;
                fbCondition.signalAll();
            }
        } finally {
            fbLock.unlock();
        }
    }

}
