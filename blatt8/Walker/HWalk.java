package Walker;

import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class HWalk 
{
	private ImageWindow iw;
	private int szx = 800;
	private int szy = 600;
	private Walker w;
	public HWalk(int szx, int szy)
	{
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.pressBallPen();
	}
	public HWalk()
	{
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.pressBallPen();
	}
	public void walk(int numwalks, int len)
	{
		swalk(numwalks,(int)szx/2-len/2,(int)szy/2-len/2,len);
		
	}
	private void swalk(float n,float x, float y, float sz)
	{
		if(n < 1) 
		{
			return;
		}
		//just a test
		w.setPos(x, y);
		w.setDir(0,1);
		w.move(sz);
		w.setPos(x+sz,y);
		w.move(sz);
		w.setPos(x, y+sz/2);
		w.setDir(1, 0);
		w.move(sz);
		
		float s = sz/4;
		swalk(n-1,x-s,y-s,2*s);
		swalk(n-1,x+sz-s,y-s,2*s);
		swalk(n-1,x+sz-s,y+sz-s,2*s);
		swalk(n-1,x-s,y+sz-s,2*s);//*/		
	}
	
	public void show()
	{
		iw.openWindow("HWalk");
	}
}
