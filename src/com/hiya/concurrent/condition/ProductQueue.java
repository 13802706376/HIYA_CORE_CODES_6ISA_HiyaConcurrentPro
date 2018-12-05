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
     * ����put()��Ҫ���в�����������˾͹���await()����ֱ���յ�notFull���źš�
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
                //�ڽ���lock.lock()��Ψһ�����ͷ����Ĳ�������await()�ˡ�Ҳ����˵await()����ʵ���Ͼ����ͷ�����Ȼ������̣߳�һ����������ͱ����ѣ��ٴλ�ȡ��
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
     * ����take()��Ҫ ���в�Ϊ�գ����Ϊ�վ͹���await()����ֱ���յ�notEmpty���źţ�
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
                //�ڽ���lock.lock()��Ψһ�����ͷ����Ĳ�������await()�ˡ�Ҳ����˵await()����ʵ���Ͼ����ͷ�����Ȼ������̣߳�һ����������ͱ����ѣ��ٴλ�ȡ��
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
