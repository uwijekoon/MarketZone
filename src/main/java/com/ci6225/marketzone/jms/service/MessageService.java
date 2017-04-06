
package com.ci6225.marketzone.jms.service;

import java.util.List;

import javax.jms.JMSException;
import javax.naming.NamingException;

import com.ci6225.marketzone.jms.controller.MessageSender;
import com.ci6225.marketzone.jms.util.Constant;
import com.ci6225.marketzone.jms.vo.MessageVo;


public class MessageService {
  
  public void sendToCustomer(String username, List customer, String message) throws JMSException, NamingException {
    
    
    
    String customerQueue = Constant.CUSTOMER_QUEUE;
    String sellerqueue = Constant.SELLER_QUEUE;
    MessageVo messageObjt = new MessageVo(username, sellerqueue, customerQueue, message);
    
    sendMessage(messageObjt);
    
  }
  
  
  
  public void sendToSeller(String username, String sellername, String message) throws JMSException, NamingException {
    
    
    String customerQueue = Constant.CUSTOMER_QUEUE;
    String sellerqueue = Constant.SELLER_QUEUE;
    MessageVo messageObjt = new MessageVo(username, customerQueue, sellerqueue, message);
    
    sendMessage(messageObjt);
    
  }
  
  private void sendMessage(MessageVo messageObjt) throws JMSException, NamingException {
    
    MessageSender messageSender = new MessageSender();
    messageSender.sendMessages(messageObjt);
    
  }
  
}
