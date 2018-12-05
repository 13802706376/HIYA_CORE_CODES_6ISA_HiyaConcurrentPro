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
         * ҵ��......ʡ��
         */
        try
        {
            System.out.println("��ʼ�����̣߳�����");
            Thread.sleep(index * 100);
            System.out.println("�ҵ��̱߳�ʶ�ǣ�" + this.toString());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
