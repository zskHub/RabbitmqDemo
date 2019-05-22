package cn.zsk.simpledemo.publish_subscribe;


import cn.zsk.simpledemo.utils.RabbitConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 15:06
 */
public class Consumer2 {
    private static final String QUEUE_NAME = "test_exchange_fanout_queue_2";
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //获取channel
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        //绑定队列到交换机转发器
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //定义一个消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //获取达到消息，触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("test_exchange_fanout2:"+msg);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("test_exchange_fanout2 done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };

        boolean autoAck = false;

        channel.basicConsume(QUEUE_NAME,autoAck,consumer);

    }

}
