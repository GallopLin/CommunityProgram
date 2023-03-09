package org.example;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

class ChatConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                // 获得文本消息
                String text = textMessage.getText();
                System.out.println(text);
            }
            // 处理其他类型的消息
            else {
                // ...
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
