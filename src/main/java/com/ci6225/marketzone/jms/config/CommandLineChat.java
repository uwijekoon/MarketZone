package com.ci6225.marketzone.jms.config;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class CommandLineChat implements MessageListener {
  
  public static void main(String[] args) throws NamingException, JMSException, IOException {
    
    if (args.length != 3) {
      System.out.println("usage: username subscribe-to-queue-name publish-to-queue-name");
    } else {
      String username = args[0];
      System.out.println(
          "username: " + username + " | subscribe-to-queue-name: " + args[1] + " |  publish-to-queue-name:" + args[2]);
      Context initialContext = CommandLineChat.getInitialContext();
      CommandLineChat commandLineChat = new CommandLineChat();
      Queue queue01 = (Queue) initialContext.lookup(args[1]);
      Queue queue02 = (Queue) initialContext.lookup(args[2]);
      JMSContext jMSContext = ((ConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory")).createContext();
     
      jMSContext.createConsumer(queue01).setMessageListener(commandLineChat);
      JMSProducer jMSProducer = jMSContext.createProducer();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      String messageToSend = null;
      while (true) {
        messageToSend = bufferedReader.readLine();
        if (messageToSend.equalsIgnoreCase("exit")) {
          jMSContext.close();
          System.exit(0);
          
        } else {
          jMSProducer.send(queue02, "[" + username + " : " + messageToSend + "]");
        }
      }
    }
  }
  
  @Override
  public void onMessage(Message msg) {
    
    try {
      System.out.println(msg.getBody(String.class));
    } catch (JMSException ex) {
      Logger.getLogger(CommandLineChat.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static Context getInitialContext() throws JMSException, NamingException {
    
    Properties properties = new Properties();
    properties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
    properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
    properties.setProperty("java.naming.provider.url", "iiop://localhost:3700");
    
    
    
    return new InitialContext(properties);
  }
}