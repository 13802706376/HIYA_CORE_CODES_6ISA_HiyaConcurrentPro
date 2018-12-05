package com.hiya.concurrent.thread;

import java.util.concurrent.Callable;

/**
 * 实际上 Thread也是继承 Runnable接口
 * Runnable有一个构造方法 参数是 Runnable实现类 
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
            System.out.println(name + "运行  :  " + i);
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