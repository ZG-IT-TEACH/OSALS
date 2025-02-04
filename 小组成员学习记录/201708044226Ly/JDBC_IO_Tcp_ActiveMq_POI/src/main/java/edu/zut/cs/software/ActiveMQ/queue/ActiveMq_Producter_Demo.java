package edu.zut.cs.software.ActiveMQ.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

import static javax.jms.Session.SESSION_TRANSACTED;


public class ActiveMq_Producter_Demo {
    private static final String userName = ActiveMQConnection.DEFAULT_USER;
    private static final String passWord = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String broker_Url = ActiveMQConnection.DEFAULT_BROKER_URL;

    AtomicInteger atomicInteger = new AtomicInteger(0);
    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<MessageProducer>();

    public void init() {
        connectionFactory = new ActiveMQConnectionFactory(userName, passWord, broker_Url);
            try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String queueName, String message) {
        try {
            Queue queue = session.createQueue(queueName);
            MessageProducer messageProducer = null;
            if(threadLocal.get() != null) {
                messageProducer = threadLocal.get();
            } else {
                messageProducer = session.createProducer(queue);
                threadLocal.set(messageProducer);
            }
            /*
            //Thread.sleep(1000);
            Integer number = atomicInteger.getAndIncrement();
            TextMessage textMessage = session.createTextMessage(Thread.currentThread().getName() +
                    ":研哥哥真帅！,number:" + number);
            System.out.println(Thread.currentThread().getName() +
                    ":研哥哥真帅！,number:" + number);
            */
            Integer number = atomicInteger.getAndIncrement();
            TextMessage textMessage = session.createTextMessage(message);
            System.out.println(Thread.currentThread().getName() +
                    message + ",number:" + number);
            messageProducer.send(textMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
