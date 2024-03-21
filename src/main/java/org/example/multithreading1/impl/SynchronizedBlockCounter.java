package org.example.multithreading1.impl;

import org.example.multithreading1.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private Integer visitCount = 0;

    @Override
    public void incrementVisitCounter() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(100);
            this.visitCount += 1;
        }
    }

    @Override
    public int getVisitCount() throws InterruptedException {
        Thread.sleep(100);
        return visitCount;
    }
}
