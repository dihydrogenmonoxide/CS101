package OLI_GOL;

public class GameOfLife implements Runnable
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
		randSet();
			
	}
	public void randSet(){
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
	
	public void reset(){
		buff=new boolean[sz][sz];
	}
	
	public boolean Get(int szx,int szy)
	{
		return buff[(szx+sz)%sz][(szy+sz)%sz];
	}
	
	public boolean getCell(int x, int y){
		if(x<sz && y<sz)
		{
			return buff[x][y];
		}
		return false;
	}
	public void setCell(int x, int y, boolean b){
		if(x<sz && y<sz)
		{
			buff[x][y]=b;
		}
	}
	
	public synchronized int neighbourCount(int szx, int szy)
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
	public synchronized void NextStep()
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.NextStep();
	}
	
}
