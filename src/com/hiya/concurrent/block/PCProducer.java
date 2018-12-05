package com.hiya.concurrent.block;

import java.util.concurrent.BlockingQueue;

public class PCProducer implements Runnable
{
    private final BlockingQueue<Integer> sharedQueue;

    public PCProducer(BlockingQueue<Integer> sharedQueue)
    {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
            } catch (InterruptedException ex)
            {

            }
        }
    }
}