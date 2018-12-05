package com.hiya.concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadClient
{

    /**
     * java���������һ�����̣����߳�main��main()����ʱ�򱻴�����
     * ���ŵ���ThreadWayExtends�����������start���������������߳�Ҳ�����ˣ�����������Ӧ�þ��ڶ��߳������С�
     * @param args
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ThreadWayExtends mTh1=new ThreadWayExtends("A");  
        ThreadWayExtends mTh2=new ThreadWayExtends("B");  
        
        /*
         * start()����������һ���̣߳���ʱ���̴߳��ھ����������У�״̬����û�����У�һ���õ�cpuʱ��Ƭ���Ϳ�ʼִ��run()������
         */
        mTh1.setPriority(Thread.MAX_PRIORITY);
        mTh1.start();  
        
        //mTh1������main�ż���
        //mTh1.join();
        
        //�������ȼ� ȡֵ��Χ��1~10
        mTh2.setPriority(Thread.MIN_PRIORITY);
        mTh2.start();  
        
        //mTh2������main�ż���
        //mTh2.join();
        /*
         * run()��Ϊ�߳��壬��������Ҫִ�е�����̵߳����ݣ�Run�������н��������߳��漴��ֹ��ֻ�����һ����ͨ�������ѣ����ֱ�ӵ���Run������
         * ��������Ȼֻ�����߳���һ���̣߳������ִ��·������ֻ��һ��������Ҫ˳��ִ��
         */
        //mTh1.run();
        //mTh2.run();
        
        /*
         * Thread2��ͨ��ʵ��Runnable�ӿڣ�ʹ�ø������˶��߳����������run���������Ƕ��̳߳����һ��Լ�������еĶ��̴߳��붼��run�������档
         * Thread��ʵ����Ҳ��ʵ����Runnable�ӿڵ��ࡣ�������Ķ��̵߳�ʱ����Ҫ��ͨ��Thread��Ĺ��췽��Thread(Runnable target) ���������
         *  Ȼ�����Thread�����start()���������ж��̴߳��롣
                ʵ�������еĶ��̴߳��붼��ͨ������Thread��start()���������еġ���ˣ���������չThread�໹��ʵ��Runnable�ӿ���ʵ�ֶ��̣߳�
                ���ջ���ͨ��Thread�Ķ����API�������̵߳ģ���ϤThread���API�ǽ��ж��̱߳�̵Ļ�����
         */
        ThreadWayImplements mTh3=new ThreadWayImplements("C");  
        ThreadWayImplements mTh4=new ThreadWayImplements("D");  
         //new Thread(mTh3).start();  
         //new Thread(mTh4).start();
        
        /*
         * ��ʵCallable�ײ�Ҳ��ʵ�� ʵ��Runnable�ӿڣ�ֻ������װ��Runnable��Future�����Ի�ȡ����ֵ
         */
        ThreadWayCallable mTh5 = new ThreadWayCallable("E");
        ThreadWayCallable mTh6 = new ThreadWayCallable("F");
        ExecutorService es =  Executors.newCachedThreadPool();
        //Future<String> f5 = es.submit(mTh5);
        //Future<String> f6 = es.submit(mTh6);
        //System.out.println(f5.get());
        //System.out.println(f6.get());
        
    }
}
