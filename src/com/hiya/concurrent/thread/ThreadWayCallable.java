package com.hiya.concurrent.thread;

import java.util.concurrent.Callable;

/**
 * ʵ���� ThreadҲ�Ǽ̳� Runnable�ӿ�
 * Runnable��һ�����췽�� ������ Runnableʵ���� 
 * @author zjq
 *
 */
public class ThreadWayCallable implements Callable<String>
{
    private String name;

    public ThreadWayCallable(String name) 
    {  
        this.name=name;  
    }

    @Override
    public String call() throws Exception
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(name + "����  :  " + i);
            try
            {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return name;
    }
}