package com.hiya.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class CarExchangerRunnable implements Runnable
{
    private Exchanger<String> exchanger;

    public CarExchangerRunnable(Exchanger<String> exchanger)
    {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(Thread.currentThread().getName() + "-CarExchangerRunnable: " + exchanger.exchange("Car"));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}