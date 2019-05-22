package cn.zsk.simpledemo.routing;


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
    private static final String EXCHANGE_NAME = "test_routing";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");


        String msg = "hello routing";

        /*
        * error时两个消费者都能收到
        * info时只有第二个消费者能收到
        * */
//        String routingKey = "error";
        String routingKey = "info";

        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());

        System.out.println("---send msg:"+msg);

        channel.close();
        connection.close();

    }
}
