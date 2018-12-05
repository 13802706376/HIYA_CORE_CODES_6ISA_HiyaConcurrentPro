package com.hiya.concurrent.safety.method;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafety
{
    /**
     * ��ͨ���̲߳���ȫ�����ܱ�֤ԭ���ԣ��ɼ��� 
     * �����̷ֱ߳������һ������������incֻ������1
     * ���ڲ��ܱ�֤�ɼ��ԣ�������ֵ��incVolatileҪС 
     */
    public   int   incCommon = 0;
    Lock lock = new ReentrantLock();
    public  AtomicInteger incAtomic = new AtomicInteger(0);
    
    /**
     *  ����ĳ��ʱ�̱���incVolatile��ֵΪ10��
            �߳�1�Ա������������������߳�1�ȶ�ȡ�˱���inc��ԭʼֵ��Ȼ���߳�1�������ˣ�
            Ȼ���߳�2�Ա������������������߳�2Ҳȥ��ȡ����inc��ԭʼֵ�������߳�1ֻ�ǶԱ���inc���ж�ȡ��������û�жԱ��������޸Ĳ��������Բ��ᵼ���߳�2�Ĺ����ڴ��л������inc�Ļ�������Ч��
            �����߳�2��ֱ��ȥ�����ȡinc��ֵ������inc��ֵʱ10��Ȼ����м�1����������11д�빤���ڴ棬���д�����档
            Ȼ���߳�1���Ž��м�1�����������Ѿ���ȡ��inc��ֵ��ע���ʱ���߳�1�Ĺ����ڴ���inc��ֵ��ȻΪ10�������߳�1��inc���м�1������inc��ֵΪ11��Ȼ��11д�빤���ڴ棬���д�����档
            
            ��ô�����̷ֱ߳������һ������������incֻ������1��
            
            ���͵�������������ѻ������ʣ����԰���ǰ�治�Ǳ�֤һ���������޸�volatile����ʱ�����û�������Ч��Ȼ�������߳�ȥ���ͻ�����µ�ֵ���ԣ����û��������������happens-before
            �����е�volatile�������򣬵���Ҫע�⣬�߳�1�Ա������ж�ȡ����֮�󣬱������˵Ļ�����û�ж�incֵ�����޸ġ�Ȼ����Ȼvolatile�ܱ�֤�߳�2�Ա���inc��ֵ��ȡ�Ǵ��ڴ��ж�ȡ�ģ������߳�1û
            �н����޸ģ������߳�2�����Ͳ��ῴ���޸ĵ�ֵ��
            ��Դ�������������������ԭ���Բ���������volatileҲ�޷���֤�Ա������κβ�������ԭ���Եġ�
     */
    public volatile  int   incVolatile= 0;
    

    /**
     * �̲߳���ȫ
     */
    public void increase()
    {
        incCommon++;
    }
    
    /**
     * ��֤�ɼ��ԣ�����д���ڴ�֪ͨ�����̣߳��������ԣ���ֹcpu����ָ�����ţ����ǽ�����Ըñ�����������û��ԭ���� 
     */
    public void increaseVolatile()
    {
        incVolatile++;
    }
    
    /**
     * �̰߳�ȫ����ͳ�취
     */
    public synchronized void increaseSynchronized()
    {
        incCommon++;
    }
    
    /**
     * �̰߳�ȫ��lock�������ٶȿ�һ�� 
     */
    public  void increaseLock() 
    {
        lock.lock();
        try 
        {
            incCommon++;
        } 
         finally
        {
            lock.unlock();
        }
    }
    
    /**
     * ��java 1.5��java.util.concurrent.atomic�����ṩ��һЩԭ�Ӳ����࣬���Ի����������͵� ��������1���������Լ�����1���������Լ��ӷ���������һ��������
     * ������������һ�����������˷�װ����֤��Щ������ԭ���Բ�����atomic������CAS��ʵ��ԭ���Բ����ģ�Compare And Swap����CASʵ���������ô������ṩ��
     * CMPXCHGָ��ʵ�ֵģ���������ִ��CMPXCHGָ����һ��ԭ���Բ�����
     */
    public  void increaseAtomic() 
    {
        incAtomic.getAndIncrement();
    }
    
    public static void doBusiness(String type)
    {
        System.out.println("Begin time:"+new Date(System.currentTimeMillis()));
        final ThreadSafety test = new ThreadSafety();
        for (int i = 0; i < 10; i++)
        {
            new Thread()
            {
                public void run()
                {
                    for (int j = 0; j < 10000000; j++)
                    {
                        if("Common".equals(type))
                        {
                            test.increase();
                        }
                        else  if("Volatile".equals(type))
                        {
                            test.increaseVolatile();
                        }
                        else  if("Synchronized".equals(type))
                        {
                            test.increaseSynchronized();
                        }
                        else  if("Lock".equals(type))
                        {
                            test.increaseLock();
                        }
                        else  if("Atomic".equals(type))
                        {
                            test.increaseAtomic();
                        }
                        else
                        {
                            test.increase();
                        }
                    }
                };
            }.start();
        }

        while (true) 
        {
            int activeCount = Thread.activeCount() ;
            //System.out.println("activeCount="+activeCount );
            if(activeCount >1)
            {
                Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
                Set<Entry<Thread, StackTraceElement[]>> entry = map.entrySet();  
                Iterator<Map.Entry<Thread, StackTraceElement[]>>  ite = entry.iterator();  
                while(ite.hasNext())  
                {  
                    Map.Entry<Thread, StackTraceElement[]> en = ite.next();  
                    Thread key = en.getKey();  
                    System.out.println("��ǰ�̣߳�"+key.getName() );
                } 
            }
            //ֻ�����߳� main
            if(activeCount == 1)
            {
                System.out.println("incCommon="+test.incCommon);
                System.out.println("incVolatile="+test.incVolatile);
                System.out.println("incAtomic="+test.incAtomic);
                System.out.println("End time:"+new Date(System.currentTimeMillis()));
                break;
            }
        }
    }
}