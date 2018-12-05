package com.hiya.concurrent.executor;

import java.util.concurrent.Callable;

public class BusinessCallable implements Callable<String>
{
    private Integer index;

    public BusinessCallable(Integer index)
    {
        this.index = index;
    }

    @Override
    public String call() throws Exception
    {
        return  String.valueOf( index);
    }
}
