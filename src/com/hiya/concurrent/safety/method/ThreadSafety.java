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
     * 普通的线程不安全，不能保证原子性，可见性 
     * 几个线程分别进行了一次自增操作后，inc只增加了1
     * 由于不能保证可见性，所以数值比incVolatile要小 
     */
    public   int   incCommon = 0;
    Lock lock = new ReentrantLock();
    public  AtomicInteger incAtomic = new AtomicInteger(0);
    
    /**
     *  假如某个时刻变量incVolatile的值为10，
            线程1对变量进行自增操作，线程1先读取了变量inc的原始值，然后线程1被阻塞了；
            然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，
            所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。
            然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入工作内存，最后写入主存。
            
            那么两个线程分别进行了一次自增操作后，inc只增加了1。
            
            解释到这里，可能有朋友会有疑问，不对啊，前面不是保证一个变量在修改volatile变量时，会让缓存行无效吗？然后其他线程去读就会读到新的值，对，这个没错。这个就是上面的happens-before
            规则中的volatile变量规则，但是要注意，线程1对变量进行读取操作之后，被阻塞了的话，并没有对inc值进行修改。然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没
            有进行修改，所以线程2根本就不会看到修改的值。
            根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。
     */
    public volatile  int   incVolatile= 0;
    

    /**
     * 线程不安全
     */
    public void increase()
    {
        incCommon++;
    }
    
    /**
     * 保证可见性（立即写入内存通知其他线程）和有序性（阻止cpu进行指令重排，但是仅仅针对该变量），但是没有原子性 
     */
    public void increaseVolatile()
    {
        incVolatile++;
    }
    
    /**
     * 线程安全，传统办法
     */
    public synchronized void increaseSynchronized()
    {
        incCommon++;
    }
    
    /**
     * 线程安全，lock方法，速度快一倍 
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
     * 在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），
     * 减法操作（减一个数）进行了封装，保证这些操作是原子性操作。atomic是利用CAS来实现原子性操作的（Compare And Swap），CAS实际上是利用处理器提供的
     * CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。
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
                    System.out.println("当前线程："+key.getName() );
                } 
            }
            //只有主线程 main
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