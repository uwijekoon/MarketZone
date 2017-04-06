package com.ci6225.marketzone.jms.config;

import java.util.Properties;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class JMSConfig {
  public static Context getInitialContext() throws JMSException, NamingException {
    
    Properties properties = new Properties();
    properties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
    properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
    properties.setProperty("java.naming.provider.url", "iiop://localhost:3700");
    
    
    
    return new InitialContext(properties);
  }
  
  
}
