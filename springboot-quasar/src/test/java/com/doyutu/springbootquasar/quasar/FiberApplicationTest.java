package com.doyutu.springbootquasar.quasar;

import org.junit.Test;

import static org.junit.Assert.*;

public class FiberApplicationTest {
    @Test
    public void fiber() {
        try {
            FiberApplication.fiber();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}