    routing、topic和work queues三种模式是由于exchange的三种模式不同，
    对应的exchange的三种模式分别是direct exchange，topic exchange，
    Nameless exchange（就是为空）

### 本次使用：Topic exchange
- #：匹配一个或者多个
- *：匹配一个

### 在本例中
#### 前提：
1. 消费者1绑定的是：topic.add,只能接受topic.add的消息
2. 消费者2绑定的是：topic.# ,可以接受所有的topic.XXX的东西
#### 测试：
1. 当生产者发布消息的key为：topic.add时两个消费者都能接受
2. 当生产者发布消息的key为:topic.delete时，只有消费者2能接受到