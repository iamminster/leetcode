package io.iamminster.coding.leetcode.concurrency.blocking_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
    private int[] items;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("queue capacity should larger than 0");
        size = 0;
        front = 0;
        rear = 0;
        this.capacity = capacity;
        items = new int[capacity];
        this.lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        final var lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (size == capacity)
                notFull.await();
            final int[] items = this.items;
            items[rear] = element;
            rear = (rear + 1) % capacity;
            size++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        final var lock = this.lock;
       lock.lockInterruptibly();
       try {
           while (size == 0)
               notEmpty.await();
           final int[] items = this.items;
           var item = items[front];
           front = (front + 1) % capacity;
           size--;
           notFull.signalAll();
           return item;
       } finally {
           lock.unlock();
       }
    }

    public int size() {
        final var lock = this.lock;
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}
