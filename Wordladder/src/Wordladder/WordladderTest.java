package Wordladder;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class WordladderTest {
	static char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static Queue <String> infected = new LinkedList<String>();
	static HashSet<String> dict = new HashSet<String>();
	
	
	@Before
	public void prepare()
	{
		Wordladder.getDict("dictionary.txt");
	}
	
	@Test
	public void testTestSingleWord()
	{
		assertEquals(false,Wordladder.testSingleWord("123"));
		assertEquals(true,Wordladder.testSingleWord("dog"));
	}
	
	@Test
	public void testTestBothWords()
	{
		assertEquals(false,Wordladder.testBothWords("code","code"));
		assertEquals(false,Wordladder.testBothWords("dog","code"));
		assertEquals(true,Wordladder.testBothWords("code","data"));
	}

	@Test
	public void testBody()
	{	
		assertEquals("A ladder from data back to code:\ndata date cate cade code",Wordladder.body("code","data"));
		Wordladder.getDict("dictionary.txt");
		assertEquals("A ladder from code back to data:\ncode cote cate date data",Wordladder.body("data","code"));
		Wordladder.getDict("dictionary.txt");
		assertEquals("A ladder from dog back to cat:\ndog dot cot cat",Wordladder.body("cat","dog"));
	}
}
