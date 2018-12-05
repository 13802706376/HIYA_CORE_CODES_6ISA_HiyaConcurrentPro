package com.hiya.concurrent.atomic;

public class Person
{
    volatile long id;

    public Person(long id)
    {
        this.id = id;
    }

    public String toString()
    {
        return "id:" + id;
    }
}
