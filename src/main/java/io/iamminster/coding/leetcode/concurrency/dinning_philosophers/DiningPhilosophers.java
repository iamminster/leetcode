package io.iamminster.coding.leetcode.concurrency.dinning_philosophers;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {

    private final Semaphore[] locks;

    public DiningPhilosophers() {
        locks = new Semaphore[5];
        for (int i = 0; i < locks.length; ++i)
            locks[i] = new Semaphore(1, true);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        var leftLock = locks[philosopher];
        var rightLock = locks[(philosopher + 1) % locks.length];

        if (philosopher % 2 == 0) {
            leftLock.acquire();
            pickLeftFork.run();
            rightLock.acquire();
            pickRightFork.run();
            eat.run();
            putRightFork.run();
            rightLock.release();
            putLeftFork.run();
            leftLock.release();
        } else {
            rightLock.acquire();
            pickRightFork.run();
            leftLock.acquire();
            pickLeftFork.run();
            eat.run();
            putLeftFork.run();
            leftLock.release();
            putRightFork.run();
            rightLock.release();

        }
    }
}
