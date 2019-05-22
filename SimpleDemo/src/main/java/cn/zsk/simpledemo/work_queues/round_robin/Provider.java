package cn.zsk.simpledemo.work_queues.round_robin;

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
* 轮询模式，每个消费者接受到相同数量的消息
*
* 本例中，生产者发送50条消息
* 消费者1需要两秒处理数据，消费者2需要一秒处理数据，但是两个消费者接受到的数据数量是一样的，都是25条
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
