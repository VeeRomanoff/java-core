package org.example.multithreading1.impl;

import org.example.multithreading1.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private AtomicInteger visitCounter = new AtomicInteger(0);

    @Override
    public void incrementVisitCounter() throws InterruptedException {
        Thread.sleep(100);
        visitCounter.incrementAndGet();
    }

    @Override
    public int getVisitCount() throws InterruptedException {
        Thread.sleep(100);
        return visitCounter.get();
    }
}
