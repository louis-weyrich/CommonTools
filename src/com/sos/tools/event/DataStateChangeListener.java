/*
 * File: GameStateChangeEvent.java
 *
 */
package com.sos.tools.event;

import java.util.EventListener;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public interface DataStateChangeListener <K,V> extends EventListener
{
    public void dataStateChanged(DataStateChangeEvent <K,V> event);
    public void addDataStateChangeListener(DataStateChangeListener <K,V> listener);
}
