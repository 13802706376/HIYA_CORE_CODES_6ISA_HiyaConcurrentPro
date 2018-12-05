package com.hiya.concurrent.executor;

import java.util.concurrent.ExecutionException;

public class ThreadPoolClient
{
    public static  void main(String[]args) throws InterruptedException, ExecutionException
    {
        //System.out.println(ExcutorServiceFactory.doCachedThreadPool());
        
        //ExcutorServiceFactory.doFixedThreadPool();
        
        //ExcutorServiceFactory.doScheduledThreadPool();
        
        ExcutorServiceFactory.doSingleThreadPool();
    }
}
