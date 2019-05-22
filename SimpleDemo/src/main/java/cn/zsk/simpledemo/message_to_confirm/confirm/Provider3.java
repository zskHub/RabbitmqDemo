package cn.zsk.simpledemo.message_to_confirm.confirm;

import cn.zsk.simpledemo.utils.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 14:27
 */
/*
* 生产者
* 异步confirm模式
* */
public class Provider3 {
    private static final String QUEUE_NAME = "test_confirm_queue3";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //生产者调用confirmSelect，将channel设置为confirm模式
        channel.confirmSelect();

        //存放未确认的消息
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());

        channel.addConfirmListener(new ConfirmListener() {

            //没有问题的
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //b:true返回多条，false：返回一条
                if(b){
                    System.out.printf("---handleAck----multiple");
                    confirmSet.headSet(l+1).clear();
                }else {
                    System.out.printf("---handleAck----multiple false");
                    confirmSet.remove(l);
                }
            }

            //有问题的
            @Override
            public void handleNack(long l, boolean b) throws IOException {
                //b:true返回多条，false：返回一条
                if(b){
                    System.out.printf("---handleNack----multiple");
                    confirmSet.headSet(l+1).clear();
                }else {
                    System.out.printf("---handleNack----multiple false");
                    confirmSet.remove(l);
                }
            }
        });

        String msg = "hello test_confirm_queue3";

        while (true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            confirmSet.add(seqNo);
        }



    }
}
