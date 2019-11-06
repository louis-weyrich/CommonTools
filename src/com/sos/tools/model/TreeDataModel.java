package com.sos.tools.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sos.tools.utilities.collection.UtilIterator;


public class TreeDataModel 
{
	
	protected static final char DEFAULT_TREE_TOKEN = '.';
	protected Map <String, TreeObject<?>> nodes;
	protected boolean hasRoot = false;
	protected String rootId;
	protected char pathToken = DEFAULT_TREE_TOKEN;

	public TreeDataModel()
	{
		this(null);
	}
	
	public TreeDataModel(String rootId)
	{
		if(rootId != null)
		{
			this.rootId = rootId;
			this.hasRoot = true;
		}
		nodes = new HashMap <String, TreeObject<?>> ();
	}
	
	public void clear()
	{
		nodes.clear();
	}
	
	public void setPathToken(char token)
	{
		this.pathToken = token;
	}
	
	public char getPathToken()
	{
		return pathToken;
	}
	
	public boolean hasRootId()
	{
		return this.hasRoot;
	}
	
	public String getRootId()
	{
		return this.rootId;
	}
	
	public boolean hasTreePath(String path)
	{
		return(getTreeNode(path, false) != null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> TreeObject <T> getTreeNode(String tokenId, boolean create)
	{
		String [] path = splitPath(tokenId);
		Iterator <String> iterator = new UtilIterator <String> (path);
		
		if(this.hasRoot)
		{
			if(iterator.hasNext()){
				String rootId = iterator.next();
				if(!this.rootId.equals(rootId))
				{
					return null;
				}
			}
		}
		
		if(iterator.hasNext()){
			String id = iterator.next();
			if(nodes.containsKey(id))
			{
				TreeObject <?> node = nodes.get(id);
				if(iterator.hasNext())
				{
					return (TreeObject <T>)getTreeNode(iterator, node, create);
				}
				else
				{
					return (TreeObject <T>)node;
				}
			}
			else
			{
				if(create)
					return this.buildPath(tokenId);
			}
		}
		
		return null;
	}
	
	protected <T> TreeObject <T> getTreeNode(Iterator <String> iterator, TreeObject <T> node, boolean create)
	{
		if(iterator.hasNext())
		{
			String id = iterator.next();
			if(node.hasChild(id))
			{
				TreeObject <T> childNode = node.getChild(id);
				if(iterator.hasNext())
				{
					return getTreeNode(iterator, childNode, create);
				}
				else
				{
					return (TreeObject<T>)childNode;
				}
			}
			else
			{
				if(create)
				{
					TreeObject <T> childNode = new TreeObject<T>(id, null);
					node.addChild(id, childNode);
					if(iterator.hasNext())
					{
						return getTreeNode(iterator, childNode, create);
					}
					
					return (TreeObject <T>)childNode;
				}
			}
		}
		
		return null;
	}
	
	public <T> T addNode(String path, String id, T object)
	{
		TreeObject<T> parent = getTreeNode(path, true);
		if(parent != null)
		{
			if(parent.hasChild(id))
			{
				TreeObject <T> child = parent.getChild(id);
				T oldObject = child.getObject();
				child.setObject(object);
				return oldObject;
			}
			else
			{
				parent.addChild(id, new TreeObject<T>(id, object));
				return null;
			}
		}
		
		return null;
	}
	
	public <T> T removeNode(String path, String id)
	{
		TreeObject <T> parent = getTreeNode(path, false);
		if(parent.hasChild(id))
		{
			return (T)parent.removeChild(id).getObject();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> TreeObject<T> buildPath(String tokenId)
	{
		if(tokenId == null)
			throw new IllegalArgumentException("could not build path.  Path is null.");
		
		String [] path = splitPath(tokenId);
		Iterator <String> iterator = new UtilIterator <String> (path);
		
		if(iterator.hasNext())
		{
			String id = iterator.next();
			if(hasRootId())
			{
				if(!id.equals(getRootId()))
				{
					throw new IllegalArgumentException("Illegal Path: "+tokenId);
				}
				
				id = iterator.next();
			}
			
			TreeObject <T> treeNode = null;
			TreeObject <T> oldNode = null;
			
			if(nodes.containsKey(id))
			{
				treeNode =  (TreeObject<T>) nodes.get(id);
				oldNode = treeNode;
			}
			else
			{
				treeNode = new TreeObject <T>(id, null);
				oldNode = treeNode;
			}
			
			while(iterator.hasNext())
			{
				String nextid =  iterator.next();
				if(oldNode.hasChild(nextid))
				{
					TreeObject <T> childNode = oldNode.getChild(nextid);
					oldNode = (TreeObject<T>)childNode;
				}
				else
				{
					TreeObject <T> newNode = new TreeObject <T>(nextid, null);
					oldNode.addChild(nextid, newNode);
					oldNode = newNode;
				}
			}
			
			nodes.put(id, treeNode);
			return oldNode;
		}
		
		return new TreeObject <T>(tokenId, null);
	}
	
	protected String [] splitPath(String path)
	{
		char token = getPathToken();
		int startIndex = 0, length = path.length();
		List <String> paths = new ArrayList <String> ();
		
		for(int index = 0; index < length; index++)
		{
			char c = path.charAt(index);
			if(c == token || index + 1 == length)
			{
				if(index + 1 == length) index += 1;
				String subPath = path.substring(startIndex, index);
				paths.add(subPath);
				startIndex = index +1;
			}
		}
		
		String [] pathArray = new String [paths.size()];
		
		return (String [])paths.toArray(pathArray);
	}
}
