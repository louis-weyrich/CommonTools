package com.sos.tools.utilities.tree;

import java.util.ArrayList;
import java.util.List;

public class  TreeNode <T>
{
	private T value;
	private TreeNode <T> parent;
	private String nodeId;
	private Integer index;
	private List <TreeNode <T>> children;
	

	public TreeNode()
	{
		setNodeId("0");
	}

	public TreeNode(T value)
	{
		setValue(value);
	}

	public TreeNode(T value, String nodeId)
	{
		setValue(value);
		setNodeId(nodeId);
	}

	public TreeNode(T value, TreeNode <T> parent)
	{
		setValue(value);
		parent.addChildNode(this);
		setParent(parent);
	}

	public TreeNode(T value, TreeNode <T> parent, String nodeId)
	{
		setValue(value);
		parent.addChildNode(this);
		setParent(parent);
		setNodeId(nodeId);
	}


	public T getValue()
	{
		return value;
	}


	public void setValue(T value)
	{
		this.value = value;
	}


	public TreeNode<T> getParent()
	{
		return parent;
	}


	public void setParent(TreeNode<T> parent)
	{
		this.parent = parent;
	}


	public String getNodeId()
	{
		return nodeId;
	}


	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}


	public Integer getIndex()
	{
		return index;
	}


	public void setIndex(Integer index)
	{
		this.index = index;
	}


	public List <TreeNode<T>> getChildren()
	{
		if(children == null)
		{
			children = new ArrayList <TreeNode<T>> (0);
		}
		
		return children;
	}


	public void setChildren(List<TreeNode<T>> children)
	{
		this.children = children;
	}
	
	public void addChildNode(TreeNode <T> child)
	{
		List <TreeNode<T>> children = getChildren();
		
		int size = children.size();
		child.setNodeId(getNodeId()+"_"+((child.getNodeId() != null)?child.getNodeId():size+1));
		child.setIndex(size);
		child.setParent(this);
		children.add(child);
	}
	
	public int childCount()
	{
		return getChildren().size();
	}
	
	public boolean hasChildren()
	{
		return (getChildren().size() > 0);
	}
	
	public boolean hasValue()
	{
		return (value != null);
	}
	
	public boolean isRoot()
	{
		return (parent != null);
	}
	
	public boolean isLeaf()
	{
		return (getChildren().size() == 0);
	}

}
