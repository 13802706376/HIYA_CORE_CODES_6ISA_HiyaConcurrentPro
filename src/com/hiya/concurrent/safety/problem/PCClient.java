package com.hiya.concurrent.safety.problem;

import java.util.LinkedList;
import java.util.Queue;

public class PCClient
{
    public static void main(String[] args)
    {
            final Queue<Integer> sharedQ = new LinkedList<Integer>();
            Thread producer = new Thread(new PCProducer(sharedQ));
            Thread consumer =new Thread( new PCConsumer(sharedQ));

            producer.start();
            consumer.start();
    }
}
