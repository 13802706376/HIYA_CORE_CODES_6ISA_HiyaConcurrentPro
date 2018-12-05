package com.hiya.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class BikeExchangerRunnable implements Runnable
{
    private Exchanger<String> exchanger;

    public BikeExchangerRunnable(Exchanger<String> exchanger)
    {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(Thread.currentThread().getName() + "-BikeExchangerRunnable: " + exchanger.exchange("Bike"));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}