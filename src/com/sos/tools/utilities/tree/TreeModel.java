package com.sos.tools.utilities.tree;

public interface TreeModel <T>
{
	public TreeNode <T> getRootNode();
	
	public TreeNode <T> getNode(String parentNodeId, int index);
	
	public TreeNode <T> getNode(String parentNodeId);

	
}
