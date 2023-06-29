package com.concurrent.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyncExecutor extends AsyncExecutor {

    @Override
    public void execute(Runnable callback) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            System.out.println("Perform execution synchronously");
            super.execute(() -> {
                try {
                    lock.lock();
                    condition.signal();
                    callback.run();
                } finally {
                    lock.unlock();
                }
            });
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
