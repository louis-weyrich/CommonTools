/**
 * 
 */
package com.sos.tools.utilities.tree;

import java.util.List;

/**
 * @author lweyrich
 *
 */
public class DefaultTreeModel <T> implements TreeModel <T>
{
	
	private TreeNode <T> rootNode;

	/**
	 * 
	 */
	public DefaultTreeModel()
	{
		rootNode = getRootNode();
	}
	
	public DefaultTreeModel(TreeNode <T> rootNode)
	{
		rootNode.setIndex(0);
		rootNode.setNodeId("0");
		this.rootNode = rootNode;
	}


	/**
	 * @see com.dc.dpss.utility.datamodel.TreeModel#getRootNode()
	 */
	public TreeNode <T> getRootNode()
	{
		if(rootNode == null)
		{
			rootNode = new TreeNode <T> ();
			rootNode.setNodeId("0");
		}
		return rootNode;
	}

	/**
	 * @see com.dc.dpss.utility.datamodel.TreeModel#getNode(java.lang.String, int)
	 */
	public TreeNode <T> getNode(String parentNodeId, int index)
	{
		String [] ids = parentNodeId.split("_");
		if(getRootNode().getNodeId().startsWith(ids[0]))
		{
			String [] tempIdArray = new String [ids.length - 1];
			System.arraycopy(ids, 1, tempIdArray, 0, ids.length - 1);
			ids = tempIdArray;
			
			return find(getRootNode().getChildren(), ids, index);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param nodes
	 * @param ids
	 * @param index
	 * @return
	 */
	protected TreeNode <T> find(List <TreeNode<T>> nodes, String [] ids, int index)
	{
		for(TreeNode <T> node : nodes)
		{
			if(node.getNodeId().startsWith(ids[0]))
			{
				if(node.isLeaf() && ids.length == 1 && node.getIndex() == index)
				{
					return node;
				}
				else if(!node.isLeaf() && ids.length > 1)
				{
					String [] tempIdArray = new String [ids.length - 1];
					System.arraycopy(ids, 1, tempIdArray, 0, ids.length - 1);
					ids = tempIdArray;
					
					return find(node.getChildren(), ids, index);
				}
			}
		}
		
		return null;
	}
	

	/**
	 * @see com.dc.dpss.utility.datamodel.TreeModel#getNode(java.lang.String, int)
	 */
	public TreeNode <T> getNode(String parentNodeId)
	{
		String [] ids = parentNodeId.split("_");
		if(getRootNode().getNodeId().startsWith(ids[0]))
		{
			if(ids.length > 1)
			{
				String tempValue = ids[0];
				String [] tempIdArray = new String [ids.length - 1];
				System.arraycopy(ids, 1, tempIdArray, 0, ids.length - 1);
				ids = tempIdArray;
				ids[0] = tempValue+"_"+ids[0];
				return find(getRootNode().getChildren(), ids);
			}
			else
			{
				return getRootNode();
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param nodes
	 * @param ids
	 * @param index
	 * @return
	 */
	protected TreeNode <T> find(List <TreeNode<T>> nodes, String [] ids)
	{
		for(TreeNode <T> node : nodes)
		{
			if(node.getNodeId().startsWith(ids[0]))
			{
				if(ids.length == 1)
				{
					return node;
				}
				else if(!node.isLeaf() && ids.length > 1)
				{
					String tempValue = ids[0];
					String [] tempIdArray = new String [ids.length - 1];
					System.arraycopy(ids, 1, tempIdArray, 0, ids.length - 1);
					ids = tempIdArray;
					ids[0] = tempValue+"_"+ids[0];
					
					return find(node.getChildren(), ids);
				}
			}
		}
		
		return null;
	}
	
	public String addNode(TreeNode <T> node)
	{
		TreeNode <T> parent = getRootNode();
		parent.addChildNode(node);
		return node.getNodeId();
	}

	
	public String addNode(String parentNodeId, TreeNode <T> node)
	{
		TreeNode <T> parent = getNode(parentNodeId);
		
		if(parent != null)
		{
			parent.addChildNode(node);
		}
		
		return node.getNodeId();
	}
	
}
