/*
 * File: FileMonitorSession.java
 *
 */
package com.sos.tools.filemonitor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import com.sos.tools.event.FileChangeListener;
import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.logging.LoggerSession;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileMonitorSession
{
    
    
    private Map <String, FileMonitor> monitors;
    private LoggerSession logger = null;
    

    /**
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * 
     */
    public FileMonitorSession(String config) throws JDOMException, IOException, ConfigurationException
    {
       this(new File(config));
    }
    
    public FileMonitorSession(File config) throws JDOMException, IOException, ConfigurationException
    {
        if(!config.exists())
        {
            throw new IOException("Configuration File: "+config.getAbsolutePath()+" does not exist");
        }
        
        logger = LoggerSession.instance();
        if(!logger.isOpened()) logger.openSession();
        
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(config);
        Element rootNode = document.getRootElement();
        configMonitors( rootNode.getChildren());
        
        while(!monitors.isEmpty())
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                logger.logException(e, this);
            }
        }
        
        
        logger.closeSession();
        
    }
    
    private void configMonitors(List <Element> monotorList) throws ConfigurationException, IOException
    {
        monitors = new HashMap <String, FileMonitor> (monotorList.size());
        
        for(Element configElement : monotorList)
        {
            FileMonitor monitor = new FileMonitor(this);
            String name = configElement.getAttributeValue("name");
            
            if(name == null)
                throw new ConfigurationException("Configuration is missing the name attribute.");
            
            for(Element child : configElement.getChildren())
            {
                if(child.getName().equalsIgnoreCase("Timer"))
                {
                    try
                    {
                        String timerValue = child.getValue();
                        monitor.setPollingInterval(Long.parseLong(timerValue));
                    }
                    catch(NumberFormatException e)
                    {
                        throw new ConfigurationException("Could not parse Timer for "+name);
                    }
                }
                else if(child.getName().equalsIgnoreCase("Folders"))
                {
                    configuteFolders(child, monitor);
                }
                else if(child.getName().equalsIgnoreCase("Files"))
                {
                    configuteFiles(child, monitor);
                }
                else if(child.getName().equalsIgnoreCase("Listeners"))
                {
                    configuteListeners(child, monitor);
                } 
            }
            
            monitor.start();
            monitors.put(name, monitor);
        }
    }
    
    protected void configuteFolders(Element foldersElement, FileMonitor monitor) throws IOException, ConfigurationException
    {
        List <Element> folderChildren = foldersElement.getChildren();
        for(Element folderElement : folderChildren)
        {
            Element locationElement = folderElement.getChild("Location");
            if(locationElement != null)
            {
                
                Attribute recursiveAttribute = locationElement.getAttribute("recursive");
                if(recursiveAttribute != null)
                {
                    try
                    {
                        boolean recursiveValue = recursiveAttribute.getBooleanValue();
                        monitor.setRecursive(recursiveValue);
                    }
                    catch (DataConversionException e)
                    {
                        throw new ConfigurationException(e.getMessage());
                    }
                }
                
                String locationValue = locationElement.getValue();
                monitor.addFile(new File(locationValue));
            }
            else
            {
                throw new ConfigurationException("Folder configuration does not contain a Location");
            }
            
            configurePatterns(folderElement.getChild("Patterns"), monitor);
        }
    }
    
    
    protected void configurePatterns(Element patternsElement, FileMonitor monitor) throws ConfigurationException{
        if(patternsElement!= null)
        {
            FileFilter filter = new FileFilter();
            List <Element> patternsChildren = patternsElement.getChildren();
            for(Element patternElement : patternsChildren)
            {
                
                Attribute nameAttribute = patternElement.getAttribute("name");
                if(nameAttribute != null)
                {
                    filter.setName(nameAttribute.getValue());
                }
                
                try
                {
                    Attribute typeAttribute = patternElement.getAttribute("include");
                    if(typeAttribute != null) filter.setInclude(typeAttribute.getBooleanValue());
                }
                catch (DataConversionException e)
                {
                    throw new ConfigurationException(e.getMessage());
                }
               
                try
                {
                    Attribute recursiveAttribute = patternElement.getAttribute("recursive");
                    if(recursiveAttribute != null) filter.setInclude(recursiveAttribute.getBooleanValue());
                }
                catch (DataConversionException e)
                {
                    throw new ConfigurationException(e.getMessage());
                }
                
                Element fileElement = patternElement.getChild("File");
                if(fileElement != null)
                {
                    filter.setFilePattern(fileElement.getValue());
                }
                
                monitor.addFilter(filter);
            }
        }
    }

    protected void configuteFiles(Element filesElement, FileMonitor monitor) 
    throws ConfigurationException
    {
        List <Element> filesChildList = filesElement.getChildren();
        for(Element fileElement : filesChildList)
        {
            try
            {
                monitor.addFile(new File(fileElement.getValue()));
            }
            catch (IOException e)
            {
                throw new ConfigurationException(e.getMessage());
            }
        }
    }
    
    
    protected void configuteListeners(Element listenersElement, FileMonitor monitor) 
    throws ConfigurationException
    {
        List <Element> listenersElementList = listenersElement.getChildren();
        for(Element ListenerElement : listenersElementList)
        {
            String listenerClassString = ListenerElement.getValue();
            if(listenerClassString != null)
            {
                try
                {
                    Class<?> listenerClass = getClass().getClassLoader().loadClass(listenerClassString);
                    Object listenerInstance = listenerClass.newInstance();
                    if(listenerInstance instanceof FileChangeListener)
                    {
                        monitor.addListener((FileChangeListener)listenerClass.newInstance());
                    }
                }
                catch (ClassNotFoundException e)
                {
                    throw new ConfigurationException(e.getMessage());
                }
                catch (InstantiationException e)
                {
                    throw new ConfigurationException(e.getMessage());
                }
                catch (IllegalAccessException e)
                {
                    throw new ConfigurationException(e.getMessage());
                }
                
            }
        }
    }
    
    public void closeMonitors()
    {
        logger.logInfo("Closing all monitors", this);
        Iterator <String> keys = monitors.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            FileMonitor monitor = monitors.get(key);
            monitor.stop();
        }
    }
    
    public void startMonitors()
    {
        logger.logInfo("Starting all monitors", this);
        Iterator <String> keys = monitors.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            FileMonitor monitor = monitors.get(key);
            monitor.start();
        }
    }
    
    public void removeMonitors()
    {
        logger.logInfo("Rermoving all monitors", this);
        Iterator <String> keys = monitors.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            FileMonitor monitor = monitors.get(key);
            monitor.stop();
            monitors.remove(key);
        }
    }
    
    @Override
    public void finalize()
    {
        try
        {
            closeMonitors();
            logger.closeSession();
        }
        catch (IOException e)
        {
            // add logging
        }
    }



    /**
     * @param args
     */
    public static void main(String[] args)
    {
        if(args.length == 1)
        {
           
            LoggerSession logger = LoggerSession.instance();
            
            
            try
            {
                if(!logger.isOpened()) logger.openSession();
                new FileMonitorSession(args[0]);
            }
            catch (JDOMException e)
            {
               logger.logException(e, FileMonitorSession.class);
            }
            catch (IOException e)
            {
                logger.logException(e, FileMonitorSession.class);
            }
            catch (ConfigurationException e)
            {
                logger.logException(e, FileMonitorSession.class);
            }
           
        }
        else
        {
            printUsage();
        }

    }
    
    public static String printUsage()
    {
        return"";
    }


}
