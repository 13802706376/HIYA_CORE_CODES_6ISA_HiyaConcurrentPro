package com.hiya.concurrent.atomic;

import java.util.concurrent.TimeUnit;

public class BooleanOld implements Runnable
{
    private static boolean exists = false;
    private String name;

    public BooleanOld(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        if (!exists)
        {
            try
            {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1)
            {
                // do nothing
            }
            exists = true;
            System.out.println(name + " enter");
            try
            {
                System.out.println(name + " working");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e)
            {
                // do nothing
            }
            System.out.println(name + " leave");
            exists = false;
        } else
        {
            System.out.println(name + " give up");
        }
    }

    public static void main(String[] args)
    {
        BooleanOld bar1 = new BooleanOld("bar1");
        BooleanOld bar2 = new BooleanOld("bar2");
        new Thread(bar1).start();
        new Thread(bar2).start();
    }
}
