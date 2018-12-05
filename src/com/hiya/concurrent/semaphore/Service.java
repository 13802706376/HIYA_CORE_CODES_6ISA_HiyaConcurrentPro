package com.hiya.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class Service
{
    private Semaphore semaphore = new Semaphore(1);// 同一个时刻，只有一个线程可以执行acquire和release部分的代码

    public void testMethod()
    {
        try
        {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " begin timer=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "   end timer=" + System.currentTimeMillis());
            semaphore.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
