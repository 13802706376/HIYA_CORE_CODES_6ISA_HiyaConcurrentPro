package com.hiya.concurrent.safety.deadlock;

public class LockDemoClient
{
    public static void main(String[] args) 
    {
        String str1 = new String("��Դ1");
        String str2 = new String("��Դ2");

        //һ��Ҫ��ʼ���෴�Ķ����� ��һ����סstr1���ͷţ�����һ����סstr2���ͷţ�������� 
        new Thread(new LockDemo(str1, str2), "�߳�1").start();
        new Thread(new LockDemo(str2, str1), "�߳�2").start();
        
    }
}