package cn.zsk.springbootrabbitmq.controller;

import cn.zsk.springbootrabbitmq.config.RabbitDeadLetterConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author:zsk
 * @CreateTime:2019-07-26 15:11
 */

@Slf4j
@Component
@RabbitListener(queues = RabbitDeadLetterConfig.DEAD_LETTER_QUEUE)
public class DeadLetterConsumer {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitDeadLetterConfig.DEAD_LETTER_QUEUE, durable = "true"),
            exchange = @Exchange(value = RabbitDeadLetterConfig.DEAD_LETTER_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = RabbitDeadLetterConfig.DEAD_LETTER_TEST_ROUTING_KEY)
    )*/
    @RabbitHandler
    public void testDeadLetterQueueAndThrowsException(@Payload Integer number){
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DeadLetterConsumer :{}/0 ", number);
        int i = number / 0;
    }
}