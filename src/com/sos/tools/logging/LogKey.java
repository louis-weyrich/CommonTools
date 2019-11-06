/*
 * File: LogKey.java
 *
 */
package com.sos.tools.logging;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class LogKey
{
    
    private String classPath;
    private LoggerLevel level;

    /**
     * 
     */
    public LogKey()
    {
        
    }
    
    public LogKey(String classPath, LoggerLevel level)
    {
        this.classPath = classPath;
        this.level = level;
    }

    @Override
    public String toString()
    {
        return level.ordinal()+":"+classPath;
    }
    
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof LogKey)
        {
            LogKey key = (LogKey)obj;
            String c =  "";
            if(classPath != null)
            {
                c = (classPath.endsWith("*"))?classPath.substring(0, classPath.length()-1):classPath;
            }
            return (key.getClassPath().startsWith(c))?(key.getLevel().ordinal() <= level.ordinal()):false;
            
        }
        else if(obj instanceof String)
        {
            boolean value = false;            
            String s = (String)obj;
            
            int index = s.indexOf(":");
            
            if(index == -1)
            {
                if(classPath != null)
                {
                    String c = (classPath.endsWith("*"))?classPath.substring(0, classPath.length()-1):classPath;
                    value = (s.startsWith(c));
                }
                
                if(level != null && !value)
                {
                    try
                    {
                        value = (level.ordinal() == Integer.parseInt(s));
                    }
                    catch(NumberFormatException e)
                    {
                        
                    }
                }
            }
            else
            {
                String [] parts = s.split(":");
                 
                if(level != null && !value)
                {
                    value = (level.name().toLowerCase().equals(parts[0].toLowerCase()));
                }

                
                if(classPath != null)
                {
                    String c = (classPath.endsWith("*"))?classPath.substring(0, classPath.length()-1):classPath;
                    value = (parts[1].startsWith(c));
                }
            }
            
            return value;
        }
        
        return false;
    }

    
    public String getClassPath()
    {
        return this.classPath;
    }

    
    public void setClassPath(String classPath)
    {
        this.classPath = classPath;
    }

    
    public LoggerLevel getLevel()
    {
        return this.level;
    }

    
    public void setLevel(LoggerLevel level)
    {
        this.level = level;
    }

}
