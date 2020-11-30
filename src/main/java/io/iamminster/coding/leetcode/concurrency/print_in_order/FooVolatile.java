package io.iamminster.coding.leetcode.concurrency.print_in_order;

public class FooVolatile {
    private volatile int anchor = FIRST;
    public static int FIRST = 1;
    public static int SECOND = 2;
    public static int THIRD = 3;

    public FooVolatile() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        anchor = SECOND;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (anchor != SECOND) {
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        anchor = THIRD;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (anchor != THIRD) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
