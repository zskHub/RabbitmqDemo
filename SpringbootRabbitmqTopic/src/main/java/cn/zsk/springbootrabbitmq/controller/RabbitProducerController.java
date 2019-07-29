package cn.zsk.springbootrabbitmq.controller;

import cn.zsk.springbootrabbitmq.service.RabbitProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:zsk
 * @CreateTime:2019-07-16 13:52
 */
@Api(tags = "rabbitmq普通测试")
@RestController
@RequestMapping("/rabbit/producer")
public class RabbitProducerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @ApiOperation(value = "发送topic.msg消息")
    @PostMapping("sendTopicAMsg")
    public void sendTopicAMsg() {
       rabbitProducerService.sendTopicAMsg();
       logger.info("*************sendTopicAMsg方法：已经放入队列，返回结果********************");
    }

    @ApiOperation(value = "发送topic.good.msg消息")
    @PostMapping("sendTopicBMsg")
    public void sendTopicBMsg() {
        rabbitProducerService.sendTopicBMsg();
        logger.info("*************sendTopicBMsg方法：已经放入队列，返回结果********************");
    }

    @ApiOperation(value = "发送topic.m.z消息")
    @PostMapping("sendTopicCMsg")
    public void sendTopicCMsg() {
       rabbitProducerService.sendTopicCMsg();
        logger.info("*************sendTopicCMsg方法：已经放入队列，返回结果********************");
    }
}
