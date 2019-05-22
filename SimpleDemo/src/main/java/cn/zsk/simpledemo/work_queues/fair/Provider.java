package cn.zsk.simpledemo.work_queues.fair;

import cn.zsk.simpledemo.utils.RabbitConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 15:02
 */

/*
* 公平模式(能者多劳)
* 按照每个消费者实际处理时间发送消息（即处理完一个，直接再给一个）
*
* 本例中，生产者发送50条消息
* 消费者1需要两秒处理数据，消费者2需要一秒处理数据。消费者1总共接受的数据量高于消费者2，因为它处理的快。
*
* 注意：使用该模式必须关闭自动应答 ack，改为手动应答。
*
* */
public class Provider {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //获取channel
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        /*
        * 每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只让消费者处理一个消息
        *
        * 限制发送同一个消费者不得超过一条消息
        *
        * */
        channel.basicQos(1);

        for(int i = 0;i < 50; i++){
            String msg = "hello" + i;
            System.out.println("work_queue_provider"+msg);
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            Thread.sleep(i*20);
        }
        channel.close();
        connection.close();

    }
}
