package com.hiya.concurrent.countDown;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable
{
    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch)
    {
        this.latch = latch;
    }

    public void run()
    {
        try
        {
            Thread.sleep(1000);
            this.latch.countDown();// ��������1
            Thread.sleep(1000);
            this.latch.countDown();// ��������1
            Thread.sleep(1000);
            this.latch.countDown();// ��������1
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
