package com.hiya.concurrent.safety.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountGetThread implements Runnable
{
    //线程里面 共享对象 
    private  Account account;
    Lock lock = new ReentrantLock();
    
    public AccountGetThread(Account account)
    {
        super();
        this.account = account;
    }

    @Override
    public void run()
    {
        doGetNomal(account);
        //doGetSync(account);
        //doGetLock(account);
    }
    
    private void doGetNomal(Account account)
    {
            System.out.println(Thread.currentThread().getName() + "账户现在有" + account.getMoney() + "元");
            // 使效果更明显，休眠100ms
            try
            {
                 Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int money = account.getMoney() - 2000;
            account.setMoney(money);
            System.out.println(Thread.currentThread().getName() + "取了2000元，账户现在有" + account.getMoney() + "元");
    }
    
    private void doGetSync(Account account)
    {
         synchronized (this)  
         {
                System.out.println(Thread.currentThread().getName() + "账户现在有" + account.getMoney() + "元");
                try
                {
                     Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                int money = account.getMoney() - 2000;
                account.setMoney(money);
                System.out.println(Thread.currentThread().getName() + "取了2000元，账户现在有" + account.getMoney() + "元");
        }
    }
    
    private void doGetLock(Account account)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "账户现在有" + account.getMoney() + "元");
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int money = account.getMoney() - 2000;
            account.setMoney(money);
            System.out.println(Thread.currentThread().getName() + "取了2000元，账户现在有" + account.getMoney() + "元");
        } finally
        {
            lock.unlock();
        }
    }
}
