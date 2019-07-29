package cn.zsk.springbootrabbitmq.controller;

import cn.zsk.springbootrabbitmq.service.RabbitProducerAckService;
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
 * @CreateTime:2019-07-26 10:45
 */
@Api(tags = "带有消息应答的测试方法")
@RestController
@RequestMapping("rabbit/producerAck")
public class RabbitProducerAckController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitProducerAckService rabbitProducerAckService;

    @ApiOperation(value = "发送topicD,测试手动消息应答机制")
    @PostMapping("sendTopicDMsg")
    public void sendTopicDMsg() {
        rabbitProducerAckService.sendTopicDMsg();
        logger.info("*************sendTopicDMsg方法：已经放入队列，返回结果********************");
    }

}
