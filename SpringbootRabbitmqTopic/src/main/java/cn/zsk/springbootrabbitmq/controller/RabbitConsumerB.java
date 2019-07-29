package cn.zsk.springbootrabbitmq.controller;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:zsk
 * @CreateTime:2019-07-16 14:04
 */
@Component
@RabbitListener(queues = "topic.b")
public class RabbitConsumerB {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg, Channel channel, Message message) {
        try {
            Thread.sleep(5000);
            logger.info("********************[topic.b] recieved message:" + msg);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
