package cn.zsk.springbootrabbitmq.service.impl;

import cn.zsk.springbootrabbitmq.service.RabbitConsumerDService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author:zsk
 * @CreateTime:2019-07-26 14:06
 */
@Service
public class RabbitConsumerDServiceImpl implements RabbitConsumerDService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void recieved(String msg, Channel channel, Message message) throws IOException {
        try {
            /*
            * channel.basicAck(long,boolean); 确认收到消息，消息将被队列移除，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息。
            *  channel.basicNack(long,boolean,boolean); 确认否定消息，第一个boolean表示一个consumer还是所有，第二个boolean表示requeue是否重新回到队列，true重新入队。
            * channel.basicReject(long,boolean); 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列。
            *
            * */
//            int i= 1/0;

            logger.info("********************[topic.d] recieved message:" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }catch (Exception e){
            e.printStackTrace();
            //重新回到队伍里面，会发现控制台一直输出错误日志，因为返回队伍后会重复处理消息，如此循环
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,true);
            //不让回到队伍中
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);

        }
    }
}
