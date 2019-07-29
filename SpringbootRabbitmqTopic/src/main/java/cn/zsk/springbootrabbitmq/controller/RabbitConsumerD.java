package cn.zsk.springbootrabbitmq.controller;

import cn.zsk.springbootrabbitmq.service.RabbitConsumerDService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author:zsk
 * @CreateTime:2019-07-26 10:54
 */
@Component
@RabbitListener(queues = "topic.d")
public class RabbitConsumerD {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitConsumerDService rabbitConsumerDService;


    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg, Channel channel, Message message) throws IOException {
        try {
           rabbitConsumerDService.recieved(msg,channel,message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}