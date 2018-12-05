package com.hiya.concurrent.safety.problem;

public class Account
{
    private int money;

    public Account(int money)
    {
        super();
        this.money = money;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }
}
