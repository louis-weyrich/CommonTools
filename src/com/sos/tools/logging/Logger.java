/*
 * File: Logger.java
 *
 */
package com.sos.tools.logging;

import java.io.IOException;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public interface Logger
{
    public void openLog() throws IOException;
    public void logException(Throwable e) throws IOException;
    public void log(LoggerLevel type, String message) throws IOException;
    public void closeLog() throws IOException;
}
