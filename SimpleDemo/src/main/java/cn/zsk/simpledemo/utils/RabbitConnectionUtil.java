package cn.zsk.simpledemo.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:zsk
 * @CreateTime:2019-05-21 14:20
 */
public class RabbitConnectionUtil {

    /*
    * 获取链接
    * */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置服务地址
        factory.setHost("127.0.0.1");

        //设置端口号，AMQP协议，端口号：5672
        factory.setPort(5672);

        //设置数据库，vhost
        factory.setVirtualHost("/vohost_admin");

        //用户名
        factory.setUsername("admin");

        //密码
        factory.setPassword("123456");


        return factory.newConnection();
    }
}
