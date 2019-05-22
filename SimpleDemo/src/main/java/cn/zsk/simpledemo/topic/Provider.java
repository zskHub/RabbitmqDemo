package cn.zsk.simpledemo.topic;

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
    private static final String EXCHANGE_NAME = "test_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");


        String msg = "hello topic test add";

        /*
        * 消费者1绑定的是：topic.add,只能接受topic.add的消息
        * 消费者2绑定的是：topic.# ,可以接受所有的topic.XXX的东西
        *
        * */
//        String routingKey = "topic.add";
        String routingKey = "topic.delete";

        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());

        System.out.println("---send msg:"+msg);

        channel.close();
        connection.close();

    }
}
