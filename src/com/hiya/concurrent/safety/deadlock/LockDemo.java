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
            System.out.println(Thread.currentThread().getName() + "����");
            synchronized (str1)
            {
                System.out.println(Thread.currentThread().getName() + "��ס" + str1);
                Thread.sleep(1000);
                synchronized (str2)
                {
                    // ִ�в�������
                    System.out.println(Thread.currentThread().getName() + "��ס" + str2);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}