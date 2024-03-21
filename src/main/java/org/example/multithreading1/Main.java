package org.example.multithreading1;

import org.example.multithreading1.impl.*;
import org.example.multithreading1.visitor.MultithreadingSiteVisitor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerCounter atomicIntegerCounter = new AtomicIntegerCounter();
        MultithreadingSiteVisitor atomicCounterSiteVisitor = new MultithreadingSiteVisitor(atomicIntegerCounter);

        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();
        MultithreadingSiteVisitor reentrantCounterSiteVisitor = new MultithreadingSiteVisitor(reentrantLockCounter);

        SynchronizedBlockCounter synchronizedBlockCounter = new SynchronizedBlockCounter();
        MultithreadingSiteVisitor synchronizedBlockCounterSiteVisitor = new MultithreadingSiteVisitor(synchronizedBlockCounter);

        UnsynchronizedCounter unsynchronizedCounter = new UnsynchronizedCounter();
        MultithreadingSiteVisitor unsychronizedCounterSiteVisitor = new MultithreadingSiteVisitor(unsynchronizedCounter);

        VolatileCounter volatileCounter = new VolatileCounter();
        MultithreadingSiteVisitor volatileCounterSiteVisitor = new MultithreadingSiteVisitor(volatileCounter);


        // AtomicIntegerCounter
        atomicCounterSiteVisitor.visitMultithread(100);
        atomicCounterSiteVisitor.waitUntilAllVisited();
        atomicCounterSiteVisitor.getTotalTimeOfHandling();
        System.out.println(atomicIntegerCounter.getVisitCount());

        // ReentrantLockCounter
        reentrantCounterSiteVisitor.visitMultithread(100);
        reentrantCounterSiteVisitor.waitUntilAllVisited();
        reentrantCounterSiteVisitor.getTotalTimeOfHandling();
        System.out.println(reentrantLockCounter.getVisitCount());

        // SynchronizedBlock
        synchronizedBlockCounterSiteVisitor.visitMultithread(100);
        synchronizedBlockCounterSiteVisitor.waitUntilAllVisited();
        synchronizedBlockCounterSiteVisitor.getTotalTimeOfHandling();
        System.out.println(synchronizedBlockCounter.getVisitCount());

        // UnsynchronizedBlock
        unsychronizedCounterSiteVisitor.visitMultithread(100);
        unsychronizedCounterSiteVisitor.waitUntilAllVisited();
        unsychronizedCounterSiteVisitor.getTotalTimeOfHandling();
        System.out.println(unsynchronizedCounter.getVisitCount());
0
        // Volatile
        volatileCounterSiteVisitor.visitMultithread(100);
        volatileCounterSiteVisitor.waitUntilAllVisited();
        volatileCounterSiteVisitor.getTotalTimeOfHandling();
        System.out.println(volatileCounter.getVisitCount());
    }
}
