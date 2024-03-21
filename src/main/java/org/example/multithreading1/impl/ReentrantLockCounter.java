package org.example.multithreading1.impl;

import org.example.multithreading1.SiteVisitCounter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    private final Lock lock = new ReentrantLock(true);
    volatile int visitCount = 0;

    @Override
    public void incrementVisitCounter() throws InterruptedException {
        lock.lock();
        Thread.sleep(100);
        try {
            visitCount++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() throws InterruptedException {
        Thread.sleep(100);
        return visitCount;
    }
}
