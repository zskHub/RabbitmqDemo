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
        return (TopicExchange) ExchangeBuilder.topicExchange("topicExchange").durable(true).build();
    }

    //定义队列
    @Bean(name="topic.a")
    Queue queueA() {
        //topic。message 是routing-key，匹配规则
        return new Queue("topic.a");
    }

    //定义队列
    @Bean(name="topic.b")
    Queue queueB() {
        return new Queue("topic.b");
    }

    //定义队列
    @Bean(name="topic.c")
    Queue queueC() {
        return new Queue("topic.c");
    }

    /*
    * topicA的key为topic.msg 那么他只会接收包含topic.msg的消息
    * topicB的key为topic.#那么他只会接收topic开头的消息
    * topicC的key为topic.*.Z那么他只会接收topic.B.z这样格式的消息
    *
    * */
    //将队列A绑定到交换机
    @Bean
    Binding bindingExchangeWithA(@Qualifier("topic.a") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueMessage)
                .to(exchange)
                .with("topic.msg");
    }

    //将队列B绑定到交换机
    @Bean
    Binding bindingExchangeWithB(@Qualifier("topic.b") Queue queueMessages, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder
                .bind(queueMessages)
                .to(exchange)
                .with("topic.#");
    }

    //将队列C绑定到交换机
    @Bean
    Binding bindingExchangeWithC(@Qualifier("topic.c") Queue queueMessages, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder
                .bind(queueMessages)
                .to(exchange)
                .with("topic.*.z");
    }

}
