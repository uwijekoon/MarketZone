package com.ci6225.marketzone.jms.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


public class MessageReceiver implements MessageListener {
 
  /*
   * (non-Javadoc)
   * 
   * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
   */
  @Override
  public void onMessage(Message msg) {
    
    try {
      System.out.println(msg.getBody(String.class));
    } catch (JMSException ex) {
      Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
}
