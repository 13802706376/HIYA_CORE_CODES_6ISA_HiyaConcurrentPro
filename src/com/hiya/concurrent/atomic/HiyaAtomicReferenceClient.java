package com.hiya.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class HiyaAtomicReferenceClient
{
    public static void main(String[] args)
    {
        // ��������Person�������ǵ�id�ֱ���101��102��
        Person p1 = new Person(101);
        Person p2 = new Person(102);
        
        // �½�AtomicReference���󣬳�ʼ������ֵΪp1����
        AtomicReference<Person> ar = new AtomicReference<Person>(p1);
        // ͨ��CAS����ar�����ar��ֵΪp1�Ļ�����������Ϊp2��
        ar.compareAndSet(p1, p2);

        Person p3 = (Person) ar.get();
        System.out.println("p3 is " + p3);
        System.out.println("p3.equals(p1)=" + p3.equals(p1));
    }
}
