package com.hiya.concurrent.thread;

public class ThreadWayExtends extends Thread
{
    //线程独自的变量 （pc计数器和线程栈 ）
    private String name;

    public ThreadWayExtends(String name)
    {
        this.name = name;
    }

    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            try
            {
                //Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
                Thread.sleep(5);
            } catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            System.out.println(name + "运行  :  " + i);
            
            if( i  == 20)
            {
                //第一种情况：李四（线程）当执行到20时会CPU时间让掉，这时张三（线程）抢到CPU时间并执行。
                //第二种情况：李四（线程）当执行到20时会CPU时间让掉，这时李四（线程）抢到CPU时间并执行
                Thread.yield();
            }
            
            try
            {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
