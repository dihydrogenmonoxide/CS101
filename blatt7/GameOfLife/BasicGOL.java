package GameOfLife;

public class BasicGOL 
{
	private static final long sleeptimer = 500;
	public static void main(String[] args) 
	{
		GameOfLife g = new GameOfLife(10);
	/*	boolean b[][] =g.GetAll();
		b[0][0]=false;
		b[0][1]=true;
		b[0][2]=false;
		b[1][0]=false;
		b[1][1]=true;
		b[1][2]=false;
		b[2][0]=false;
		b[2][1]=true;
		b[2][2]=false;//*/
		while(true)
		{
			System.out.println(g.toString());
			g.NextStep();
			if(g.notChanging()==true)break;
			try
			{
				Thread.sleep(sleeptimer);
			}
			catch(InterruptedException e)
			{
				//zeh fuck - as if I'd care
				//but honestly... what can go wrong with a sleep timer?
			}
		}
		System.out.println("It's not changing anymore, thus I stopped computing it");
	}

}
