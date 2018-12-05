package com.hiya.concurrent.atomic;

/**
 * 使用synchronized方法模拟CAS 算法，实际是由硬件机制完成的，用10个线程代表对内存中数据的10次修改请求。只有上个线程修改完，
 * 这个线程从内存中获取的内存值当成期望值，才等于内存值，才能对内存值进行修改。
 * @author zjq
 *
 */
public class CompareAndSwap
{
    private int value;

    public synchronized int get()
    {
        return value;
    }

    public static void main(String[] args)
    {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSwap(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }
    }

    // 比较
    public synchronized boolean compareAndSwap(int expectedValue, int newValue)
    {
        int oldValue = value;// 线程读取内存值，与预估值比较
        if (oldValue == expectedValue)
        {
            this.value = newValue;
            return true;
        }
        return false;
    }
}
