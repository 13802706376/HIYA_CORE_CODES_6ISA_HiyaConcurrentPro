package com.hiya.concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadClient
{

    /**
     * java虚拟机启动一个进程，主线程main在main()调用时候被创建。
     * 随着调用ThreadWayExtends的两个对象的start方法，另外两个线程也启动了，这样，整个应用就在多线程下运行。
     * @param args
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ThreadWayExtends mTh1=new ThreadWayExtends("A");  
        ThreadWayExtends mTh2=new ThreadWayExtends("B");  
        
        /*
         * start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，一旦得到cpu时间片，就开始执行run()方法。
         */
        mTh1.setPriority(Thread.MAX_PRIORITY);
        mTh1.start();  
        
        //mTh1结束后main才继续
        //mTh1.join();
        
        //设置优先级 取值范围是1~10
        mTh2.setPriority(Thread.MIN_PRIORITY);
        mTh2.start();  
        
        //mTh2结束后main才继续
        //mTh2.join();
        /*
         * run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止，只是类的一个普通方法而已，如果直接调用Run方法，
         * 程序中依然只有主线程这一个线程，其程序执行路径还是只有一条，还是要顺序执行
         */
        //mTh1.run();
        //mTh2.run();
        
        /*
         * Thread2类通过实现Runnable接口，使得该类有了多线程类的特征。run（）方法是多线程程序的一个约定。所有的多线程代码都在run方法里面。
         * Thread类实际上也是实现了Runnable接口的类。在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，
         *  然后调用Thread对象的start()方法来运行多线程代码。
                实际上所有的多线程代码都是通过运行Thread的start()方法来运行的。因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，
                最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。
         */
        ThreadWayImplements mTh3=new ThreadWayImplements("C");  
        ThreadWayImplements mTh4=new ThreadWayImplements("D");  
         //new Thread(mTh3).start();  
         //new Thread(mTh4).start();
        
        /*
         * 其实Callable底层也是实现 实现Runnable接口，只不过封装了Runnable和Future，可以获取返回值
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
