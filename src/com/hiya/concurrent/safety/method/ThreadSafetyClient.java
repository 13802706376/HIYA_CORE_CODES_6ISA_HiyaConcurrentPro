package com.hiya.concurrent.safety.method;

public class ThreadSafetyClient
{
    public static void main(String[] args)
    {
        /*
            1�룬û�пɼ��ԣ�û��ԭ���ԣ�û��������
         */
        ThreadSafety.doBusiness("Common");
        
        /*
            1�룬�пɼ��Ժ������ԣ�û��ԭ����
         */
        ThreadSafety.doBusiness("Volatile");
        
        /*
            4��,׼ȷ
         */
        ThreadSafety.doBusiness("Synchronized");
        
        /*
            2��,׼ȷ
         */
        ThreadSafety.doBusiness("Lock");
        
        /*
             1.5��,׼ȷ
         */
        ThreadSafety.doBusiness("Atomic");
    }
}