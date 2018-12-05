package com.hiya.concurrent.safety.problem;

import java.util.Queue;

public class PCProducer implements Runnable
{
    private final Queue<Integer> sharedQ;
    public PCProducer(Queue<Integer> sharedQ)
    {
        this.sharedQ = sharedQ;
    }

    @Override
    public void run()
    {
        for (int i = 1; i < 5; i++)
        {
            synchronized (sharedQ)
            {
                while (sharedQ.size() >= 3)
                {
                    try
                    {
                        System.out.println("Queue is full, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                System.out.println("producing : " + i);
                sharedQ.add(i);
                sharedQ.notify();
            }
        }
    }
}