package com.hiya.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerClient
{
    public static void main(String[] args) throws InterruptedException
    {
        Exchanger<String> exchanger = new Exchanger<>();
        CarExchangerRunnable car = new CarExchangerRunnable(exchanger);
        BikeExchangerRunnable bike = new BikeExchangerRunnable(exchanger);
        new Thread(car).start();
        new Thread(bike).start();
        System.out.println("Main end!");
    }
}
