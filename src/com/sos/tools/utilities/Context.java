package com.sos.tools.utilities;

import com.sos.tools.model.TreeDataModel;


public class Context
{
	
	protected static Context _instance;
	protected TreeDataModel context;
	

	private Context()
	{
		context = new TreeDataModel ();
	}
	
	public static Context getInstance()
	{
		if(_instance == null)
		{
			if(_instance == null)
			{
				synchronized(Context.class)
				{
					_instance = new Context();
				}
			}
		}
		
		return _instance;
	}
	
	public void removeAll()
	{
		context.clear();
	}
	
	public synchronized <T> T addToContextPath(String path, String id, T object)
	{
		T t = context.addNode(path, id, object);
		return t;
	}
	
	public synchronized boolean hasContextPath(String path)
	{
		return context.hasTreePath(path);
	}

	@SuppressWarnings("unchecked")
	public synchronized <T> T getContext(String path)
	{
		return (T)context.getTreeNode(path, false).getObject();
	}
	
	public synchronized <T> T removeContext(String path, String id)
	{
		return context.removeNode(path, id);
	}
	
}
