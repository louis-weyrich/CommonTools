/*
 * File: LoggerSession.java
 *
 */
package com.sos.tools.logging;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.utilities.collection.UtilMap;
import com.sos.tools.utilities.comparitor.LogKeyComparator;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class LoggerSession
{
    
    public static final String DEFAULT_CONFIG_LOCATION = "./config/logger.xml";
    
    private static LoggerSession instance;
    private boolean opened = false;
    private File configLocation;
    private UtilMap <LogKey, Logger> loggers;
    

    /**
     * 
     */
    private LoggerSession()
    {
        loggers = new UtilMap <LogKey, Logger> (false);
        loggers.setKeyComparator(new LogKeyComparator());
    }
    
    public void setConfigLocation(File location){
        String path = location.getAbsolutePath();
        if(path.endsWith("logger.xml"))
        {
        	configLocation = location;
        }
        else
        {
        	configLocation = new File(((path.endsWith(File.separator))?path:path+File.separator)+"logger.xml");
        }
    }
    
    public static LoggerSession instance()
    {
        if(instance == null)
            instance = new LoggerSession();
        
        return instance;
    }
    
    
    public void logInfo(String message, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.log(LoggerLevel.INFO, message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void logDebug(String message, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.log(LoggerLevel.DEBUG, message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void logError(Throwable e, String message, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.log(LoggerLevel.ERROR, message);
            }
            catch (IOException ioe)
            {
                e.printStackTrace();
            }
            
            try
            {
                logger.logException(e);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }
    public void logWarn(String message, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.log(LoggerLevel.WARNING, message);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
    
    public void logFatal(Error error, String message, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.log(LoggerLevel.ERROR, message);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            
            try
            {
                logger.logException(error);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

        }
    }

    public void logException(Throwable e, Object c)
    {
        Logger logger = loggers.get(new LogKey(c.getClass().getName(), LoggerLevel.INFO));
        if(logger != null)
        {
            try
            {
                logger.logException(e);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public void openSession()throws IOException
    {
    	if(!opened)
    	{
	        if(configLocation == null)
	        {
	            configLocation = new File(DEFAULT_CONFIG_LOCATION);
	        }
	        
	        if(configLocation.exists())
	        {
	            SAXBuilder builder = new SAXBuilder();
	            Document document = null;
	            
	            try
	            {
	                document = builder.build(configLocation);
	                Element rootNode = document.getRootElement();
	                configLoggers(rootNode.getChildren());
	            }
	            catch (JDOMException e)
	            {
	                e.printStackTrace();
	            }
	            catch (ConfigurationException e)
	            {
	                e.printStackTrace();
	            }
	            
	           
	        }
	        else
	        {
	            LoggerContainer container = new LoggerContainer();
	            container.addLogger(new ConsoleLogger(System.out));
	            loggers.put(new LogKey(null, LoggerLevel.FATAL), container);
	        }
	        
	        opened = true;
    	}
    }
    
    private void configLoggers(List <Element> loggerList) throws ConfigurationException, IOException
    {
        for(Element loggerElement : loggerList)
        {
            if(loggerElement.getName().equals("Logger"))
            {
                LogKey key = new LogKey();
                
                Attribute classPathAttrib = loggerElement.getAttribute("classPath");
                if(classPathAttrib != null)
                {
                    String classPath = classPathAttrib.getValue();
                    if(classPath != null)
                    {
                        key.setClassPath(classPath);
                    }
                }
                
                Attribute logLevelAttrib = loggerElement.getAttribute("logLevel");
                if(logLevelAttrib != null)
                {
                    String logLevel = logLevelAttrib.getValue();
                    if(logLevel != null)
                    {
                        key.setLevel(LoggerLevel.valueOf(logLevel));
                    }
                }
                
                LoggerContainer container = new LoggerContainer();
                for(Element loggerChildElement : loggerElement.getChildren())
                {
                    Logger logger = null;
                    
                    if(loggerChildElement.getName().equals("File"))
                    {
                        logger = configFile(loggerChildElement);
                    }
                    else if(loggerChildElement.getName().equals("Console"))
                    {
                        logger = configConsole(loggerChildElement);
                    }
                    else
                    {
                        logger = new ConsoleLogger(System.out);
                    }
                    
                    if(logger != null)
                    {
                        container.addLogger(logger);
                    }
                }
                
                container.openLog();
                loggers.put(key, container);
            }
        }
        
    }
    
    private Logger configFile(Element fileElement) throws ConfigurationException, IOException
    {
        Attribute locationAttrib = fileElement.getAttribute("location");
        if(locationAttrib != null)
        {
            String value = locationAttrib.getValue();
            if(value != null)
            {
            	boolean append = true;
            	Attribute appendAttrib = fileElement.getAttribute("append");
            	if(appendAttrib != null)
            	{
            		String appendValue = appendAttrib.getValue();
            		append = Boolean.parseBoolean(appendValue);
            	}
            	
                return new FileLogger(value, append);
            }
            
        }
        
        throw new ConfigurationException("Could not find file location");
    }
 
    @SuppressWarnings("rawtypes")
	private Logger configConsole(Element consoleElement) throws ConfigurationException, IOException
    {
        Attribute classAttrib = consoleElement.getAttribute("class");
        if(classAttrib != null)
        {
            String value = classAttrib.getValue();
            if(value != null)
            {
                ClassLoader loader = getClass().getClassLoader();
                Class newClass;
                try
                {
                    
                    if(value.equals("System.out"))
                    {
                        return new ConsoleLogger(System.out);
                    }
                    else
                    {
                        newClass = loader.loadClass(value);
                        Object printStream = newClass.newInstance();
                        if(printStream instanceof PrintStream)
                        {
                            return new ConsoleLogger((PrintStream)printStream);
                        }
                    }
                }
                catch (ClassNotFoundException e)
                {
                    throw new ConfigurationException("Could not load console class.");
                }
                catch (InstantiationException e)
                {
                    throw new ConfigurationException("Could not load console class.");
                }
                catch (IllegalAccessException e)
                {
                    throw new ConfigurationException("Could not load console class.");
                }
            }
            
        }
        
        throw new ConfigurationException("Could not load console class.");
    }
    
    public void closeSession() throws IOException
    {
    	if(opened)
    	{
	        Collection <Logger> loggerList = loggers.values();
	        
	        for(Logger logger : loggerList)
	        {
	            logger.closeLog();
	        }
	        
	        loggers.clear();
	        opened = false;
    	}
    }
    
    
    public boolean isOpened()
    {
        return opened;
    }

}
