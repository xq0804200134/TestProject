package com.mq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 管理服务器连接
 * 
 */

public class ConnectionManager {

	private String url; // 远程服务器地址
	private String queue; // 消息队列名
	private String username; // 用于连接服务器的用户名
	private String password; // 用于连接服务器的密码
	private final String URL = "tcp://127.0.0.1:61616"; // properties文件中url的key
	private final String QUEUE = "myqueue"; // properties文件中query的key
	private final String USERNAME = "system"; // properties文件中username的key
	private final String PASSWORD = "manager"; // properties文件中password的key

	/**
	 * 初始化连接字符串
	 * 
	 * @return 是否初始化成功
	 */
	public Boolean init() {
		url = URL;
		queue = QUEUE;
		username = USERNAME;
		password = PASSWORD;
		return true;
	}

	/**
	 * 与远程服务器取得连接
	 * 
	 * @param url
	 *            服务器连接地址
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 连接对象
	 * @throws JMSException
	 *             JMS异常S
	 */
	public Connection getConnection() {
		Connection connection = null;
		if (init()) {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
					url);
			try {
				connection = factory.createConnection(username, password);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * 关闭连接
	 * 
	 * @param connection
	 *            与远程服务器连接
	 * @throws JMSException
	 *             JMS异常
	 */
	public void closeConnection(Connection connection, Session session,
			MessageProducer producer, MessageConsumer consumer) {
		try {
			if (producer != null) {
				producer.close();
			}
			if (consumer != null) {
				consumer.close();
			}
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得队列名
	 * 
	 * @return 队列名
	 */
	public String getQueue() {
		init();
		return queue;
	}

}