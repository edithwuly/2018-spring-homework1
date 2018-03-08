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
	public static void main(String[] args)throws IOException
	{	
		System.out.print("Dictionary file name? ");
		Scanner in = new Scanner(System.in);
		File filename = new File(in.nextLine());	
		String readin="";
		String w1;
		String w2;
		System.out.print("Word #1 (or Enter to quit): ");
		while (true)
		{
			w1=in.nextLine();
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
			if (w1 == "") 
			{
				System.out.print("Have a nice day.");
				return;
			}
			System.out.print("Word #2 (or Enter to quit): ");
			w2=in.nextLine();
			w1.toLowerCase();
			w2.toLowerCase();
			if (w2 == "") 
				System.out.print("Have a nice day.");
			else if (w1 == w2) 
			{
				System.out.print("The two words must be different.");
			}
			else {
				infected.offer(w1);
				dict.remove(w1);							
				while (!done) 
				{
					go(w1, w2);
				}
				System.out.print(out);			
				dict.clear();
			}
			System.out.println();
			System.out.print("Word #1 (or Enter to quit): ");
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
						Wordladder.done = true;
						String now = first;
						Wordladder.out = w2;
						Wordladder.out += " ";
						Wordladder.out += first;
						while (now != w1) 
						{
							Wordladder.out += " ";
							now = route.get(now);
							Wordladder.out += now;
						}
						System.out.print("A ladder from " +w2+" back to "+w1+":\n");
						return;
					}
					if (Wordladder.dict.contains(create)) 
					{
						if (!Wordladder.route.containsKey(create)) 
							Wordladder.route.put(create, first);
						newinfected.offer(create);
						Wordladder.dict.remove(create);
					}
				}
			}
			Wordladder.infected.poll();
		}
		if (newinfected.isEmpty()) 
		{
			Wordladder.done = true;
			Wordladder.out = "No word ladder found from " + w2 + " back to " + w1 + ".";
			return;
		}
		Wordladder.infected = newinfected;
		return;
	}
}