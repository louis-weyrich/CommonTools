package com.sos.tools.test.utilities.trie;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.trie.TrieNode;
import com.sos.tools.utilities.trie.TrieTree;

public class TestTrieTree {

	private static TrieTree tree;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tree = new TrieTree(3);
		
		LineNumberReader lineReader = new LineNumberReader(new FileReader(new File("./words/words.txt")));
		String s;
		
		while((s = lineReader.readLine()) != null)
		{
			tree.addTerm(s);
		}
		
		lineReader.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		tree.clear();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public final void testAddTerm() {
		tree.addTerm("Str@nge2");
		assertTrue(tree.containsWord("Str@nge2"));
	}

	@Test
	public final void testMatchWords() {
		List <String> list = tree.matchWords("Louis");
		assertTrue(list.size() == 9);
		
		tree.setMinimumLength(0);
		
		list = tree.matchWords("L");
		assertTrue(list.size() == 9303);
	}

	@Test
	public final void testContainsWord() {
		assertTrue(tree.containsWord("Louis"));
	}

	@Test
	public final void testGetNode_L() {
		TrieNode node = tree.getNode("L");
		List <String> list = tree.matchWords("L");
		System.out.println(node.getCharacter()+" ("+node.getWord()+") "+node.getWordCount()+" = "+node.getChildren().size()+" = "+list.size());
		}
	
	@Test
	public final void testGetNode_Louis() {
	
		TrieNode node = tree.getNode("Louis");
		List <String> list = tree.matchWords("Louis");
		System.out.println(node.getCharacter()+" ("+node.getWord()+") "+node.getWordCount()+" = "+node.getChildren().size()+" = "+list.size());
	}
	
	
	@Test
	public final void testGetNode_root() {
		
		TrieNode node = tree.getNode("");
		List <String> list = tree.matchWords("");
		System.out.println(node.getCharacter()+" ("+node.getWord()+") "+node.getWordCount()+" : "+node.getChildren().size()+" = "+list.size());
	}
}
