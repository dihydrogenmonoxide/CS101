package Walker;

import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class KochWalk
{
	private ImageWindow iw;
	private int szx = 800;
	private int szy = 600;
	private Walker w;
	public KochWalk(int szx, int szy)
	{
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.setPos(0, szy/3*2);
		w.pressBallPen();
	}
	public KochWalk()
	{
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.setPos(0, szy/3*2);
		w.pressBallPen();
	}
	public void walk(int numwalks, float len)
	{
		if(numwalks < 1)
			{
				w.move(len);
				return;
			}
		for(int i = 0; i!= 4;i++)
		{
			switch(i)
			{
			case 0:
				walk(numwalks-1,len/3);
				break;
			case 3:
			case 1:
				w.turn(-60);
				walk(numwalks-1,len/3);
				break;
			case 2:
				w.turn(120);
				walk(numwalks-1,len/3);
				break;
			default:
				break;
				
			}
		}
	}
	public void show()
	{
		iw.openWindow("kochwalk",0,600);
	}
	public void resetpos()
	{
		w.setPos(0, szy-2);
		w.setDir(1,0);
	}
	
	public void DrawSnowflake(int sidelen, int count)
	{
		w.turn(-60);
		walk(sidelen,count);
		w.turn(120);
		walk(sidelen,count);
		w.turn(120);
		walk(sidelen,count);
	}

}
