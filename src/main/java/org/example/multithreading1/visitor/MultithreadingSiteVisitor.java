package org.example.multithreading1.visitor;

import org.example.multithreading1.SiteVisitCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter siteVisitCounter;
    private List<Thread> threads = new ArrayList<>();
    private Long totalWorkingTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter1) {
        this.siteVisitCounter = siteVisitCounter1;
    }

    public void visitMultithread(int numOfThreads) {
        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    siteVisitCounter.incrementVisitCounter();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.start();
            threads.add(thread);
        }
    }

    public void waitUntilAllVisited() throws InterruptedException {
        Long startTime = System.nanoTime();
        for (Thread thread : threads) {
            thread.join();
        }
        Long endTime = System.nanoTime();
        totalWorkingTime = endTime - startTime;
    }

    public double getTotalTimeOfHandling() {
        System.out.println("total time of handling - " + (double) totalWorkingTime / 1_000_000_000);
        return (double) totalWorkingTime / 1_000_000_000;
    }
}
