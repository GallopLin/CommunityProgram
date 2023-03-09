package org.example;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("chatqueue");
        MessageProducer producer = session.createProducer(destination);

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new ChatConsumer());
// 发送文本消息
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("Hello World!");
        producer.send(textMessage);

// 接收文本消息，由于上面已经指定了消息监听器，故能实时监听到新信息。
        MessageConsumer messageConsumer = session.createConsumer(destination);
        while (true) {
            Message message = messageConsumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println(text);
            }
            // 处理其他类型的消息
            else {
                // ...
            }
        }

    }
}