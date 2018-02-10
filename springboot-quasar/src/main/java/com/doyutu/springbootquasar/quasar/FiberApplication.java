package com.doyutu.springbootquasar.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class FiberApplication {

    public static void fiber() throws InterruptedException {
        //启动纤程数
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
                Strand.sleep(1_000_000);
            }).start();
        }
        System.out.println(System.currentTimeMillis() - startTime);
        //阻止程序退出
        latch.await();
    }
}
