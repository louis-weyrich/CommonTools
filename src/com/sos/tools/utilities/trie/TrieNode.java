package com.sos.tools.utilities.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * 
 * @author louisweyrich
 *
 */
public class TrieNode 
{
	private TrieNode parent;
	private Map <Character, TrieNode> children;
	private Character character;
	private boolean wordEnd = false;
	private int wordCount = 0;

	/**
	 * 
	 * @param c
	 */
	public TrieNode(Character c) 
	{
		this(c, false);
	}

	/**
	 * 
	 * @param c
	 * @param wordEnd
	 */
	public TrieNode(Character c, boolean wordEnd) 
	{
		this.character = c;
		this.wordEnd = wordEnd;
	}

	/**
	 * 
	 * @return
	 */
	public TrieNode getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent
	 */
	public void setParent(TrieNode parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return
	 */
	public Map<Character, TrieNode> getChildren() 
	{
		if(this.children == null)
		{
			this.children = new HashMap <Character, TrieNode> ();
		}
		
		return children;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public TrieNode getChild(Character c)
	{
		return (this.children != null)? this.children.get(c) : null;
	}
	
	/**
	 * 
	 * @return
	 */
	public int childCount()
	{
		return (this.children != null)? this.children.size() : 0;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean containsChildNode(Character c)
	{
		return (this.children != null)? this.children.containsKey(c) : false;
	}
	
	/**
	 * 
	 * @param c
	 * @param child
	 */
	public void addChild(Character c, TrieNode child)
	{
		child.setParent(this);
		getChildren().put(c, child);
	}

	/**
	 * 
	 * @param children
	 */
	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}

	/**
	 * 
	 * @return
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isWordEnd() {
		return wordEnd;
	}

	/**
	 * 
	 * @param wordEnd
	 */
	public void setWordEnd(boolean wordEnd) {
		this.wordEnd = wordEnd;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRoot()
	{
		return (this.parent == null);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isLeaf()
	{
		return (this.children == null);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getWordCount()
	{
		return wordCount;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isWord()
	{
		return this.wordEnd;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getWord()
	{
		
		Stack <TrieNode> stack = new Stack <TrieNode> ();
		TrieNode parentNode = this;
		
		while(!parentNode.isRoot())
		{
			stack.push(parentNode);
			parentNode = parentNode.getParent();
		}
		
		StringBuilder builder = new StringBuilder(stack.size());
		
		while(!stack.isEmpty())
		{
			TrieNode node = stack.pop();
			builder.append(node.getCharacter());
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode()
	{
		return getWord().hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object src)
	{
		if(src instanceof TrieNode)
		{
			TrieNode node = (TrieNode)src;
			return (getWord().equals(node.getWord()));
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public void clear()
	{
		if(this.children != null)
		{
			for(TrieNode node : this.children.values())
			{
				node.clear();
			}
			
			this.children.clear();
		}
	}

	/**
	 * 
	 */
	public void incrementCount()
	{
		this.wordCount++;
	}
}
