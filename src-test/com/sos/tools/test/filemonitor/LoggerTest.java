/*
 * File: LoggerTest.java
 *
 */

package com.sos.tools.test.filemonitor;

import java.io.IOException;

import org.junit.Test;

import com.sos.tools.logging.LoggerSession;

/**
 * ...
 * 
 * @author louis.weyrich
 */
public class LoggerTest
{

    @Test
    public void test()
    {
        LoggerSession session = LoggerSession.instance();
        try
        {
            session.openSession();
            session.logInfo("hello", this);
            session.logDebug("hello again", this);
            session.logException(new NullPointerException("This is Null!!!!!!!"), this);
            session.closeSession();

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
