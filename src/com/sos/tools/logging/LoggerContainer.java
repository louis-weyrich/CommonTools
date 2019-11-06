/*
 * File: LoggerContainer.java
 *
 */
package com.sos.tools.logging;

import java.io.IOException;
import java.util.Iterator;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class LoggerContainer implements Logger
{

    
    private Logger [] loggers = new Logger[0];
    
    /**
     * 
     */
    public LoggerContainer()
    {
        // do nothing
    }
    
    public void addLogger(Logger logger)
    {
        Logger [] temp = new Logger[loggers.length + 1];
        System.arraycopy(loggers, 0, temp, 0, loggers.length);
        temp[loggers.length] = logger;
        loggers = temp;
    }
    
    public Iterator <Logger> loggerIterator()
    {
        return new Iterator <Logger>(){
            
            private int currentIndex = 0;

            public boolean hasNext()
            {
                return currentIndex < loggers.length;
            }

            public Logger next()
            {
                return loggers[currentIndex++];
            }

            public void remove()
            {
                Logger [] temp = new Logger[loggers.length -1];
                if(currentIndex == 0 && loggers.length > 1)
                {
                    System.arraycopy(temp, 1, loggers, 1, loggers.length - 1);
                }
                else
                {
                    System.arraycopy(temp, 0, loggers, 0, currentIndex);
                    System.arraycopy(temp, currentIndex+ 1, loggers, currentIndex, loggers.length - currentIndex);
                }
                
            }
            
        };
    }
    
    public void logException(Throwable e) throws IOException
    {
        for(Logger logger : loggers)
        {
            logger.logException(e);
        }
    }
    
    public void log(LoggerLevel type, String message) throws IOException
    {
        for(Logger logger : loggers)
        {
            logger.log(type, message);
        }
    }

    /**
     * @see com.sos.tools.logging.Logger#opeLog()
     */
    public void openLog() throws IOException
    {
        for(Logger logger : loggers)
        {
            logger.openLog();
        }
    }

    /**
     * @see com.sos.tools.logging.Logger#closeLog()
     */
    public void closeLog()
    throws IOException
    {
        for(Logger logger : loggers)
        {
            logger.closeLog();
        }
    }

}
