package Walker;

import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class HWalk
{
	private ImageWindow iw;
	private int szx = 800;
	private int szy = 600;
	private Walker w;
	public HWalk()
	{
		//not much todo here
	}
	public void walk(int numwalks, int len)
	{
		double pos = 0;
		for(int i = 0; i != numwalks;i++)
		{
			pos += len/Math.pow(2,i);
		}
		this.szx = (int) pos + 16;
		this.szy = this.szx;
		iw = new ImageWindow(szx, szy);
		w= new Walker(iw);
		w.pressBallPen();
		swalk(numwalks,this.szy/2-len/2,this.szy/2-len/2,len);
		
	}
	private void swalk(float n,float x, float y, float sz)
	{
		if(n < 1) 
		{
			return;
		}
		//tada, added funny colors @ fox918
		w.setColor((int)(n*1337)%256,(int)(n*1234)%256,(int)(n*4321)%256);
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
		iw.openWindow("HWalk",800,0);
	}
}
