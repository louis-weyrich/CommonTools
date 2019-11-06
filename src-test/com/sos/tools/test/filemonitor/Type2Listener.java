/*
 * File: Type2Listener.java
 *
 */
package com.sos.tools.test.filemonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Iterator;

import com.sos.tools.event.FileChangeEvent;
import com.sos.tools.event.FileChangeListener;
import com.sos.tools.filemonitor.FileChangeType;
import com.sos.tools.logging.LoggerSession;
import com.sos.tools.utilities.CSVParser2;
import com.sos.tools.utilities.collection.RowSet;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class Type2Listener implements FileChangeListener
{

    /**
     * 
     */
    public Type2Listener()
    {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.sos.tools.filemonitor.FileListener#fileChanged(java.io.File, com.sos.tools.filemonitor.FileMonitor)
     */
    public void fileChanged(FileChangeEvent event)
    {
        
        LoggerSession logger = LoggerSession.instance();
        File file = event.getSourceFile();
        logger.logInfo(file.getAbsolutePath()+" has been "+event.getFileChangeType().name()+"ED", this);
        try
        {
            if(event.getFileChangeType() != FileChangeType.DELETE){
                CSVParser2 parser = new CSVParser2(file, ',', true);
                StringBuffer buffer = new StringBuffer();
                
                String [] headers = parser.getHeaders();
                
                for(String header : headers){
                    buffer.append(header+"\t");
                }
                buffer.append("\n");
                while(parser.hasData()){
                    RowSet <String> row = parser.nextRow();
                    Iterator <String> headerIterator = row.headerIterator();
                    
                    
                    while(headerIterator.hasNext()){
                        String header = headerIterator.next();
                        if(header.equals("UnitPrice") || header.equals("PriceModifier") || header.equals("TareWeight")){
                            buffer.append(row.getFloat(header)+"\t");
                        }else if(header.equals("UPC")){
                            buffer.append(row.getLong(header)+"\t");
                        }else{
                            buffer.append(row.getInteger(header)+"\t");
                        }
                    }
                    buffer.append("\n");
                    
                }
                
                logger.logInfo(buffer.toString(), this);
            }
            
            
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
