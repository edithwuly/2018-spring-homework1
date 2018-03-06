import java.io.*;
import java.util.*;

public class Wordladder
{
	public static void main(String[] args)throws IOException
	{	
		Scanner in = new Scanner(System.in);
		File fin = new File(in.nextLine());
		System.out.print("Dictionary file name? ");
		
		FileReader dictionary = new FileReader(fin);
		BufferedReader word=new BufferedReader(dictionary);
		System.out.print(word);
	}
}