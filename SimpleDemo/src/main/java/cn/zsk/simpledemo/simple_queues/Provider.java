package cn.zsk.simpledemo.simple_queues;

import cn.zsk.simpledemo.utils.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 14:27
 */
/*
* 生产者
* */
public class Provider {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "hello world";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        System.out.println("---send msg:"+msg);

        channel.close();
        connection.close();

    }
}
