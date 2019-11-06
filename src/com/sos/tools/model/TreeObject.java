package com.sos.tools.model;

import java.util.HashMap;
import java.util.Map;


public class TreeObject <T>
{
	protected T object;
	protected String id;
	protected Map <String, TreeObject<T>> children;
	
	public TreeObject(String id, T object)
	{
		this.id = id;
		this.object = object;
		getChildren();
	}
	
	
	public TreeObject(T object)
	{
		this(null, object);
	}

	public TreeObject()
	{
		this(null, null);
	}

	
	public Map<String, TreeObject<T>> getChildren()
	{
		if(children == null)
			children = new HashMap<String, TreeObject<T>>();
		
		return children;
	}

	
	public void setChildren(HashMap<String, TreeObject<T>> children)
	{
		this.children = children;
	}
	
	public void addChild(String id, TreeObject <T> child)
	{
		Map<String, TreeObject<T>> childList = getChildren();
		childList.put(id, child);
	}

	public boolean hasChild(String id)
	{
		return getChildren().containsKey(id);
	}
	
	public TreeObject <T> getChild(String id)
	{
		return (TreeObject <T>) getChildren().get(id);
	}
	
	public TreeObject <T> removeChild(String id)
	{
		return getChildren().remove(id);
	}
	
	public T getObject()
	{
		return object;
	}

	public T setObject(T object)
	{
		T oldObject = this.object;
		this.object = object;
		return oldObject;
	}
	
	public String getId()
	{
		return id;
	}
}
