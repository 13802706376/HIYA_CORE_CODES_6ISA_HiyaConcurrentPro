package com.hiya.concurrent.executor;

public class BusinessThread implements Runnable
{

    private Integer index;

    public BusinessThread(Integer index)
    {
        this.index = index;
    }

    @Override
    public void run()
    {
        /***
         * 业务......省略
         */
        try
        {
            System.out.println("开始处理线程！！！");
            Thread.sleep(index * 100);
            System.out.println("我的线程标识是：" + this.toString());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
