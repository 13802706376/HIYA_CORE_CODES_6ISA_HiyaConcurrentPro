package com.hiya.concurrent.threadLocal;

public class SequenceNumber
{
    /**
     * 在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。

        而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
        
        
        概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响
        
        Spring: 只有无状态的Bean才可以在多线程环境下共享，在Spring中，绝大部分Bean都可以声明为singleton作用域。就是因为Spring对一些Bean（如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等）中非线程安全状态采用ThreadLocal进行处理，让它们也成为线程安全的状态，因为有状态的Bean就可以在多线程中共享了。
        
        
        线程安全问题都是由全局变量及静态变量引起的
        
        SimpleDateFormate sdf = new SimpleDateFormat();
        使用sdf.parse(dateStr);sdf.format(date);
        在sdf内有一个对Caleadar对象的引用，在源码sdf.parse(dateStr);源码中calendar.clear();和calendar.getTime(); // 获取calendar的时间
        
        如果 线程A 调用了 sdf.parse(), 并且进行了 calendar.clear()后还未执行calendar.getTime()的时候,线程B又调用了sdf.parse(), 这时候线程B也执行了sdf.clear()方法, 这样就导致线程A的的calendar数据被清空了;
        
        ThreadLocal是使用空间换时间，synchronized是使用时间换空间
        
        
        比如说有一个流程业务类ProcessBusiness是单例的，所有并发公用一个pb实例，要使用同步方法，用锁的形式。ThreadLocal可以封装一个pb实例跟着线程走。
        注意：只能局限在各自使用的层级，如果涉及多个线程共同卖票不能够这么做。

     */
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>()
    {
        public Integer initialValue()
        {
            return 0;
        }
    };

    public int getNextNum()
    {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}