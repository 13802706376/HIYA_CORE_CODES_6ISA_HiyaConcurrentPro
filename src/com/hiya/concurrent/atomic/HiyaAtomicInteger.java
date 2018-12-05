package com.hiya.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class HiyaAtomicInteger implements Runnable
{
    /**
     *    在Java语言中，i++这类的操作不是原子操作，并非是线程安全的，使用的时候需要用到synchronized关键字进行同步。而AtomicInteger则通过一种线程安全的操作接口自动实现同步，
     *    不需要再人为的增加同步控制。
     * get()             直接返回值 
        getAndAdd(int)    增加指定的数据，返回变化前的数据 
        getAndDecrement() 减少1，返回减少前的数据 
        getAndIncrement() 增加1，返回增加前的数据 
        getAndSet(int)    设置指定的数据，返回设置前的数据 
        addAndGet(int)    增加指定的数据后返回增加后的数据 
        decrementAndGet() 减少1，返回减少后的值 
        incrementAndGet() 增加1，返回增加后的值 
        lazySet(int)      仅仅当get时才会set 
        compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false 
     */
    private AtomicInteger serialNumber = new AtomicInteger(0);
    
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber()
    {
        return serialNumber.getAndIncrement();// i++ 实际是int temp=i;i=i+1;i=temp; 需要原子性操作
    }
}
