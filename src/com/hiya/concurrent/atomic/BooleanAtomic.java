package com.hiya.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *       AtomicBoolean��java.util.concurrent.atomic���µ�ԭ�ӱ���������������ṩ��һ��ԭ���ࡣ����������Ծ����ڶ��̻߳����£����ж���߳�ͬʱִ����Щ���ʵ�������ķ���ʱ��
 *       ���������ԣ�����ĳ���߳̽��뷽����ִ�����е�ָ��ʱ�����ᱻ�����̴߳�ϣ�������߳̾���������һ����һֱ�ȵ��÷���ִ����ɣ�����JVM�ӵȴ�������ѡ��һ����һ���߳̽��룬
 *       ��ֻ��һ���߼��ϵ���⡣ʵ�����ǽ���Ӳ�������ָ����ʵ�ֵģ����������߳�(����˵ֻ����Ӳ��������������)��

      ����AtomicBoolean�������Booleanֵ�ı仯��ʱ��������֮����룬���ֲ�����ԭ���ԡ������;�����compareAndSet(boolean expect, boolean update)�����������Ҫ�������� 
              1. �Ƚ�AtomicBoolean��expect��ֵ�����һ�£�ִ�з����ڵ���䡣��ʵ����һ��if���         
              2. ��AtomicBoolean��ֵ���update         �Ƚ���Ҫ��������������һ���ǳɵģ�����������֮�䲻�ᱻ��ϣ��κ��ڲ������ⲿ����䶼����������������֮�����С�
                      Ϊ���̵߳Ŀ����ṩ�˽���ķ�����
 * @author zjq
 *
 */
public class BooleanAtomic implements Runnable
{
    private static AtomicBoolean exists = new AtomicBoolean(false);
    private String name;

    public BooleanAtomic(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        if (exists.compareAndSet(false, true))
        {
            System.out.println(name + " enter");
            try
            {
                System.out.println(name + " working");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e)
            {
            }
            System.out.println(name + " leave");
            exists.set(false);
        } else
        {
            System.out.println(name + " give up");
        }
    }

    public static void main(String[] args)
    {
        BooleanAtomic bar1 = new BooleanAtomic("bar1");
        BooleanAtomic bar2 = new BooleanAtomic("bar2");
        new Thread(bar1).start();
        new Thread(bar2).start();
    }
}