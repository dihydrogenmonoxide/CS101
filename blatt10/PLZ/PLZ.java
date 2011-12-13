package PLZ;

import java.io.*;
import java.util.*;


public class PLZ 
{
	protected static final boolean verbose = true;
	protected List<cAddy> PLZlist;
	
	public PLZ(){}
	
 	public boolean parse(String filename)
	{
		try
		{
			//creating a handle with 'r' rights to access the file
			FileInputStream fstream = new FileInputStream(filename);
			//creating a buffered reader to read line by line
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			List<cAddy> pp = new ArrayList<cAddy>();
			//temporary string that always holds one line of the text file
			String s;
			String l[];
			//read a line and s<--line
			try{
				while((s=br.readLine())!= null)
				{
					l = s.split("\t");
					if(l.length == 7)
					{
						try
						{
							pp.add(new cAddy(
									//<interpreting the text input>
									Integer.parseInt(l[2]),//PLZ
									l[4],//village name
									l[6],//kanton
									Integer.parseInt(l[0]),//dunno what, maybe some internal number the post uses
									Integer.parseInt(l[1]),//no clue what this number stands for
									Integer.parseInt(l[3])//looks like an iterator of the different village names
									//</interpreting the text input>
									));
						}
						catch(NumberFormatException e)
						{
							if (verbose)//the show must go on
								System.out.println("Error: converting some numbers in \'"+s+"\' failed!");
						}
							
					}
					else
					{
						if(verbose && !s.equals(""))//printing some debug information (if wanted)
							System.out.println("Error: Splitting \'"+s+"\' by tabs returns an invalid result!" );
					}
				}
				//unregging the access handle
				fstream.close();
				Collections.sort(pp);
				PLZlist = pp;
				return true;
			}
			catch(IOException e)
			{
				if(verbose)//fails reading it... so nothing's returned
					System.out.println("Error: can't access \'"+filename+"\'!");
				return false;
			}
			
			//sorting the list for binsearch
		}
		catch(FileNotFoundException e)
		{
			//please specify a valid file
			if(verbose)//fails reading it... so nothing's returned
				System.out.println("Error: File \'"+filename+"\' not found!");
			return false;			
		}
		
		
		
	}

	public cAddy HandleInput(String Input)
	{
		if (PLZlist == null)
		{
			if(verbose)
				System.out.println("Please parse a file first!");
			return null;
		}
		
		int index;
		
		try
		{
			//searching for the PLZ
			index = Collections.binarySearch(PLZlist,new cAddy(Integer.parseInt(Input),"","",0,0,0));
		}
		catch(NumberFormatException e)
		{
			if(verbose)
				System.out.println("Seems your input wasn't a Number and thus no PLZ");
			return null;
		}
		if(index == 0)
		{
			if(verbose)
				System.out.println("Couldn't find the PLZ "+Input+" in switzerland");
			return null;
		}
		return PLZlist.get(index);				
	}
	
	
}
