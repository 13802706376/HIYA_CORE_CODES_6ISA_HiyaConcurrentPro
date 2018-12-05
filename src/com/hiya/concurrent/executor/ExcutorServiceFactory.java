package com.hiya.concurrent.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * һ���̳߳أ��ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴��������ٶ��⿪�����������Ӧ���ٶȡ�
 * �����̳߳ص���ϵ�ṹ��
 *    java.util.concurrent.Executor:�����̵߳�ʹ������ȵĸ��ӿ�
 *        |--**ExecutorService �ӽӿڣ��̳߳ص���Ҫ�ӿ�
 *             |--ThreadPoolExecutor �̳߳ص�ʵ����
 *             |--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 *                  |--ScheduledThreadPoolExecutor:�̳�ThreadPoolExecutor,ʵ��ScheduledExecutorService�ӿ�
 * ���������ࣺExecutors
 * �����У�
 * ExecutorService newFixedThreadPool(): �����̶���С���̳߳�
 * ExecutorService newCachedThreadPool():�����̳߳أ��̳߳ص��������̶������Ը�����Ҫ�Զ��ĸ���������
 * ExecutorService newSingleThreadExecutor():���������̳߳ء��̳߳���ֻ��һ���߳�
 * ScheduledExecutorService newScheduledThreadPool():�����̶���С���̣߳������ӳٻ�ʱ��ִ������
 * 
 */
public class ExcutorServiceFactory
{
    
        public static List<String> doCachedThreadPool() throws InterruptedException, ExecutionException
        {
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
               System.out.println("****************************newCachedThreadPool*******************************");
               List<String> list = Collections.synchronizedList(new ArrayList<String>());
               for(int i=0;i<4;i++)
               {
                   final int index=i;
                   cachedThreadPool.execute(new BusinessThread(index));
                   Future<String> future = cachedThreadPool.submit(new BusinessCallable(index));
                   list.add(future.get());
               }
               return list;
        }
  
        /**
         * ÿ��ֻ�������߳��ڴ�������һ���߳�ִ����Ϻ��µ��߳̽�����ʼ�����̵߳�ַ��һ����
         * ��ʼ�����̣߳�����
                ��ʼ�����̣߳�����
                �ҵ��̱߳�ʶ�ǣ�com.hiya.concurrent.executor.ThreadBusiness@7b1be7fc
                ��ʼ�����̣߳�����
                �ҵ��̱߳�ʶ�ǣ�com.hiya.concurrent.executor.ThreadBusiness@6b96ae9c
                ��ʼ�����̣߳�����
                �ҵ��̱߳�ʶ�ǣ�com.hiya.concurrent.executor.ThreadBusiness@75dc16ef
                �ҵ��̱߳�ʶ�ǣ�com.hiya.concurrent.executor.ThreadBusiness@65514d9e
         */
        public static void doFixedThreadPool()
        {
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
                System.out.println("****************************newFixedThreadPool*******************************");
                for(int i=0;i<4;i++)
                {
                    final int index=i;
                    fixedThreadPool.execute(new BusinessThread(index));
                }
        }
    
        public static void doScheduledThreadPool()
        {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
            System.out.println("****************************newScheduledThreadPool*******************************");
            for(int i=0;i<4;i++)
            {
                final int index=i;
                //�ӳ�����ִ��
                newScheduledThreadPool.schedule(new BusinessThread(index),3, TimeUnit.SECONDS);
                
                //���� ������ʱ2�룬ÿ4��ִ��  
                newScheduledThreadPool.scheduleAtFixedRate(new BusinessThread(index), 2000, 4000, TimeUnit.MILLISECONDS);
            }
        }
        
        public static void doSingleThreadPool()
        {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            System.out.println("****************************newFixedThreadPool*******************************");
            for(int i=0;i<4;i++)
            {
                final int index=i;
                newSingleThreadExecutor.execute(new BusinessThread(index));
            }
        }
}
