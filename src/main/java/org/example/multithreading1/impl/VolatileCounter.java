package org.example.multithreading1.impl;

import org.example.multithreading1.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileCounter implements SiteVisitCounter {

    volatile int visitCount = 0;

    @Override
    public void incrementVisitCounter() throws InterruptedException {
        Thread.sleep(100);
        visitCount++;
    }

    @Override
    public int getVisitCount() throws InterruptedException {
        Thread.sleep(100);
        return visitCount;
    }
}
