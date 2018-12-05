package com.hiya.concurrent.thread;

/**
 * ���������̣߳�A�̴߳�ӡ10��A��B�̴߳�ӡ10��B,C�̴߳�ӡ10��C��Ҫ���߳�ͬʱ���У������ӡ10��ABC��
 * ���������Object��wait()��notify()�Ϳ��Ժܷ���Ľ����
 * 
 * 
 * ��������һ��������˼·���Ӵ�ķ�����������������Ϊ���̼߳��ͬ�����Ѳ�������Ҫ��Ŀ�ľ���ThreadA->ThreadB->ThreadC->ThreadAѭ��ִ�������̡߳�
 * Ϊ�˿����߳�ִ�е�˳����ô�ͱ���Ҫȷ�����ѡ��ȴ���˳������ÿһ���̱߳���ͬʱ�������������������ܼ���ִ�С�һ����������prev������ǰһ���߳���
 * ���еĶ�����������һ�������������������Ҫ��˼����ǣ�Ϊ�˿���ִ�е�˳�򣬱���Ҫ�ȳ���prev����Ҳ��ǰһ���߳�Ҫ�ͷ��������������ȥ���������������
 * ���߼汸ʱ��ӡ��֮�����ȵ���self.notify()�ͷ������������������һ���ȴ��̣߳��ٵ���prev.wait()�ͷ�prev����������ֹ��ǰ�̣߳��ȴ�ѭ���������ٴα���
 * �ѡ������������룬���Է��������߳�ѭ����ӡABC����10�Ρ��������е���Ҫ���̾���A�߳��������У�����C,A�����������ͷ�A,C��������B���߳�B�ȴ�A����
 * ������B�������ӡB�����ͷ�B��A��������C���߳�C�ȴ�B����������C�������ӡC�����ͷ�C,B��������A���������ƺ�ûʲô���⣬���������ϸ��һ�£��ͻ�
 * ���������⣬���ǳ�ʼ�����������̰߳���A,B,C��˳��������������ǰ���˼����A����B��B����C��C�ٻ���A���������ּ���������JVM���̵߳��ȡ�ִ�е�˳��
 * 
 * @author zjq
 *
 */
public class ThreadWait implements Runnable
{
    private String name;
    private Object prev;
    private Object self;

    private ThreadWait(String name, Object prev, Object self) 
    {     
        this.name = name;     
        this.prev = prev;     
        this.self = self;     
    }

    @Override
    public void run()
    {
        int count = 10;
        while (count > 0)
        {
            synchronized (prev)
            {
                synchronized (self)
                {
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try
                {
                    prev.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception
    {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadWait pa = new ThreadWait("A", c, a);
        ThreadWait pb = new ThreadWait("B", a, b);
        ThreadWait pc = new ThreadWait("C", b, c);

        new Thread(pa).start();
        Thread.sleep(100); // ȷ����˳��A��B��Cִ��
        
        new Thread(pb).start();
        Thread.sleep(100);
        
        new Thread(pc).start();
        Thread.sleep(100);
    }
}