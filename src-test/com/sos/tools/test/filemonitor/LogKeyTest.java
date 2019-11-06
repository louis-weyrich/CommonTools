/*
 * File: LogKeyTest.java
 *
 */
package com.sos.tools.test.filemonitor;

import org.junit.Test;

import com.sos.tools.logging.LogKey;
import com.sos.tools.logging.LoggerLevel;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class LogKeyTest
{

   @Test
   public void testLogKey(){
       LogKey key1 = new LogKey("com.wn.*", LoggerLevel.ERROR);
       LogKey key2 = new LogKey("com.sos.tools.filemonitor.FileFilter", LoggerLevel.DEBUG);
       LogKey key3 = new LogKey("com.sos.tools.filemonitor.FileFilter", LoggerLevel.FATAL);
       LogKey key4 = new LogKey("com.sos.filemonitor.FileFilter", LoggerLevel.INFO);
       
       System.out.println("Key1 = key2 : "+key1.equals(key2));
       System.out.println("Key1 = key3 : "+key1.equals(key3));
       System.out.println("Key1 = key4 : "+key1.equals(key4));
   }

}
