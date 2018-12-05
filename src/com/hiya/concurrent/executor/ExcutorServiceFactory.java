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
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 * 二、线程池的体系结构：
 *    java.util.concurrent.Executor:负责线程的使用与调度的根接口
 *        |--**ExecutorService 子接口：线程池的主要接口
 *             |--ThreadPoolExecutor 线程池的实现类
 *             |--ScheduledExecutorService 子接口：负责线程的调度
 *                  |--ScheduledThreadPoolExecutor:继承ThreadPoolExecutor,实现ScheduledExecutorService接口
 * 三、工具类：Executors
 * 方法有：
 * ExecutorService newFixedThreadPool(): 创建固定大小的线程池
 * ExecutorService newCachedThreadPool():缓存线程池，线程池的数量不固定，可以根据需要自动的更改数量。
 * ExecutorService newSingleThreadExecutor():创建单个线程池。线程池中只有一个线程
 * ScheduledExecutorService newScheduledThreadPool():创建固定大小的线程，可以延迟或定时的执行任务。
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
         * 每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）
         * 开始处理线程！！！
                开始处理线程！！！
                我的线程标识是：com.hiya.concurrent.executor.ThreadBusiness@7b1be7fc
                开始处理线程！！！
                我的线程标识是：com.hiya.concurrent.executor.ThreadBusiness@6b96ae9c
                开始处理线程！！！
                我的线程标识是：com.hiya.concurrent.executor.ThreadBusiness@75dc16ef
                我的线程标识是：com.hiya.concurrent.executor.ThreadBusiness@65514d9e
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
                //延迟三秒执行
                newScheduledThreadPool.schedule(new BusinessThread(index),3, TimeUnit.SECONDS);
                
                //周期 初次延时2秒，每4秒执行  
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
