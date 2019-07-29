package cn.zsk.springbootrabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @author:zsk
 * @CreateTime:2019-07-26 14:05
 */
public interface RabbitConsumerDService {

    void recieved(String msg, Channel channel, Message message) throws IOException;
}
