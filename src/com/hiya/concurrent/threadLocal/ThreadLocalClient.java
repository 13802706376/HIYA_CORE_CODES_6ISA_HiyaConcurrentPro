package com.hiya.concurrent.threadLocal;

public class ThreadLocalClient
{
    /**
     * ��������Ľ����Ϣ�����Ƿ���ÿ���߳��������������Ȼ������ͬһ��SequenceNumberʵ���������ǲ�û�з����໥���ŵ���������Ǹ��Բ������������кţ�
     * ����Ϊ����ͨ��ThreadLocalΪÿһ���߳��ṩ�˵����ĸ�����
     * @param args
     */
    public static void main(String[] args)
    {
        SequenceNumber sn = new SequenceNumber();
        new Thread(new SequenceNumberThread(sn)).start();
        new Thread(new SequenceNumberThread(sn)).start();
        new Thread(new SequenceNumberThread(sn)).start();
    }
}