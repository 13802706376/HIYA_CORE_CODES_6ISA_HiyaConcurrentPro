package com.hiya.concurrent.block;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PCConsumer implements Runnable
{

    private final BlockingQueue<Integer> sharedQueue;

    public PCConsumer(BlockingQueue<Integer> sharedQueue)
    {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                System.out.println("Consumed: " + sharedQueue.take());
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}