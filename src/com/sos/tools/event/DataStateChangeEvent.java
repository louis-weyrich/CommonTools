/*
 * File: ameStateEvent.java
 *
 */
package com.sos.tools.event;

import java.util.EventObject;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class DataStateChangeEvent <K, V> extends EventObject
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7964091036271729406L;
	protected K modelKey;
    protected V oldValue;
    protected V newValue;
    protected EventType type;
    

    /**
     * @param arg0
     */
    public DataStateChangeEvent(Object source, K modelKey, V oldValue, V newValue, EventType type)
    {
        super(source);
        this.modelKey = modelKey;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = type;
    }


    
    public K getModeKey()
    {
        return this.modelKey;
    }
    
    public Object getOldValue()
    {
        return this.oldValue;
    }
    
    public Object getNewValue()
    {
        return this.newValue;
    }

    public EventType getEventType(){
        return type;
    }
}
