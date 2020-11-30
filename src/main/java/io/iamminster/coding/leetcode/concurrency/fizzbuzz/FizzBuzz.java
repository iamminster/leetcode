package io.iamminster.coding.leetcode.concurrency.fizzbuzz;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private AtomicInteger counter;

    public FizzBuzz(int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int x;
        while ((x = counter.intValue()) <= n) {
            synchronized (this) {
                if (x == counter.intValue() && counter.intValue() % 15 != 0 && counter.intValue() % 3 == 0) {
                    printFizz.run();
                    counter.incrementAndGet();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int x;
        while ((x = counter.intValue()) <= n) {
            synchronized (this) {
                if (x == counter.intValue() && counter.intValue() % 15 != 0 && counter.intValue() % 5 == 0) {
                    printBuzz.run();
                    counter.incrementAndGet();
                }
            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int x;
        while ((x = counter.intValue()) <= n) {
            synchronized (this) {
                if (x == counter.intValue() && counter.intValue() % 15 == 0) {
                    printFizzBuzz.run();
                    counter.incrementAndGet();
                }
            }
        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        int x;
        while ((x = counter.intValue()) <= n) {
            synchronized (this) {
                if (x == counter.intValue() && counter.intValue() % 3 != 0 && counter.intValue() % 5 != 0) {
                    printNumber.accept(counter.intValue());
                    counter.incrementAndGet();
                }
            }
        }
    }
}

