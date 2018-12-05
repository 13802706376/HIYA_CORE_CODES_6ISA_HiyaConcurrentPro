package com.hiya.concurrent.safety.deadlock;

public class LockDemoClient
{
    public static void main(String[] args) 
    {
        String str1 = new String("资源1");
        String str2 = new String("资源2");

        //一定要初始化相反的对象锁 ，一个锁住str1不释放；另外一个锁住str2不释放，造成死锁 
        new Thread(new LockDemo(str1, str2), "线程1").start();
        new Thread(new LockDemo(str2, str1), "线程2").start();
        
    }
}