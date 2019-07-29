package cn.zsk.springbootrabbitmq.service;

/**
 * @author:zsk
 * @CreateTime:2019-07-16 13:58
 */
public interface RabbitProducerService {

    void sendTopicAMsg();

    void sendTopicBMsg();

    void sendTopicCMsg();
}
