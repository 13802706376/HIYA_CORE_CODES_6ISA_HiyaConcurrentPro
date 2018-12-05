package com.hiya.concurrent.concurrent.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListClient
{
    public static void main(String[] args)
    {
        List<Integer> tempList = Arrays.asList(new Integer[]{ 1, 2 });
        
        
        /**
         *     ReadThread:1
                ReadThread:2
                ReadThread:9
                Exception in thread "pool-1-thread-2" java.lang.ArrayIndexOutOfBoundsException: 4
                at java.util.ArrayList.add(Unknown Source)
                at com.hiya.concurrent.concurrent.list.WriteThread.run(WriteThread.java:17)
                at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
                at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
                at java.lang.Thread.run(Unknown Source)
                ż������� ����Խ����쳣���̲߳���ȫ 
         */
       // List<Integer> copyList  = new ArrayList<>(tempList);
        
        
        List<Integer> copyList  = new Vector<Integer>(tempList);        
        
        /**
         * �̰߳�ȫ 
         */
        // CopyOnWriteArrayList<Integer> ps = new CopyOnWriteArrayList<Integer>();
        //CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);
        
        
        // 2��ģ����̶߳�list���ж���д
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        System.out.println("copyList size:" + copyList.size());
    }
}
