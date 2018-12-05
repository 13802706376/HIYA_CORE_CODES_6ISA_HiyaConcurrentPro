package com.hiya.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class HiyaAtomicInteger implements Runnable
{
    /**
     *    ��Java�����У�i++����Ĳ�������ԭ�Ӳ������������̰߳�ȫ�ģ�ʹ�õ�ʱ����Ҫ�õ�synchronized�ؼ��ֽ���ͬ������AtomicInteger��ͨ��һ���̰߳�ȫ�Ĳ����ӿ��Զ�ʵ��ͬ����
     *    ����Ҫ����Ϊ������ͬ�����ơ�
     * get()             ֱ�ӷ���ֵ 
        getAndAdd(int)    ����ָ�������ݣ����ر仯ǰ������ 
        getAndDecrement() ����1�����ؼ���ǰ������ 
        getAndIncrement() ����1����������ǰ������ 
        getAndSet(int)    ����ָ�������ݣ���������ǰ������ 
        addAndGet(int)    ����ָ�������ݺ󷵻����Ӻ������ 
        decrementAndGet() ����1�����ؼ��ٺ��ֵ 
        incrementAndGet() ����1���������Ӻ��ֵ 
        lazySet(int)      ������getʱ�Ż�set 
        compareAndSet(int, int) ����������Աȣ������ӳɹ��򷵻�true���򷵻�false 
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
        return serialNumber.getAndIncrement();// i++ ʵ����int temp=i;i=i+1;i=temp; ��Ҫԭ���Բ���
    }
}
