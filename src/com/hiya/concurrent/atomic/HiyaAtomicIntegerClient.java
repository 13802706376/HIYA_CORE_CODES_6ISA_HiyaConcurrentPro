package com.hiya.concurrent.atomic;

public class HiyaAtomicIntegerClient
{
    public static void main(String[] args)
    {
        HiyaAtomicInteger ad = new HiyaAtomicInteger();
        for (int i = 0; i < 100; i++)
        {
            new Thread(ad).start();
        }
    }
}
