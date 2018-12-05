package com.hiya.concurrent.threadLocal;

public class SequenceNumber
{
    /**
     * ��ͬ�������У�ͨ������������Ʊ�֤ͬһʱ��ֻ��һ���̷߳��ʱ�������ʱ�ñ����Ƕ���̹߳���ģ�ʹ��ͬ������Ҫ��������ܵط���ʲôʱ��Ա������ж�д��ʲôʱ����Ҫ����ĳ������ʲôʱ���ͷŶ������ȷ��ӵ����⣬������ƺͱ�д�Ѷ���Խϴ�

        ��ThreadLocal�����һ���Ƕ���������̵߳Ĳ������ʡ�ThreadLocal��Ϊÿһ���߳��ṩһ�������ı����������Ӷ������˶���̶߳����ݵķ��ʳ�ͻ����Ϊÿһ���̶߳�ӵ���Լ��ı����������Ӷ�Ҳ��û�б�Ҫ�Ըñ�������ͬ���ˡ�ThreadLocal�ṩ���̰߳�ȫ�Ĺ�������ڱ�д���̴߳���ʱ�����԰Ѳ���ȫ�ı�����װ��ThreadLocal��
        
        
        ��������˵�����ڶ��߳���Դ��������⣬ͬ�����Ʋ����ˡ���ʱ�任�ռ䡱�ķ�ʽ����ThreadLocal�����ˡ��Կռ任ʱ�䡱�ķ�ʽ��ǰ�߽��ṩһ�ݱ������ò�ͬ���߳��Ŷӷ��ʣ�������Ϊÿһ���̶߳��ṩ��һ�ݱ�������˿���ͬʱ���ʶ�����Ӱ��
        
        Spring: ֻ����״̬��Bean�ſ����ڶ��̻߳����¹�����Spring�У����󲿷�Bean����������Ϊsingleton�����򡣾�����ΪSpring��һЩBean����RequestContextHolder��TransactionSynchronizationManager��LocaleContextHolder�ȣ��з��̰߳�ȫ״̬����ThreadLocal���д���������Ҳ��Ϊ�̰߳�ȫ��״̬����Ϊ��״̬��Bean�Ϳ����ڶ��߳��й����ˡ�
        
        
        �̰߳�ȫ���ⶼ����ȫ�ֱ�������̬���������
        
        SimpleDateFormate sdf = new SimpleDateFormat();
        ʹ��sdf.parse(dateStr);sdf.format(date);
        ��sdf����һ����Caleadar��������ã���Դ��sdf.parse(dateStr);Դ����calendar.clear();��calendar.getTime(); // ��ȡcalendar��ʱ��
        
        ��� �߳�A ������ sdf.parse(), ���ҽ����� calendar.clear()��δִ��calendar.getTime()��ʱ��,�߳�B�ֵ�����sdf.parse(), ��ʱ���߳�BҲִ����sdf.clear()����, �����͵����߳�A�ĵ�calendar���ݱ������;
        
        ThreadLocal��ʹ�ÿռ任ʱ�䣬synchronized��ʹ��ʱ�任�ռ�
        
        
        ����˵��һ������ҵ����ProcessBusiness�ǵ����ģ����в�������һ��pbʵ����Ҫʹ��ͬ����������������ʽ��ThreadLocal���Է�װһ��pbʵ�������߳��ߡ�
        ע�⣺ֻ�ܾ����ڸ���ʹ�õĲ㼶������漰����̹߳�ͬ��Ʊ���ܹ���ô����

     */
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>()
    {
        public Integer initialValue()
        {
            return 0;
        }
    };

    public int getNextNum()
    {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}