package cn.zsk.springbootrabbitmq.service.impl;

import cn.zsk.springbootrabbitmq.config.RabbitDeadLetterConfig;
import cn.zsk.springbootrabbitmq.service.RabbitProducerAckService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:zsk
 * @CreateTime:2019-07-26 10:59
 */
@Service
public class RabbitProducerAckServiceImpl implements RabbitProducerAckService {
    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public void sendTopicDMsg() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.d] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b、topic.d接收
//        this.rabbitTemplate.convertAndSend("topicExchange", "topic.d", dateString);

        //测试：直接放到死信exchange
        this.rabbitTemplate.convertAndSend(RabbitDeadLetterConfig.DEAD_LETTER_EXCHANGE,
                RabbitDeadLetterConfig.DEAD_LETTER_TEST_ROUTING_KEY, 10);

    }
}
