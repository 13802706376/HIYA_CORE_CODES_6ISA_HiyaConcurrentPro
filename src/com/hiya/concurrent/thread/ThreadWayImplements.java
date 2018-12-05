package com.hiya.concurrent.thread;

/**
 * ʵ���� ThreadҲ�Ǽ̳� Runnable�ӿ�
 * Runnable��һ�����췽�� ������ Runnableʵ���� 
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
            System.out.println(name + "����  :  " + i);
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