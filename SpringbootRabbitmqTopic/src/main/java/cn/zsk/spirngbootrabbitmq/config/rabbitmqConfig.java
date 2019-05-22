package cn.zsk.spirngbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:zsk
 * @CreateTime:2019-05-22 14:30
 */
@Configuration
public class rabbitmqConfig {

    //声明交换机
    @Bean
    TopicExchange exchange() {
        /*
        * 两种声明方法，推荐第二种，各个参数设置比较容易理解
        * */
//        return new TopicExchange("exchange");
        return (TopicExchange) ExchangeBuilder.topicExchange("exchange").durable(true).build();
    }

    //定义队列
    @Bean(name="message")
    Queue queueMessage() {
        //topic。message 是routing-key，匹配规则
        return new Queue("topic.message");
    }

    //定义队列
    @Bean(name="notes")
    Queue queueMessages() {
        return new Queue("topic.notes");
    }



    //将队列绑定到交换机
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueMessage)
                .to(exchange)
                .with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("notes") Queue queueMessages, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder
                .bind(queueMessages)
                .to(exchange)
                .with("topic.#");
    }
}
