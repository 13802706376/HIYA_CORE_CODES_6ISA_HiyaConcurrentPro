package com.hiya.concurrent.safety.problem;

/**
 * 这种场景是 独立一个复合对象（自定义后者jdk里面的）
 * 初始化一个公共对象，构造多个线程类去创建2个线程。分别start  ... 
 * 这样多个线程共享资源了
 * 一般2种情况，1是分布式处理各自的任务，1是共享资源  
 * 还有一种情况是继承Thread，new出的对象不一样，只能处理各自的任务
 * 或者new出两个 Account，也不是供向资源 
 * @author zjq
 *
 */
public class AccountClient 
{
    public static void main(String[] args)
    {
        // 创建一个账户，里面有存款5000元,这个才是共享对象 
        Account account = new Account(5000);
        
        // 模拟取钱过程
        AccountGetThread agt1 = new AccountGetThread(account);
        AccountGetThread agt2 = new AccountGetThread(account);
        new Thread(agt1, "你").start();
        new Thread(agt2, "你老婆").start();
    }
}
