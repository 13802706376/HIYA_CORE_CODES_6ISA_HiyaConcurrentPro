package com.hiya.concurrent.condition;

public class ProductQueueClient
{
    public static void main(String[] args) throws Exception
    {
        ProductQueue<String> productQueue = new ProductQueue<String>();
        
        productQueue.put("A");
        productQueue.put("A");
        productQueue.put("A");
        productQueue.put("A");
        
        
        String code = productQueue.take();
        
        
        
        
    }
}
