## rabbitmq和springboot集成示例 ##
### 目录结构 ###
    |------config
    |------controller
    |------entity
    |------service
    |      |-----impl
### 简单的springboot集成rabbitmq ###
- ***因为后期会加入新的功能，可能会造成多个配置文件，所以每个功能都会额外说明一下配置文件的情况***
1. 需要的配置文件：RabbitmqConfig
2. 测试的类：RabbitConsumerA,RabbitConsumerB,RabbitConsumerC，RabbitProduceController



### 应答方式 ###
1. 配置文件：RabbitmqConfig，RabbitmqAckConfig
2. 测试的类：RabbitProducerAckController,RabbitConsumerB，RabbitConsumerD
    应答方式发送的消息类型是：topic.d，所以RabbitConsumerB,RabbitConsumerD都能接受到信息
3. 注意，一旦加上RabbitmqAckConfig,其他的A,B,C,D等也都变成了应答模式了