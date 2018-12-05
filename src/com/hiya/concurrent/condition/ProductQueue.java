package com.hiya.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductQueue<T>
{

    private final T[] items;

    private final Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private int head, tail, count;

    @SuppressWarnings("unchecked")
    public ProductQueue(int maxSize)
    {
        items = (T[]) new Object[maxSize];
    }

    public ProductQueue()
    {
        this(10);
    }

    /**
     * 生产put()需要队列不满，如果满了就挂起（await()），直到收到notFull的信号。
     * @param t
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException
    {
        lock.lock();
        try
        {
            while (count == getCapacity())
            {
                //在进入lock.lock()后唯一可能释放锁的操作就是await()了。也就是说await()操作实际上就是释放锁，然后挂起线程，一旦条件满足就被唤醒，再次获取锁
                notFull.await();
            }
            items[tail] = t;
            if (++tail == getCapacity())
            {
                tail = 0;
            }
            ++count;
            notEmpty.signalAll();
        } finally
        {
            lock.unlock();
        }
    }

    /**
     * 消费take()需要 队列不为空，如果为空就挂起（await()），直到收到notEmpty的信号；
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (count == 0)
            {
                //在进入lock.lock()后唯一可能释放锁的操作就是await()了。也就是说await()操作实际上就是释放锁，然后挂起线程，一旦条件满足就被唤醒，再次获取锁
                notEmpty.await();
            }
            T ret = items[head];
            items[head] = null;// GC
            //
            if (++head == getCapacity())
            {
                head = 0;
            }
            --count;
            notFull.signalAll();
            return ret;
        } finally
        {
            lock.unlock();
        }
    }

    public int getCapacity()
    {
        return items.length;
    }

    public int size()
    {
        lock.lock();
        try
        {
            return count;
        } finally
        {
            lock.unlock();
        }
    }
}
