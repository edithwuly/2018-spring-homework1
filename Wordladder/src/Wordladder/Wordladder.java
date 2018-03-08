package Wordladder;
import java.io.*;
import java.util.*;

public class Wordladder
{
	static char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static Queue <String> infected = new LinkedList<String>();
	static HashMap<String, String> route = new HashMap<String, String>();
	static boolean done = false;
	static String out="";
	static HashSet<String> dict = new HashSet<String>();
	public static void main(String[] args)
	{	
		System.out.print("Dictionary file name? ");
		Scanner in = new Scanner(System.in);	
		String file = in.nextLine();
		String w1;
		String w2;
		
		while (true)
		{		
			getDict(file);
			
			System.out.print("Word #1 (or Enter to quit): ");
			w1=in.nextLine();
			w1.toLowerCase();
			
			if (!testSingleWord(w1)) 
				continue;
			
			System.out.print("Word #2 (or Enter to quit): ");
			w2=in.nextLine();		
			w2.toLowerCase();
			
			if (!testSingleWord(w2)) 
				continue;
			
			if (!testBothWords(w1,w2)) return;
			
			infected.offer(w1);
			dict.remove(w1);							
			while (!done) 
			{
				go(w1, w2);
			}
			System.out.print(out);							

			dict.clear();
			route.clear();
			infected.clear();
			done=false;
			
			System.out.println();
		}
	}
	public static boolean testSingleWord(String w)
	{
		if (w.equals("")) 
		{
			System.out.print("Have a nice day.");
			System.exit(0);
		}
		if (!dict.contains(w))
		{
			System.out.print("The input word must be a proper word.\n");
			return false;
		}  
		return true;
	}
	
	public static boolean testBothWords(String w1,String w2)
	{
		if (w1.equals(w2))
		{
			System.out.print("The two words must be different.");
			return false;
		}
		if (w1.length()!=w2.length())
		{
			System.out.print("The two words must have the same length.");
			return false;
		}
		return true;
	}
	
	public static void getDict(String file)
	{
		File filename = new File(file);
		String readin="";
		try
		{
			Reader reader = new InputStreamReader(new FileInputStream(filename));
			int charread = 0;
			while ((charread = reader.read()) != -1 ) 
			{
				if (charread != '\n')
					readin+=(char)charread;
				else
				{
					dict.add(readin);
					readin="";
				}
			}
			reader.close();
		}
		catch(IOException e)
		{
			System.out.print("Can't open the file. Have a  nice day.");
			System.exit(0);
		}
	}
	public static void go(String w1, String w2) 
	{
		Queue<String> newinfected = new LinkedList<String>();
		String first;
		String create;
		String temp;
		
		while (!infected.isEmpty()) 
		{
			first = infected.peek();
			for (int i = 0; i < first.length(); i++) 
			{
				for (char cc : alpha) 
				{
					temp = first;
					first = first.substring(0, i) + cc +first.substring(i+1);
					create = first;
					first = temp;
					if (create.equals(w2)) 
					{
						done = true;
						String now = first;
						out = w2;
						out += " ";
						out += first;
						while (now != w1) 
						{
							out += " ";
							now = route.get(now);
							out += now;
						}
						System.out.print("A ladder from " +w2+" back to "+w1+":\n");
						return;
					}
					if (dict.contains(create)) 
					{
						if (!route.containsKey(create)) 
							route.put(create, first);
						newinfected.offer(create);
						dict.remove(create);
					}
				}
			}
			infected.poll();
		}
		if (newinfected.isEmpty()) 
		{
			done = true;
			out = "No word ladder found from " + w2 + " back to " + w1 + ".";
			return;
		}
		infected = newinfected;
		return;
	}
}