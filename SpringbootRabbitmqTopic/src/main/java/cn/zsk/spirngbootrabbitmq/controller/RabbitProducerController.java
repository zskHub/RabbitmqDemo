package cn.zsk.spirngbootrabbitmq.controller;

import cn.zsk.spirngbootrabbitmq.service.RabbitProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:zsk
 * @CreateTime:2019-07-16 13:52
 */
@RestController
@RequestMapping("/rabbit/producer")
public class RabbitProducerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @RequestMapping("sendTopicAMsg")
    public void sendTopicAMsg() {
       rabbitProducerService.sendTopicAMsg();
       logger.info("*************sendTopicAMsg方法：已经放入队列，返回结果********************");
    }

    @RequestMapping("sendTopicBMsg")
    public void sendTopicBMsg() {
        rabbitProducerService.sendTopicBMsg();
        logger.info("*************sendTopicBMsg方法：已经放入队列，返回结果********************");
    }

    @RequestMapping("sendTopicCMsg")
    public void sendTopicCMsg() {
       rabbitProducerService.sendTopicCMsg();
        logger.info("*************sendTopicCMsg方法：已经放入队列，返回结果********************");
    }
}
