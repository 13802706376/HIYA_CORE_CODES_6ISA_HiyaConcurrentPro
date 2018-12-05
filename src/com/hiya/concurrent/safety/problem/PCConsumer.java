package com.hiya.concurrent.safety.problem;

import java.util.Queue;

public class PCConsumer implements Runnable
{
    private final Queue<Integer> sharedQ;
    public PCConsumer(Queue<Integer> sharedQ) 
    {
        this.sharedQ = sharedQ;
    }

    @Override
    public void run()
    {
        while (true)
        {
            synchronized (sharedQ)
            {
                // waiting condition - wait until Queue is not empty
                while (sharedQ.size() == 0)
                {
                    try
                    {
                        System.out.println("Queue is empty, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                int number = sharedQ.poll();
                System.out.println("consuming : " + number);
                sharedQ.notify();

                // termination condition
                if (number == 3)
                {
                    break;
                }
            }
        }
    }
}
