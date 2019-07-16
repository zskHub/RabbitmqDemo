package cn.zsk.spirngbootrabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:zsk
 * @CreateTime:2019-07-16 14:04
 */
/*
* 通过测试可以知道，该类可以当成普通的controller类来使用，同时还能监听rabbit队列
* 如果不打算当成controller使用，可以将@RestController改为@Component
*
* */
@RestController
@RequestMapping("rabbit/consumer")
@RabbitListener(queues = "topic.a")
public class RabbitConsumerA {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    @RequestMapping("rabbitConsumerA")
    public void recieved(String msg) {
        try {
            Thread.sleep(5000);
           logger.info("********************[topic.a] recieved message:" + msg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
    * 如果有两个@RabbitHandler，会根据生产者放入队列时的参数自己判断，
    * 如果找到两个参数一样的就会报错，
    * 如果一个也找不到参数一样的也会报错
    * */
//    @RabbitHandler
//    @RequestMapping("rabbitConsumerA1")
//    public void recieved1(List<String> list) {
//        System.out.println("********************测试两个RabbitHandler会怎么样***");
//    }


}
