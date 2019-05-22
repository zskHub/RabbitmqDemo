package cn.zsk.simpledemo.simple_queues;


import cn.zsk.simpledemo.utils.RabbitConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 14:36
 */

/*
* 消费者
* */
public class Consumer {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();


        /*
        * durable参数：消息的持久化
        *
        * 这里注意一下：如果代码原来写的是false，然后运行了（说白了就是这个queue已经声明了），是不能直接在代码中改为true
        * 因为rabbitmq不允许重新定义不同参数的同一个名称的队列。可以从控制台或者其他地方删除这个queue后再声明。
        *
        *
        * */
        boolean durable = false;
        //声明队列
        channel.queueDeclare(QUEUE_NAME,durable,false,false,null);

        //定义一个消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //获取达到消息，触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String msg = new String(body,"utf-8");
                System.out.println("new api recv:"+msg);
            }
        };

        /*
        * ack：消息应答模式
        * true：自动确认模式，一旦rabbitmq将消息分发给消费者，就会从内存中删除
        *       这种情况下，如果杀死正在执行的消费者，就会丢失正在处理的消息（因为内存中已经删除了）
        * false：手动模式，如果有一个消费者挂掉，没有接收到回执，就会将消息交给其他的消费者，rabbitmq支持消息应答
        *       消费者发送一个消息应答告诉rabbitmq这个消息我已经处理完了，然后rabbitmq才会去删除内存中的消息
        *
        * 消息应答默认是打开的，即ack默认是false
        *
        * */
        boolean ack = true;
        //监听队列
        channel.basicConsume(QUEUE_NAME,ack,consumer);



    }
}
