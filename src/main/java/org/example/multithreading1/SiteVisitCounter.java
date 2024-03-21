package org.example.multithreading1;

import java.util.concurrent.atomic.AtomicInteger;

public interface SiteVisitCounter {
    void incrementVisitCounter() throws InterruptedException;
    int getVisitCount() throws InterruptedException;
}
