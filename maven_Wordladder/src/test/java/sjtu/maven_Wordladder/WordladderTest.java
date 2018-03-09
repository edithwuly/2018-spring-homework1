package sjtu.maven_Wordladder;

import junit.framework.TestCase;
import junit.framework.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;



public class WordladderTest extends TestCase{
	static char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static Queue <String> infected = new LinkedList<String>();
	static HashSet<String> dict = new HashSet<String>();
	
	
	public void testTestSingleWord()
	{
		Wordladder.getDict("dictionary.txt");
		assertEquals(false,Wordladder.testSingleWord("123"));
		assertEquals(true,Wordladder.testSingleWord("dog"));
	}
	
	public void testTestBothWords()
	{
		assertEquals(false,Wordladder.testBothWords("code","code"));
		assertEquals(false,Wordladder.testBothWords("dog","code"));
		assertEquals(true,Wordladder.testBothWords("code","data"));
	}

	public void testBody()
	{	
		Wordladder.getDict("dictionary.txt");
		assertEquals("A ladder from data back to code:\ndata date cate cade code",Wordladder.body("code","data"));
		Wordladder.getDict("dictionary.txt");
		assertEquals("A ladder from code back to data:\ncode cote cate date data",Wordladder.body("data","code"));
		Wordladder.getDict("dictionary.txt");
		assertEquals("A ladder from dog back to cat:\ndog dot cot cat",Wordladder.body("cat","dog"));
	}
}
