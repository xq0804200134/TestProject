package com.mq;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 与远程服务器通过JMS进行数据传递
 *
 */

public class DataExchange {
	
	/**
	 * 往消息队列发送消息
	 * @param data 消息内容
	 */
	public void dataSend(String data) {
		Connection connection;
		Session session = null;
		MessageProducer producer = null;
		String queue;
		ConnectionManager manager = new ConnectionManager();
		connection = manager.getConnection();
		try {
			connection.start();
			queue = manager.getQueue();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue);
			producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(data);
			producer.send(message);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection, session, producer, null);
		}
	}
	
	/**
	 * 从服务器消息队列取消息
	 * @param timeout 超时时间
	 * @return 取到的消息
	 */
	public List<String> dataReceive() {
		List<String> resultList = new ArrayList<String>();
		final int TIMEOUT = 1000;
		Connection connection;
		Session session = null;
		MessageConsumer consumer = null;
		String queue;
		ConnectionManager manager = new ConnectionManager();
		connection = manager.getConnection();
		queue = manager.getQueue();
		try {
			connection = manager.getConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue);
			consumer = session.createConsumer(destination);
			// Receives the next message that arrives within the specified timeout interval
			Message message;
			while ((message = consumer.receive()) != null){
				if (message != null && (message instanceof TextMessage) ) {
					TextMessage txtMsg = (TextMessage) message;
					resultList.add(txtMsg.getText());
				}
			}
			session.commit();
			if(resultList.size()>0){
				for(int i=0;i<resultList.size();i++){
					System.out.print(resultList.get(i));
				}
				System.out.println();
			}
			consumer.close();
			session.close();
		} catch (JMSException e){
			e.printStackTrace();
		} finally {
		
			manager.closeConnection(connection, session, null, consumer);
		}
		return resultList;
	}
	
}
