package com.hiya.concurrent.safety.method;

public class ThreadSafetyClient
{
    public static void main(String[] args)
    {
        /*
            1秒，没有可见性，没有原子性，没有有序性
         */
        ThreadSafety.doBusiness("Common");
        
        /*
            1秒，有可见性和有序性，没有原子性
         */
        ThreadSafety.doBusiness("Volatile");
        
        /*
            4秒,准确
         */
        ThreadSafety.doBusiness("Synchronized");
        
        /*
            2秒,准确
         */
        ThreadSafety.doBusiness("Lock");
        
        /*
             1.5秒,准确
         */
        ThreadSafety.doBusiness("Atomic");
    }
}