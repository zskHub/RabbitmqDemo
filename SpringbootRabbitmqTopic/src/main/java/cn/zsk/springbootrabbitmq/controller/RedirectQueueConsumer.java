package cn.zsk.springbootrabbitmq.controller;

import cn.zsk.springbootrabbitmq.config.RabbitDeadLetterConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author:zsk
 * @CreateTime:2019-07-26 15:13
 */
/*
* 队列"死信"后，会将消息投递到Dead Letter Exchanges，然后该Exchange会将消息投递到重定向队列。
* 此时，在重定向队列中，做对应的业务操作。
* */
@RabbitListener(queues = RabbitDeadLetterConfig.REDIRECT_QUEUE)
@Component
@Slf4j
public class RedirectQueueConsumer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 重定向队列和死信队列形参一致Integer number
     * @param number
     */
    @RabbitHandler
    public void fromDeadLetter(Integer number){
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!RedirectQueueConsumer : {}", number);
        // 对应的操作
        int i = number / 1;
    }
}
