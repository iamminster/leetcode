package io.iamminster.coding.leetcode.concurrency.blocking_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GenericBoundedBlockingQueue<T> {
    private Object[] items;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public GenericBoundedBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("queue capacity should larger than 0");
        this.capacity = capacity;
        items = new Object[capacity];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void enqueue(T element) throws InterruptedException {
        final var lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (size == capacity)
                notFull.await();
            final var items = this.items;
            items[rear] = element;
            rear = (rear + 1) % capacity;
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        final var lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (size == 0)
                notEmpty.await();
            final var items = this.items;
            final var item = items[front];
            // necessary remove object's reference for gc
            items[front] = null;
            front = (front + 1) % capacity;
            size--;
            notFull.signal();
            return (T)item;
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
