package PLZ;

import java.util.*;

public class jmain
{
	public static void main(String[] args) 
	{
		PLZ q = new PLZ();
		if(!q.parse("blatt10/plz/plz.txt")) System.exit(-1);
		while(true)
		{
			System.out.println("Enter a plz:");
			String s = (new Scanner(System.in)).nextLine();
			if(s.toLowerCase().equals("q")) break;
			cAddy[] a = q.HandleInput(s);
			if(a != null && a.length > 0) System.out.println(q.makeString(a));
		}

	}
}
