package com.hiya.concurrent.countDown;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable
{
    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch)
    {
        this.latch = latch;
    }

    public void run()
    {
        try
        {
            latch.await();// ×èÈûÔÚÕâ±ß
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}
