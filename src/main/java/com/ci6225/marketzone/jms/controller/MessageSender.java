package com.ci6225.marketzone.jms.controller;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TemporaryTopic;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.NamingException;

import com.ci6225.marketzone.jms.config.CommandLineChat;
import com.ci6225.marketzone.jms.config.JMSConfig;
import com.ci6225.marketzone.jms.vo.MessageVo;

public class MessageSender {
	public void sendMessages(MessageVo message) throws JMSException, NamingException {

		String sender = message.getSenderQueue();
		String receiver = message.getReceiverQueue();

		Context initialContext = JMSConfig.getInitialContext();
		MessageReceiver messageReceiver = new MessageReceiver();

		Queue senderQueue = (Queue) initialContext.lookup(sender);
		Queue receiverQueue = (Queue) initialContext.lookup(receiver);

		JMSContext jMSContext = ((ConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory"))
				.createContext();

		jMSContext.createConsumer(senderQueue).setMessageListener(messageReceiver);
		JMSProducer jMSProducer = jMSContext.createProducer();
		jMSProducer.send(receiverQueue, message.getMessage());

	}

}
