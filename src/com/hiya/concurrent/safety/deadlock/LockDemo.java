package com.hiya.concurrent.safety.deadlock;

public class LockDemo implements Runnable
{
    private String str1;
    private String str2;

    public LockDemo(String str1, String str2)
    {
        super();
        this.str1 = str1;
        this.str2 = str2;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(Thread.currentThread().getName() + "运行");
            synchronized (str1)
            {
                System.out.println(Thread.currentThread().getName() + "锁住" + str1);
                Thread.sleep(1000);
                synchronized (str2)
                {
                    // 执行不到这里
                    System.out.println(Thread.currentThread().getName() + "锁住" + str2);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}