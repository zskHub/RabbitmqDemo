package cn.zsk.simpledemo.publish_subscribe;

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
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        /*
        * 参数说明：
        *   “fanout”：不处理路由键
        *   绑定转发器的所有的队列都能接受到消息。
        *
        *
        * */
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");


        String msg = "hello exchange";

        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        System.out.println("---send msg:"+msg);

        channel.close();
        connection.close();

    }
}
