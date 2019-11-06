package com.sos.tools.utilities.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author louisweyrich
 *
 * @param <ID>
 * @param <T>
 */
public class BinaryTree  <ID, T>
{
	// The root node
	private Node <ID, T> root = null;
	
	// the total node count
	private int nodeCount = 0;
	
	// A list of Nodes by oerder inserted 
	private List <Node <ID, T>> insertOrder;
	
	// A class used to buuild new nodes
	private NodeBuilder <ID, T> nodeBuilder;

	
	/**
	 * Default constructor
	 */
	public BinaryTree() 
	{
		this.insertOrder = new ArrayList <Node <ID, T>> ();
		this.nodeBuilder = createDefaultHelper();
	}
	
	/**
	 * A constructor with a custom implementation of NodeBuilder interface
	 * 
	 * @param nodeBuilder - custom implementation of the NodeBuilder
	 * to create new nodes
	 */
	public BinaryTree(NodeBuilder <ID, T> nodeBuilder) 
	{
		insertOrder = new ArrayList <Node <ID, T>> ();
		this.nodeBuilder = nodeBuilder;
	}
	
	/**
	 * creates an anonymous class of the NodeBuilder.  This is the default implementation. 
	 * 
	 * @return a new instance of the default NodeBuilder
	 */
	private NodeBuilder <ID, T> createDefaultHelper()
	{
		return new NodeBuilder  <ID, T> ()
		{
			public Node <ID, T> createNewNode(ID theID, T theValue)
			{
				return new BinaryNode <ID, T> (theID, theValue);
			}
		};
	}
	
	/**
	 * Set a new instance of the NodeBuilder Interface
	 * 
	 * @param nodeBuilder
	 */
	public void setNodeBuilder(NodeBuilder <ID, T> nodeBuilder)
	{
		this.nodeBuilder = nodeBuilder;
	}
	
	/**
	 * The total number of nodes added to the tree
	 * 
	 * @return
	 */
	public int size()
	{
		return insertOrder.size();
	}

	/**
	 * The root node
	 * 
	 * @return the root node
	 */
	public Node <ID, T> getRoot()
	{
		return root;
	}
	
	/**
	 * Gets a list of Nodes by insert order
	 * 
	 * 
	 * @return
	 */
	public List <Node <ID, T>> getInsertOrder()
	{
		return insertOrder;
	}
	
	/**
	 * Gets a count of depth from the node to the root.
	 * 
	 * @param node
	 * @return
	 */
	public int getNodeDepth(Node <ID, T> node)
	{
		if(node != null)
			return getNodeDepth(node.getID());
		else
			return -1;
	}
	
	/**
	 * Gets a count of depth from the node to the root.
	 * 
	 * @param id
	 * @return
	 */
	public int getNodeDepth(ID id)
	{
		if(id == null)
		{
			return -1;
		}
		
		Node <ID, T> node = findNode(id);
		Node <ID, T> parent = node;
		int depth = -1;
		
		while(parent != null)
		{
			depth++;
			parent = parent.getParent();
		}
		
		return depth;
	}
	
	/**
	 * Gets the height of the node from the node to the right and left leaf.
	 * 
	 * @param id
	 * @return
	 */
	public NodeHeight getNodeHeight(ID id)
	{
		NodeHeight nodeHeight = new NodeHeight();
		
		if(id == null)
		{
			return nodeHeight;
		}
		
		Node <ID, T> node = findNode(id);
		Node <ID, T> child = node;
		
		// get left height first
		while(child != null)
		{
			nodeHeight.leftHeight++;
			child = child.getLeftChild();
		}
		
		child = node;
		
		// get right height next
		while(child != null)
		{
			nodeHeight.rightHeight++;
			child = child.getRightChild();
		}

		return nodeHeight;
	}
	
	/**
	 * Add a new node to the tree
	 * 
	 * @param id
	 * @param value
	 * @return
	 */
	public boolean addNode(ID id, T value)
	{
		if(id == null)
		{
			return false;
		}
		
		Node <ID, T> node = (Node<ID, T>)nodeBuilder.createNewNode(id, value);
		
		if(insertOrder.contains(node))
		{
			return false;
		}
		
		insertOrder.add(node);
		
		if(root == null)
		{
			root = node;
		}
		else
		{
			Node <ID, T> focusNode = root;
			Node <ID, T> parentNode = null;
			
			while(true)
			{
				parentNode = focusNode;
				
				if(id.hashCode() < focusNode.getID().hashCode())
				{
					focusNode = focusNode.getLeftChild();
					
					if(focusNode == null)
					{
						parentNode.setLeftChild(node);
						break;
					}
				}
				else
				{
					focusNode = focusNode.getRightChild();
					
					if(focusNode == null)
					{
						parentNode.setRightChild(node);
						break;
					}
				}
			}
			
		}
		
		nodeCount++;
		return true;
	}
	
	/**
	 * The total number of nodes added to the tree
	 * 
	 * @return
	 */
	public int getNodeCount()
	{
		return nodeCount;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public List <Node <ID, T>> inOrderTransversal()
	{
		return inOrderTransversal(getRoot(), new ArrayList <Node <ID, T>> ());
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public List <Node <ID, T>> inOrderTransversal(Node <ID, T> node)
	{
		return inOrderTransversal(node, new ArrayList <Node <ID, T>> ());
	}

	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	public List <Node <ID, T>> inOrderTransversal(Node <ID, T> node, List <Node <ID, T>> list)
	{
		if(node != null)
		{
			list = inOrderTransversal(node.getLeftChild(), list);
			list.add(node);
			list = inOrderTransversal(node.getRightChild(), list);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Node <ID, T>> preOrderTransversal(Node <ID, T> node)
	{
		return preOrderTransversal(node, new ArrayList <Node <ID, T>> ());
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Node <ID, T>> preOrderTransversal()
	{
		return preOrderTransversal(getRoot(), new ArrayList <Node <ID, T>> ());
	}

	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	public List <Node <ID, T>> preOrderTransversal(Node <ID, T> node, List <Node <ID, T>> list)
	{
		if(node != null)
		{
			list.add(node);
			
			if(node.hasLeftChild())
				list = preOrderTransversal(node.getLeftChild(), list);
			
			if(node.hasRightChild())
				list = preOrderTransversal(node.getRightChild(), list);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Node <ID, T>> postOrderTransversal()
	{
		return postOrderTransversal(getRoot(), new ArrayList <Node <ID, T>> ());
	}

	
	/**
	 * 
	 * @return
	 */
	public List <Node <ID, T>> postOrderTransversal(Node <ID, T> node)
	{
		return postOrderTransversal(node, new ArrayList <Node <ID, T>> ());
	}

	/**
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	public List <Node <ID, T>> postOrderTransversal(Node <ID, T> node, List <Node <ID, T>> list)
	{
		if(node != null)
		{
			list = postOrderTransversal(node.getLeftChild(), list);
			list = postOrderTransversal(node.getRightChild(), list);
			list.add(node);
		}
		
		return list;
	}
	
	/**
	 * Finds the common ancestor by nodes
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public Node <ID, T> findCommonAncestor(Node <ID, T> node1, Node <ID, T> node2)
	{
		Node <ID, T> commonNode = null;
		List <Node <ID, T>> list1 = new ArrayList <Node <ID, T>> ();
		List <Node <ID, T>> list2 = new ArrayList <Node <ID, T>> ();
		
		while(node1 != null)
		{
			list1.add(node1);
			node1 = node1.getParent();
		}
		
		while(node2 != null)
		{
			list2.add(node2);
			node2 = node2.getParent();
		}
		
		for(Node <ID, T> n1 : list1)
		{
			for(Node <ID, T> n2 : list2)
			{
				if(n1.equals(n2))
				{
					return n2;
				}
			}
		}
		return commonNode;
	}
	
	/**
	 * Finds the common ancestor 
	 * 
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */
	public Node <ID, T> findCommonAncestor(ID key1, ID key2)
	{
		Node <ID, T> node1 = findNode(key1);
		Node <ID, T> node2 = findNode(key2);
		
		return findCommonAncestor(node1, node2);
	}
	
	/**
	 * Finds a node by ID
	 * 
	 * @param id
	 * @return
	 */
	public Node <ID, T> findNode(ID id)
	{
		Node <ID, T> focusNode = root;
		
		if(focusNode != null)
		{
			while(true)
			{
				if(focusNode.getID().equals(id))
				{
					return focusNode;
				}
				else if(focusNode.getID().hashCode() > id.hashCode())
				{
					focusNode = focusNode.getLeftChild();
				}
				else
				{
					focusNode = focusNode.getRightChild();
				}
				
				if(focusNode == null) 
				{
					return null;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * removes an node by ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeNode(ID id)
	{
		Node <ID, T> node = findNode(id);
		
		if(this.insertOrder.contains(node))
		{
			this.insertOrder.remove(node);
		}
		
		boolean nodeRemoved = false;
		
		if(node != null)
		{
			if(node.isLeaf())
			{
				if(node.isRoot())
				{
					root = null;
				}
				else
				{					
					node.detach();
				}
				
				this.nodeCount--;
				nodeRemoved = true;
			}
			else if(node.hasBothChildren())
			{
				Node <ID, T> leftChild 		= node.getLeftChild();
				Node <ID, T> rightChild 	= node.getRightChild();
				Node <ID, T> rightsLeftMost = (rightChild.hasLeftChild())?rightChild.getLeftMostChild():rightChild;
				Node <ID, T> parent 		= node.getParent();
				
				if(node.isRoot())
				{
					node.detach();
					rightsLeftMost.getParent().setLeftChild(null);
					root = rightsLeftMost;
					rightsLeftMost.setParent(null);
					if(!rightsLeftMost.equals(rightChild))
					{
						rightsLeftMost.setRightChild(rightChild);
					}
					rightsLeftMost.setLeftChild(leftChild);
				}
				else
				{
					if(node.isLeftChild())
					{
						node.detach();
						rightsLeftMost.getParent().setLeftChild(null);
						parent.setLeftChild(rightsLeftMost);
						if(!rightsLeftMost.equals(rightChild))
						{
							rightsLeftMost.setRightChild(rightChild);
						}
						rightsLeftMost.setLeftChild(leftChild);
					}
					else
					{
						node.detach();
						rightsLeftMost.getParent().setLeftChild(null);
						parent.setRightChild(rightsLeftMost);
						if(!rightsLeftMost.equals(rightChild))
						{
							rightsLeftMost.setRightChild(rightChild);
						}
						rightsLeftMost.setLeftChild(leftChild);
					}
				}
				
				this.nodeCount--;
				nodeRemoved = true;
			}
			else if(node.hasLeftChild())
			{
				Node <ID, T> leftChild = node.getLeftChild();
				
				if(node.isRoot())
				{
					node.detach();
					root = leftChild;
					root.setParent(null);
				}
				else
				{
					Node <ID, T> parent = node.getParent();
					
					if(node.isLeftChild())
					{
						node.detach();
						parent.setLeftChild(leftChild);
					}
					else
					{
						node.detach();
						parent.setRightChild(leftChild);
					}
				}
				
				this.nodeCount--;
				nodeRemoved = true;
			}
			else
			{
				Node <ID, T> rightChild = node.getRightChild();
				
				if(node.isRoot())
				{
					node.detach();
					root = rightChild;
					root.setParent(null);
				}
				else
				{
					Node <ID, T> parent = node.getParent();
					
					if(node.isLeftChild())
					{
						node.detach();
						parent.setLeftChild(rightChild);
					}
					else
					{
						node.detach();
						parent.setRightChild(rightChild);
					}
				}
				
				this.nodeCount--;
				nodeRemoved = true;
			}
		}
		
		return nodeRemoved;
	}
	
	/**
	 * A class to hold node height for left and right from a node to the leaf.
	 * 
	 * @author Louis Weyrich
	 *
	 */
	public class NodeHeight
	{
		public int leftHeight = -1;
		public int rightHeight = -1;
		
		public String toString()
		{
			return "Left Height = "+leftHeight+"; Right Height = "+rightHeight;
		}
	}
	
	// the main
	public static void main(String []args)
	{
		BinaryTree <Integer, String> tree = new BinaryTree <Integer, String> ();
		
		tree.addNode(10, "node 10");
		tree.addNode(4, "node 4");
		tree.addNode(14, "node 14");
		tree.addNode(2, "node 2");
		tree.addNode(7, "node 7");
		tree.addNode(6, "node 6");
		tree.addNode(0, "node 0");
		tree.addNode(3, "node 3");
		tree.addNode(8, "node 8");
		tree.addNode(9, "node 9");
		tree.addNode(5, "node 5");
		tree.addNode(-1, "node -1");
		tree.addNode(1, "node 1");
		tree.addNode(12, "node 12");
		tree.addNode(13, "node 13");
		tree.addNode(11, "node 11"); 
		tree.addNode(17, "node 17");
		tree.addNode(16, "node 16");
		tree.addNode(15, "node 15");
		tree.addNode(18, "node 18");
		tree.addNode(19, "node 19");
		
		if(tree.addNode(2, "Duplicate Node 2"))
		{
			System.out.println("Duplicate Node 2 was added");
		}
		else
		{
			System.out.println("Duplicate Node 2 was not added");
		}
		
		System.out.println("\nNode depth for node 10:  "+tree.getNodeDepth(10));
		System.out.println("Node depth for node 0:  "+tree.getNodeDepth(0));
		System.out.println("Node height for node 10:  "+tree.getNodeHeight(10));
		System.out.println("Node height for node 0:  "+tree.getNodeHeight(0));

		
		List <Node<Integer, String>> list = tree.getInsertOrder();
		System.out.println("\n\ninsertOrder(" +tree.size()+")");
		System.out.println("-------------------");
		for(Node<Integer, String> node : list)
		{
			System.out.println(node.toString());
		}
		
		
		list = tree.preOrderTransversal();
		
		System.out.println("\n\npreOrderTransversal(" +tree.size()+")");
		System.out.println("-------------------");
		for(Node<Integer, String> node : list)
		{
			System.out.println(node.toString());
		}
		
		
		list = tree.inOrderTransversal();
		System.out.println("\n\ninOrderTransversal(" +tree.size()+")");
		System.out.println("------------------");
		for(Node<Integer, String> node : list)
		{
			System.out.println(node.toString());
		}
		
		
		list = tree.postOrderTransversal();
		System.out.println("\n\npostOrderTransversal(" +tree.size()+")");
		System.out.println("--------------------");
		for(Node<Integer, String> node : list)
		{
			System.out.println(node.toString());
		}
		
		
		if(tree.removeNode(18))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 18 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		
		if(tree.removeNode(6))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 6 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		
		if(tree.removeNode(12))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 12 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}

		
		if(tree.removeNode(14))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 14 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		
		if(tree.removeNode(15))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 15 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}

		
		
		if(tree.removeNode(10))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 10 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		if(tree.removeNode(4))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 4 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}

		
		if(tree.removeNode(11))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 11 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		if(tree.removeNode(16))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 16 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		if(tree.removeNode(17))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 17 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		if(tree.removeNode(19))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 19 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
		
		if(tree.removeNode(13))
		{
			list = tree.inOrderTransversal();
			System.out.println("\n\ninOrderTransversal after removed 13 (" +tree.size()+")");
			System.out.println("------------------");
			for(Node<Integer, String> node : list)
			{
				System.out.println(node.toString());
			}
		}
	}
	
}
