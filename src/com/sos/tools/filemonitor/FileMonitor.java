/*
 * File: FileMonitor.java
 *
 */

package com.sos.tools.filemonitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.sos.tools.event.FileChangeEvent;
import com.sos.tools.event.FileChangeListener;

/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileMonitor implements Runnable
{

    private long pollingInterval = 100000;
    private boolean running = true;
    private boolean recursive = false;
    private FileMonitorSession session;


    private HashMap<File, Long> files_; // File -> Long
    private List<FileFilter> filters; // Filters
    private Collection<FileChangeListener> listeners_; 
    private Thread thread;

    /**
     * 
     */
    public FileMonitor(FileMonitorSession session)
    {
        files_ = new HashMap<File, Long>();
        filters = new ArrayList<FileFilter> (1);
        listeners_ = new ArrayList<FileChangeListener>();
        this.session = session;
    }
    
    public void setPollingInterval(long pollingInterval){
        this.pollingInterval = pollingInterval;
    }
    
    public FileMonitorSession getSession(){
        return session;
    }

    /**
     * Stop the file monitor polling.
     */
    public void stop()
    {
        running = false;
    }

    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void addFilter(FileFilter filter)
    {
        if (filters == null)
            filters = new ArrayList<FileFilter>(2);

        filters.add(filter);
    }

    public FileFilter removeFilter(String filterName)
    {
        FileFilter temp = null;

        for (FileFilter fileFilter : filters)
        {
            if (fileFilter.getName().equals(filterName))
            {
                filters.remove(fileFilter);
                temp = fileFilter;
                break;
            }
        }

        return temp;
    }

    /**
     * Add file to listen for. File may be any java.io.File (including a
     * directory) and may well be a non-existing file in the case where the
     * creating of the file is to be trepped.
     * <p>
     * More than one file can be listened for. When the specified file is
     * created, modified or deleted, listeners are notified.
     * 
     * @param file  File to listen for.
     */
    public void addFile(File file) throws IOException
    {
        
        if(!file.exists()){
            throw new IOException(file.getAbsolutePath()+" does not exist");
        }

        if (file.isDirectory())
        {
            if(!files_.containsKey(file)){
                
                files_.put(file, new Long(0));
            }
            File[] fileList = file.listFiles();
            for (File f : fileList)
            {
                if(f.isDirectory()){
                    if(isRecursive())addFile(f);
                }else{
                    if (!files_.containsKey(f))
                    {
                        long modifiedTime = f.exists() ? f.lastModified() : -1;
                        boolean add = true;
                        for (FileFilter filter : filters)
                        {
                            add = (filter.accept(f.getParentFile(), f.getName()));
                            
                        }
                        
                        if(add)files_.put(f, new Long(modifiedTime));
                    }
                }
            }
        }
        else
        {
            if (!files_.containsKey(file))
            {
                long modifiedTime = file.exists() ? file.lastModified() : -1;
                boolean add = true;
                for (FileFilter filter : filters)
                {
                    add = (filter.accept(file.getParentFile(), file.getName()));
                    
                }
                
                if(add)files_.put(file, new Long(modifiedTime));
            }
        }
    }


    /**
     * Remove specified file for listening.
     * 
     * @param file  File to remove.
     */
    public void removeFile(File file)
    {
        files_.remove(file);
    }

    /**
     * Add listener to this file monitor.
     * 
     * @param fileChangeListener  Listener to add.
     */
    public void addListener(FileChangeListener fileChangeListener)
    {
        // Don't add if its already there
        for (Iterator<FileChangeListener> i = listeners_.iterator(); i.hasNext();)
        {
            FileChangeListener listener = i.next();
            if (listener == fileChangeListener)
                return;
        }

        // sole reference to the object.
        listeners_.add(fileChangeListener);
    }

    /**
     * Remove listener from this file monitor.
     * 
     * @param fileChangeListener  Listener to remove.
     */
    public void removeListener(FileChangeListener fileChangeListener)
    {
        for (Iterator<FileChangeListener> i = listeners_.iterator(); i.hasNext();)
        {
            FileChangeListener listener = i.next();
            if (listener == fileChangeListener)
            {
                i.remove();
                break;
            }
        }
    }

 


    /**
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        while (running)
        {
            try
            {
                Thread.sleep(this.pollingInterval);
                Collection<File> files = new ArrayList<File>(files_.keySet());

                for (Iterator<File> i = files.iterator(); i.hasNext();)
                {
                    File file = (File) i.next();
                    if(file.isDirectory()){
                        File [] folderFiles = file.listFiles();
                        for(File folderFile : folderFiles){
                            if(!files_.containsKey(folderFile)){
                                long newModifiedTime = file.exists() ? file.lastModified() : -1;
                                files_.put(folderFile, new Long(newModifiedTime));
                                
                                // Notify listeners
                                for (Iterator<FileChangeListener> j = listeners_.iterator(); j.hasNext();)
                                {
                                    FileChangeListener listener = j.next();
        
                                    // Remove from list if the back-end object has been GC'd
                                    if (listener == null){
                                        j.remove();
                                    }else{
                                        FileChangeEvent event = new FileChangeEvent(folderFile, this, FileChangeType.ADD);
                                        listener.fileChanged(event);
                                    }
                                }
                            }
                        }
                    }else{
                        long lastModifiedTime = ((Long) files_.get(file)).longValue();
                        long newModifiedTime = file.exists() ? file.lastModified() : -1;
    
                        // Check if file has changed
                        if (newModifiedTime != lastModifiedTime)
                        {
    
                            // Register new modified time
                            files_.put(file, new Long(newModifiedTime));
    
                            // Notify listeners
                            for (Iterator<FileChangeListener> j = listeners_.iterator(); j.hasNext();)
                            {
                                FileChangeListener listener = j.next();
    
                                // Remove from list if the back-end object has been GC'd
                                if (listener == null){
                                    j.remove();
                                }else{
                                    FileChangeEvent event = new FileChangeEvent(file, this, file.exists() ? FileChangeType.UPDATE : FileChangeType.DELETE);
                                    listener.fileChanged(event);
                                }
                            }
                        }
                    }
                }
            }
            catch (InterruptedException e)
            {
                running = false;
                e.printStackTrace();
            }catch(Exception e){
                running = false;
                e.printStackTrace();
            }
        }
        
        thread.interrupt();
        thread = null;
    }

    
    public boolean isRecursive()
    {
        return this.recursive;
    }

    
    public void setRecursive(boolean recursive)
    {
        this.recursive = recursive;
    }

}
