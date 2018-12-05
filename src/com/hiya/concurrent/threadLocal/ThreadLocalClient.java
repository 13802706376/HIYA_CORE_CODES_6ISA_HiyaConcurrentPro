package com.hiya.concurrent.threadLocal;

public class ThreadLocalClient
{
    /**
     * 考察输出的结果信息，我们发现每个线程所产生的序号虽然都共享同一个SequenceNumber实例，但它们并没有发生相互干扰的情况，而是各自产生独立的序列号，
     * 是因为我们通过ThreadLocal为每一个线程提供了单独的副本。
     * @param args
     */
    public static void main(String[] args)
    {
        SequenceNumber sn = new SequenceNumber();
        new Thread(new SequenceNumberThread(sn)).start();
        new Thread(new SequenceNumberThread(sn)).start();
        new Thread(new SequenceNumberThread(sn)).start();
    }
}