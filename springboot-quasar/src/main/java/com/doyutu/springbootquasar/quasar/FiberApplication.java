package com.doyutu.springbootquasar.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class FiberApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException, SuspendExecution {
        int fiberNumber = 1_000_000;
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger counter = new AtomicInteger(0);
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < fiberNumber; i++) {
            new Fiber(() -> {
                counter.incrementAndGet();
                if (counter.get() == fiberNumber) {
                    System.out.println("done");
                }
                Strand.sleep(1000000);
            }).start();
        }
        System.out.println(System.currentTimeMillis() - startTime);
        latch.await();
    }
}
