package org.example.multithreading1.impl;

import org.example.multithreading1.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class UnsynchronizedCounter implements SiteVisitCounter {

    int visitCount = 0;

    @Override
    public void incrementVisitCounter() throws InterruptedException {
        Thread.sleep(100);
        visitCount += 1;
    }

    @Override
    public int getVisitCount() throws InterruptedException {
        Thread.sleep(100);
        return visitCount;
    }
}
