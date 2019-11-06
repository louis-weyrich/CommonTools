package com.sos.tools.utilities.btree;

public interface NodeBuilder <ID, T>
{
	public Node <ID, T> createNewNode(ID theID, T theValue);
}
