/*
 * File: RowDataModel.java
 *
 */
package com.sos.tools.model;

import java.util.Iterator;

import com.sos.tools.exception.ModelException;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class RowDataModel <V> extends DataModel<Integer, V> implements Iterable <V>
{
    private HeaderDataModel headers;
    private Integer index;
    
    public RowDataModel(HeaderDataModel headers, Integer index)
    {
        this.headers = headers;
      if(headers != null)
      {
	      for(int i = 0; i < headers.size(); i++)
	      {
	          setData(new Integer(i), null);
	      }
      }

      this.index = index;
    }
    
    public Integer getIndex()
    {
        return index;
    }
    
    public String getStringData(int index)
    {
        return getStringData(new Integer(index));
    }
    
    public String getStringData(String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        if(!headers.hasHeader(headerName))
            throw new ModelException("Column with name "+headerName+" was not found.");

        
        Integer index = headers.getData(headerName);        
        return getStringData(index);
    }
    
    public Integer getIntegerData(int index)
    {
        return getIntData(new Integer(index));
    }
    
    public Integer getIntegerData(String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        if(!headers.hasHeader(headerName))
            throw new ModelException("Column with name "+headerName+" was not found.");

        
        Integer index = headers.getData(headerName);        
        return getIntData(index);
    }
    
    public Long getLongData(int index)
    {
        return getLongData(new Integer(index));
    }
    
    public Long getLongData(String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        if(!headers.hasHeader(headerName))
            throw new ModelException("Column with name "+headerName+" was not found.");

        
        Integer index = headers.getData(headerName);        
        return getLongData(index);
    }

    
    public Float getFloatData(int index)
    {
        return getFloatData(new Integer(index));
    }
    
    public Float getFloatData(String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        if(!headers.hasHeader(headerName))
            throw new ModelException("Column with name "+headerName+" was not found.");

        
        Integer index = headers.getData(headerName);        
        return getFloatData(index);
    }
    
    public V setData(int index, V value)
    {
        if(headers != null)
        {
            if(index >= headers.size())
            {
                throw new ArrayIndexOutOfBoundsException("Index("+index+") is out of bounds: size("+headers.size()+")");
            }
        }
        return setData(new Integer(index), value);
    }

    public V setData(String headerName, V value)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        if(!headers.hasHeader(headerName))
            throw new ModelException("Column with name "+headerName+" was not found.");
        
        Integer index = headers.getData(headerName);
        return setData(index, value);
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof Integer)
        {
            Integer value = (Integer)obj;
            return index.equals(value);
        }
        
        return false;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator <V> iterator()
    {
        return data.values().iterator();
    }
}
