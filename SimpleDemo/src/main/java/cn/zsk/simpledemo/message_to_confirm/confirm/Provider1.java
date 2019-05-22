package cn.zsk.simpledemo.message_to_confirm.confirm;

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
*
* 同步模式的confirm的单个发送
* */
public class Provider1 {
    private static final String QUEUE_NAME = "test_confirm_queue1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //生产者调用confirmSelect，将channel设置为confirm模式
        channel.confirmSelect();

        String msg = "hello test_confirm_queue1";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        if(channel.waitForConfirms()){
            System.out.println("---send msg:"+msg);
        }else {
            System.out.printf("---send msg failed");
        }


        channel.close();
        connection.close();

    }
}
