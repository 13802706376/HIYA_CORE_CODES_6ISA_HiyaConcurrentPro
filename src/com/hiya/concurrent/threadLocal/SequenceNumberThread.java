package com.hiya.concurrent.threadLocal;

public class SequenceNumberThread implements Runnable
{
    private SequenceNumber sn;
    public SequenceNumberThread(SequenceNumber sn)
    {
        this.sn = sn;
    }

    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.println("thread[" + Thread.currentThread().getName() +"] sn[" + sn.getNextNum() + "]");
        }
    }
}