package Walker;
import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class RandomWalk 
{
	private ImageWindow iw;
	private int count = 1000;
	private int szx = 800;
	private int szy = 600;
	private Walker w;
	public RandomWalk(int szx, int szy)
	{
		this.szx = szx;
		this.szy = szy;
	}
	public RandomWalk()
	{
	}
	public RandomWalk(int szx, int szy, int RandomWalkAmount)
	{
		count = RandomWalkAmount;
		this.szx = szx;
		this.szy = szy;
	}
	
	public void walk()
	{
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.pressBallPen();
		java.util.Random r = new java.util.Random();
		for(int i = 0; i != count; i++)
		{
			w.turn((r.nextInt()%3600-1800.0f)/10.0f);
			w.move(r.nextInt()%50);
		}
		iw.openWindow("blah");
	}

}
