package io.iamminster.coding.leetcode.concurrency.fizzbuzz;

import javax.crypto.SealedObject;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzSemaphore {
    private int n;
    private final Semaphore fizz;
    private final Semaphore buzz;
    private final Semaphore fizzbuzz;
    private final Semaphore number;

    public FizzBuzzSemaphore(int n) {
        this.n = n;
        fizz = new Semaphore(0);
        buzz = new Semaphore(0);
        fizzbuzz = new Semaphore(0);
        number = new Semaphore(1);
    }

    private void releaseLock(int k) {
        if (k % 15 == 0) {
            fizzbuzz.release();
        } else if (k % 3 == 0 && k % 5 != 0) {
            fizz.release();
        } else if (k % 3 != 0 && k % 5 == 0) {
            buzz.release();
        } else number.release();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int k = 1; k <= n; ++k) {
            if (k % 3 == 0 && k % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                releaseLock(k + 1);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int k = 1; k <= n; ++k) {
            if (k % 3 != 0 && k % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                releaseLock(k + 1);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int k = 1; k <= n; ++k) {
            if (k % 15 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                releaseLock(k + 1);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int k = 1; k <= n; ++k) {
            if (k % 3 != 0 && k % 5 != 0) {
                number.acquire();
                printNumber.accept(k);
                releaseLock(k + 1);
            }
        }
    }
}
