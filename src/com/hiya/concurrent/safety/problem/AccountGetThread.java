package com.hiya.concurrent.safety.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountGetThread implements Runnable
{
    //�߳����� ������� 
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
            System.out.println(Thread.currentThread().getName() + "�˻�������" + account.getMoney() + "Ԫ");
            // ʹЧ�������ԣ�����100ms
            try
            {
                 Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int money = account.getMoney() - 2000;
            account.setMoney(money);
            System.out.println(Thread.currentThread().getName() + "ȡ��2000Ԫ���˻�������" + account.getMoney() + "Ԫ");
    }
    
    private void doGetSync(Account account)
    {
         synchronized (this)  
         {
                System.out.println(Thread.currentThread().getName() + "�˻�������" + account.getMoney() + "Ԫ");
                try
                {
                     Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                int money = account.getMoney() - 2000;
                account.setMoney(money);
                System.out.println(Thread.currentThread().getName() + "ȡ��2000Ԫ���˻�������" + account.getMoney() + "Ԫ");
        }
    }
    
    private void doGetLock(Account account)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "�˻�������" + account.getMoney() + "Ԫ");
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int money = account.getMoney() - 2000;
            account.setMoney(money);
            System.out.println(Thread.currentThread().getName() + "ȡ��2000Ԫ���˻�������" + account.getMoney() + "Ԫ");
        } finally
        {
            lock.unlock();
        }
    }
}
