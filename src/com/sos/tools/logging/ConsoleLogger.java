/*
 * File: ConsoleLogger.java
 *
 */
package com.sos.tools.logging;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class ConsoleLogger implements Logger
{
    
    private PrintStream stream;
    private String name;

    /**
     * 
     */
    public ConsoleLogger(PrintStream stream)
    {
        this.stream = stream;
        this.name = stream.getClass().getName();
    }

    /**
     * @see com.sos.tools.logging.Logger#opeLog()
     */
    public void openLog() throws IOException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        stream.write(new String("********************************************************************************\n").getBytes());
        stream.write(new String("Logger ("+name+") is opened at "+dateFormat.format(new Date())+"\n").getBytes());
        stream.write(new String("********************************************************************************\n\n\n").getBytes());
        stream.flush();
    }

    /**
     * @see com.sos.tools.logging.Logger#logException(java.lang.Throwable)
     */
    public void logException(Throwable e) throws IOException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        stream.write(("EXCEPTION --> "+e.getClass().getName()+" at "+dateFormat.format(new Date())+"\n").getBytes());
        stream.write(new String("   MESSAGE: "+e.getMessage()+"\n").getBytes());
        
        StackTraceElement [] stack = e.getStackTrace();
        for(StackTraceElement element : stack){
            stream.write(("   "+element.getClassName()+"."+element.getMethodName()+" : line("+element.getLineNumber()+")\n").getBytes());
        }
        
        stream.flush();
        
        if(e instanceof Error){
            closeLog();
        }

    }

    /**
     * @see com.sos.tools.logging.Logger#log(com.sos.tools.logging.LoggerLevel, java.lang.String)
     */
    public void log(LoggerLevel type, String message) throws IOException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        stream.write((type.name()+" "+dateFormat.format(new Date())+" : "+message+"\n").getBytes());
        stream.flush();
        
        if(type == LoggerLevel.FATAL){
            closeLog();
        }

    }

    /**
     * @see com.sos.tools.logging.Logger#closeLog()
     */
    public void closeLog() throws IOException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        stream.write(new String("\n\n\n********************************************************************************\n").getBytes());
        stream.write(new String("Logger ("+name+") was closed at "+dateFormat.format(new Date())+"\n").getBytes());
        stream.write(new String("********************************************************************************\n\n\n").getBytes());
        stream.flush();
        stream.close();
        stream = null;
    }

}
