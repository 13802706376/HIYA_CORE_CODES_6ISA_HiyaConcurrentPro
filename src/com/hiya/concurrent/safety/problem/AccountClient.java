package com.hiya.concurrent.safety.problem;

/**
 * ���ֳ����� ����һ�����϶����Զ������jdk����ģ�
 * ��ʼ��һ���������󣬹������߳���ȥ����2���̡߳��ֱ�start  ... 
 * ��������̹߳�����Դ��
 * һ��2�������1�Ƿֲ�ʽ������Ե�����1�ǹ�����Դ  
 * ����һ������Ǽ̳�Thread��new���Ķ���һ����ֻ�ܴ�����Ե�����
 * ����new������ Account��Ҳ���ǹ�����Դ 
 * @author zjq
 *
 */
public class AccountClient 
{
    public static void main(String[] args)
    {
        // ����һ���˻��������д��5000Ԫ,������ǹ������ 
        Account account = new Account(5000);
        
        // ģ��ȡǮ����
        AccountGetThread agt1 = new AccountGetThread(account);
        AccountGetThread agt2 = new AccountGetThread(account);
        new Thread(agt1, "��").start();
        new Thread(agt2, "������").start();
    }
}
