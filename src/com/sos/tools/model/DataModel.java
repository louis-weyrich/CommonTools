/*
 * 
 *
 */
package com.sos.tools.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.event.EventListenerList;

import com.sos.tools.event.EventType;
import com.sos.tools.event.DataStateChangeEvent;
import com.sos.tools.event.DataStateChangeListener;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class DataModel <K, V> implements DataStateChangeListener <K,V>
{
    
    protected EventListenerList  eventsListeners = null;
    protected Map <K, V> data;
    protected boolean watch = false;

    /**
     * 
     */
    public DataModel()
    {
        // Do Nothing
    }
    
    public DataModel(boolean watch)
    {
        setWatch(watch);
    }

    public void addDataStateChangeListener(DataStateChangeListener <K,V> listener)
    {
        if(eventsListeners == null)
            eventsListeners = new EventListenerList();
        
        eventsListeners.add(DataStateChangeListener.class, listener);
    }
    
    protected void fireStateChangeEvent(K key, V oldValue, V newValue, EventType type)
    {
        
        fireDataStateChangeEvent(new DataStateChangeEvent <K, V> (this, key, oldValue, newValue, type));
    }
    
    @SuppressWarnings("unchecked")
	protected void fireDataStateChangeEvent(DataStateChangeEvent <K,V> event)
    {
        if(eventsListeners != null)
        {
            DataStateChangeListener <K,V> [] listeners = eventsListeners.getListeners(DataStateChangeListener.class);
            for(DataStateChangeListener <K,V> listener : listeners)
            {
                listener.dataStateChanged(event);
            }
        }
    }
    
    public Map <K, V> getData()
    {
        if(data == null)
            data = new HashMap <K, V>();
        
        return data;
    }
    
    public int getRowCount()
    {
    	return getData().size();
    }

    @SuppressWarnings("unchecked")
	public V setData(K modelName, V value)
    {
        if(data == null)
            data = new HashMap <K, V>();
        
        V oldValue = data.get(modelName);
        
        if((value != null && oldValue != null)?!value.equals(oldValue):true)
        {
            fireStateChangeEvent(modelName, oldValue, value, (data.containsKey(modelName))? EventType.UPDATE : EventType.CREATE);
            data.put(modelName, value);
            if(value instanceof DataStateChangeListener)
            {
                ((DataStateChangeListener<K, V>)value).addDataStateChangeListener(this);
            }
        }
        
        return oldValue;
    }
 
    public V removeData(K modelName)
    {
        if(data != null)
        {
            V oldValue = data.remove(modelName);
            fireStateChangeEvent(modelName, oldValue, null, EventType.DELETE);
            return oldValue;
        }
        
        return null;
    }
    
    public V getData(K modelName)
    {
        if(data != null)
        {
            if(watch) fireStateChangeEvent(modelName, data.get(modelName), null, EventType.VIEW);
            
            return data.get(modelName);
        }
        
        return null;
    }
    
    public String getStringData(K modelName)
    {
       return getData(modelName).toString();
    }

    public Integer getIntData(K modelName)
    {
        V data = getData(modelName);
        
        if(data instanceof Number)
        {
            return new Integer(((Number) data).intValue());
        }
        else
        {
            return Integer.getInteger(data.toString());
        }
    }
    
    public Long getLongData(K modelName)
    {
        V data = getData(modelName);
        
        if(data instanceof Number)
        {
            return new Long(((Number) data).longValue());
        }
        else
        {
            return Long.getLong(data.toString());
        }
    }
    
    public Float getFloatData(K modelName)
    {
        V data = getData(modelName);
        
        if(data instanceof Number)
        {
            return new Float(((Number) data).floatValue());
        }
        else
        {
            return Float.valueOf(data.toString());
        }
    }

    public Iterator <K> getModelNameIterator()
    {
        return data.keySet().iterator();
    }
    
    @SuppressWarnings("unchecked")
    public K [] getModelNames()
    {
        return (K [])data.keySet().toArray();
    }
    
    public boolean isWatch()
    {
        return this.watch;
    }

    
    public void setWatch(boolean watch)
    {
        this.watch = watch;
    }

    /**
     * @see com.soso.tools.event.DataStateChangeListener#gameStateChanged(com.thegame.ui.event.DataStateChangeEvent)
     */
    public void dataStateChanged(DataStateChangeEvent <K,V> event)
    {
        fireDataStateChangeEvent(event);
    }
    
    public int size()
    {
        return data.size();
    }
}
