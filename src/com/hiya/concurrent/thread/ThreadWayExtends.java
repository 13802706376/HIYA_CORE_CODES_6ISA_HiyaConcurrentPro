package com.hiya.concurrent.thread;

public class ThreadWayExtends extends Thread
{
    //�̶߳��Եı��� ��pc���������߳�ջ ��
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
                //Thread.sleep()��������Ŀ���ǲ��õ�ǰ�̶߳��԰�ռ�ý�������ȡ��CPU��Դ��������һ��ʱ��������߳�ִ�еĻ��ᡣ
                Thread.sleep(5);
            } catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            System.out.println(name + "����  :  " + i);
            
            if( i  == 20)
            {
                //��һ����������ģ��̣߳���ִ�е�20ʱ��CPUʱ���õ�����ʱ�������̣߳�����CPUʱ�䲢ִ�С�
                //�ڶ�����������ģ��̣߳���ִ�е�20ʱ��CPUʱ���õ�����ʱ���ģ��̣߳�����CPUʱ�䲢ִ��
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
