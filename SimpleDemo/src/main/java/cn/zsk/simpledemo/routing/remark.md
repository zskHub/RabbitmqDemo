- routing、topic和work queues三种模式是由于exchange的三种模式不同，
对应的exchange的三种模式分别是direct exchange，topic exchange，Nameless exchange（就是为空）

- 本次使用：direct exchange

### 在本例中
#### 前提：
1. 消费者1绑定的是：error,只能接受error的消息
2. 消费者2绑定的是：error和info ,可以接受error和info的东西
#### 测试：
1. 当生产者发布消息的key为：error时两个消费者都能接受
2. 当生产者发布消息的key为:info时，只有消费者2能接受到