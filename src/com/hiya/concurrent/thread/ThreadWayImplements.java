package com.hiya.concurrent.thread;

/**
 * 实际上 Thread也是继承 Runnable接口
 * Runnable有一个构造方法 参数是 Runnable实现类 
 * @author zjq
 *
 */
public class ThreadWayImplements implements Runnable
{
    private String name;

    public ThreadWayImplements(String name) 
    {  
        this.name=name;  
    }

    @Override
    public void run()
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
    }
}