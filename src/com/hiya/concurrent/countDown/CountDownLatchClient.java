package com.hiya.concurrent.countDown;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchClient
{
    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(3);//计数器初始值设置为3
        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);
        new Thread(waiter).start();
        new Thread(decrementer).start();
        Thread.sleep(4000);
    }
}
