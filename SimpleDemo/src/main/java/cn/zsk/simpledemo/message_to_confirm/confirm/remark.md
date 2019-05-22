### 生产者端confirm模式实现原理
    生产者将信道设置成confirm模式，一旦信道进入confirm模式，所有该信道上面发布的消息都会被指派一个唯一的id（从1开始），
    一旦消息被投递到所有匹配的队列之后，broker就会发哦少年宫一个确认给生产者（包含消息的唯一id），这就使得生产者知道消息已经
    正确到达目的队列了，如果消息和队列是可持久化的，那么确认消息会将消息写入磁盘之后发出，broker回传给生产者的确认消息中
    deliver-tag域包含了确认消息的序列号，此外broker也可以设置basic.ack的multiple域，表示到这个序列号之前的所有消息都已经得到了处理。

#### confirm模式最大的好处在于它可以是异步的。

#### 开启confirm模式方法：
    channel.confirmSelect();

    编程模式：
        同步的：1.普通发一条，waitForConfirms()
               2.批量的发一批，waitForConfirms()，(方法和第一个一样)
        异步的：3.异步confirm模式：提供一个回调方法

#### 异步模式：
    channel对象提供的confirmListener()回调方法只包含deliveryTag(当前chanel发出的消息序号)，我们需要自己为每一个channel维护一个
    unconfirm的消息序号集合，每publish一条数据，集合中元素+1，每回调一次handleAck方法，unconfirm集合删掉相应的一条(multiple=false)
    或多条(multiple=true)记录。从程序运行效率上看，这个unconfirm集合最好采用有序集合SortedSet存储结构。