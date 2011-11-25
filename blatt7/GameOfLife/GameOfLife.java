package GameOfLife;

public class GameOfLife 
{
	private int sz=6;
	private boolean buff[][];
	private boolean equOld = false;
	
	public GameOfLife()
	{
		buff= new boolean[sz][sz];
		for(int i = 0; i != sz;i++)
		{
			for(int ii=0;ii!=sz;ii++)
			{
				buff[i][ii]=false;
			}
		}
		buff[1][1]=true;
		buff[1][3]=true;
		buff[2][2]=true;
		buff[2][3]=true;
		buff[3][2]=true;
	}
	
	public GameOfLife(int size)
	{
		sz=size;
		buff= new boolean[sz][sz];
		java.util.Random r = new java.util.Random();
		for(int i = 0; i != sz;i++)
		{
			for(int ii=0;ii!=sz;ii++)
			{
				//"random" number between 0 and 100
				if(r.nextInt()%101 <= 30)
					buff[i][ii]=true;
				else
					buff[i][ii]=false;
			}
		}		
	}
	
	public boolean[][] GetAll()
	{
		return buff;
	}
	
	public boolean Get(int szx,int szy)
	{
		szx=(szx+1)%sz;
		szy=(szy+1)%sz;
		if(szx >= 0 && szx < sz && szy>=0 && szy < sz)
			return buff[szx][szy];
		else
			return false;
	}
	
	public int neighbourCount(int szx, int szy)
	{
		int retval = 0;
		for (int i = szx-1;i != szx+2;i++)
		{
			for(int ii = szy-1;ii!=szy+2;ii++)
			{
				//doesn't matter if index out of bounds; that's checked by get
				if(Get(i,ii)) retval++;
			}
		}
		//substract 1 if the field itself is a cell
		if(Get(szx,szy))
			return --retval;
		else
			return retval;
	}
	
	public String toString()
	{
		String retval = "";
		for(int i = 0; i !=sz; i++)	
		{
			for(int ii = 0; ii != sz; ii++)
			{
				if(Get(i,ii))
					retval += "@ ";
				else
					retval += ". ";
			}
			retval += "\n";
		}
		return retval;
	}
	
	//next step in the evolution
	public void NextStep()
	{
		boolean[][] n = new boolean[sz][sz];
		for(int i = 0; i !=sz;i++)
		{
			for(int ii = 0; ii != sz; ii++)
			{
				if(neighbourCount(i,ii) == 3)
						n[i][ii]=true;
					else if(neighbourCount(i,ii) == 2 && Get(i,ii) == true)
						n[i][ii]=true;
					else
						n[i][ii]=false;
			}
		}
		equOld=comp(n);
		buff = n;
	}
	
	public void NextStepRand()
	{
		boolean[][] n = new boolean[sz][sz];
		java.util.Random r = new java.util.Random();
		for(int i = 0; i !=sz;i++)
		{
			for(int ii = 0; ii != sz; ii++)
			{
				if(r.nextInt()%101 <= 80)
				{
					if(neighbourCount(i,ii) == 3)
							n[i][ii]=true;
						else if(neighbourCount(i,ii) == 2 && Get(i,ii) == true)
							n[i][ii]=true;
						else
							n[i][ii]=false;
				}
				else
				{
					n[i][ii]=Get(i,ii);
				}
			}
		}
		equOld=comp(n);
		buff = n;
	}
	
	private boolean comp(boolean[][] n)
	{
		boolean retval = true;
		for(int i = 0; i != sz; i++)
		{
			for(int ii = 0; ii != sz; ii++)
			{
				retval=n[i][ii]==Get(i,ii)&&retval;
			}
		}
		return retval;
	}
	public boolean notChanging()
	{
		return equOld;
	}
	
}
