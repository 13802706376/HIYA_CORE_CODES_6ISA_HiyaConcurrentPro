package com.hiya.concurrent.atomic;

/**
 * ʹ��synchronized����ģ��CAS �㷨��ʵ������Ӳ��������ɵģ���10���̴߳�����ڴ������ݵ�10���޸�����ֻ���ϸ��߳��޸��꣬
 * ����̴߳��ڴ��л�ȡ���ڴ�ֵ��������ֵ���ŵ����ڴ�ֵ�����ܶ��ڴ�ֵ�����޸ġ�
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

    // �Ƚ�
    public synchronized boolean compareAndSwap(int expectedValue, int newValue)
    {
        int oldValue = value;// �̶߳�ȡ�ڴ�ֵ����Ԥ��ֵ�Ƚ�
        if (oldValue == expectedValue)
        {
            this.value = newValue;
            return true;
        }
        return false;
    }
}
