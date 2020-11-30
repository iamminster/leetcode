package io.iamminster.coding.leetcode.concurrency.zero_even_odd;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private Lock myLock;
    private Condition myCondition;
    private Condition mySecondCondition;
    private int counter = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
        myLock = new ReentrantLock();
        myCondition = myLock.newCondition();
        mySecondCondition = myLock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        myLock.lock();
        try {
            for (int i = 0; i < n; ++i) {
                printNumber.accept(0);
                mySecondCondition.signalAll();
                myCondition.await();
            }
        } finally {
            myLock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        myLock.lock();
        try {
            while (counter <= n) {
                while (counter % 2 != 0) {
                    mySecondCondition.await();
                }
                printNumber.accept(counter++);
                myCondition.signalAll();
            }
        } finally {
            myLock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        myLock.lock();
        try {
            while (counter <= n) {
                while (counter % 2 == 0) {
                    mySecondCondition.await();
                }
                printNumber.accept(counter++);
                myCondition.signalAll();
            }
        } finally {
            myLock.unlock();
        }
    }

    public void test() {
        Set<String > testSet = ConcurrentHashMap.<String>newKeySet();
    }

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(10);
        AtomicInteger b = new AtomicInteger(10);
        Integer c = new Integer(10);
        Integer d = new Integer(10);
        System.out.println(a.equals(b));
        System.out.println(c.equals(d));
    }
}
