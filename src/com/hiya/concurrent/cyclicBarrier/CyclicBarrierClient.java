package com.hiya.concurrent.cyclicBarrier;
import java.util.concurrent.CyclicBarrier;
public class CyclicBarrierClient
{
    public static void main(String[] args) throws InterruptedException
    {
        Runnable actionAdd = new ActionAdd(); 
        Runnable actionUpdate = new ActionUpdate(); 
        Runnable actionDelete = new ActionDelete(); 
        
        CyclicBarrier barrier1 = new CyclicBarrier(3, actionAdd);// ��3���̵߳ȴ���ִ��actionAdd
        CyclicBarrier barrier2 = new CyclicBarrier(2, actionUpdate);// ��2���̵߳ȴ���ִ��actionUpdate
        CyclicBarrier barrier3 = new CyclicBarrier(1, actionDelete);// ��2���̵߳ȴ���ִ��actionUpdate
        
        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable3 = new CyclicBarrierRunnable(barrier1, barrier3);
        
        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
        new Thread(barrierRunnable3).start();
    }
}
