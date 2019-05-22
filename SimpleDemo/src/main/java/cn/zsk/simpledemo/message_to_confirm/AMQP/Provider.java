package cn.zsk.simpledemo.message_to_confirm.AMQP;

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
    private static final String QUEUE_NAME = "test_transaction_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "hello test_transaction_queue";

        try {
            //开始事务
            channel.txSelect();

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

            //模拟异常
//            int i = 1/0;

            //没有异常，提交事务,事务提交后消费者才会收到消息
            channel.txCommit();

            System.out.println("---send msg:"+msg);

        } catch (Exception e) {
            e.printStackTrace();
            //出现异常，回滚事务
            channel.txRollback();
            System.out.printf("send message txRollback");
        }


        channel.close();
        connection.close();

    }
}
