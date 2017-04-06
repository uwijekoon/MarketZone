package com.ci6225.marketzone.jms.vo;

import java.io.Serializable;

import javax.jms.Queue;

public class MessageVo implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String UserName;
  
  private String senderQueue;
  
  private String receiverQueue;
  
  private String message;
  
  
  
  public MessageVo(String userName, String senderQueue, String receiverQueue, String message) {
    super();
    UserName = userName;
    this.senderQueue = senderQueue;
    this.receiverQueue = receiverQueue;
    this.message = message;
  }
  
  public MessageVo() {
    // TODO Auto-generated constructor stub
  }
  
  /**
   * @return the userName
   */
  public String getUserName() {
    
    return UserName;
  }
  
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    
    UserName = userName;
  }
  
  /**
   * @return the senderQueue
   */
  public String getSenderQueue() {
    
    return senderQueue;
  }
  
  /**
   * @param senderQueue the senderQueue to set
   */
  public void setSenderQueue(String senderQueue) {
    
    this.senderQueue = senderQueue;
  }
  
  /**
   * @return the receiverQueue
   */
  public String getReceiverQueue() {
    
    return receiverQueue;
  }
  
  /**
   * @param receiverQueue the receiverQueue to set
   */
  public void setReceiverQueue(String receiverQueue) {
    
    this.receiverQueue = receiverQueue;
  }
  
  /**
   * @return the message
   */
  public String getMessage() {
    
    return message;
  }
  
  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    
    this.message = message;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    
    return "Message [UserName=" + UserName + ", senderQueue=" + senderQueue + ", receiverQueue=" + receiverQueue
        + ", message=" + message + "]";
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    
    final int prime = 31;
    int result = 1;
    result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((receiverQueue == null) ? 0 : receiverQueue.hashCode());
    result = prime * result + ((senderQueue == null) ? 0 : senderQueue.hashCode());
    return result;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MessageVo other = (MessageVo) obj;
    if (UserName == null) {
      if (other.UserName != null) return false;
    } else if (!UserName.equals(other.UserName)) return false;
    if (message == null) {
      if (other.message != null) return false;
    } else if (!message.equals(other.message)) return false;
    if (receiverQueue == null) {
      if (other.receiverQueue != null) return false;
    } else if (!receiverQueue.equals(other.receiverQueue)) return false;
    if (senderQueue == null) {
      if (other.senderQueue != null) return false;
    } else if (!senderQueue.equals(other.senderQueue)) return false;
    return true;
  }
  
  
}
