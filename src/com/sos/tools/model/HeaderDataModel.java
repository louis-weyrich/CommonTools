/*
 * File: HeaderDataModel.java
 *
 */
package com.sos.tools.model;

import java.util.Collection;
import java.util.Iterator;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class HeaderDataModel extends DataModel<String, Integer> implements Iterable <String>
{
    
    /**
     * 
     */
    public HeaderDataModel(){ /* do nothing*/ }
    
    /**
     * 
     */
    public HeaderDataModel(String [] headers)
    {
        addHeaders(headers);
    }
    
    /**
     * 
     */
    public HeaderDataModel(Collection <String> headers)
    {
        addHeaders((String [])headers.toArray());
    }

    public void addHeaders(String [] headers)
    {
        int index = 0;
        for(String header : headers){
            setData(header, new Integer(index++));
        }
    }
    
    public void addHeader(String headerName)
    {
        setData(headerName, new Integer(data.size()));
    }
    
    
    public Integer addHeader(String headerName, int index)
    {
        return setData(headerName, new Integer(index));
    }

    public boolean hasHeader(String headerName)
    {
        return data.containsKey(headerName);
    }
    
    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<String> iterator()
    {
        return data.keySet().iterator();
    }

}
