package io.iamminster.coding.leetcode.concurrency.dinning_philosophers;

public class Philosopher implements Runnable {
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }


    @Override
    public void run() {
        try {
            while (true) {
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Picked up left fork");
                    synchronized (rightFork) {
                        doAction(System.nanoTime() + ": Picked up right fork - eating");
                        doAction(System.nanoTime() + ": Put down right fork");
                    }
                    doAction(System.nanoTime() + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
//        Thread.sleep((int) (Math.random() * 100));
    }

    public static void main(String[] args) throws Exception {
        Philosopher[] philosophers = new Philosopher[8];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; ++i) {
            forks[i] = new Object();
        }

        for (int i = 0; i < forks.length; ++i) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            if (i == forks.length - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else
                philosophers[i] = new Philosopher(leftFork, rightFork);
            new Thread(philosophers[i], "Philosopher " + (i + 1)).start();
        }

    }
}
