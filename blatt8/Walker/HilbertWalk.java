package Walker;

import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class HilbertWalk 
{
	private enum instruction
	{
		rotateLeft,
		rotateRight,
		doit,
		doitReversed,
		walk;		
	}
	private static instruction inst[]={
		instruction.rotateRight,
		instruction.doitReversed,
		instruction.walk,
		instruction.rotateLeft,
		instruction.doit,
		instruction.walk,
		instruction.doit,
		instruction.rotateLeft,
		instruction.walk,
		instruction.doitReversed,
		instruction.rotateRight
		}; 
	
	
	
	private Walker w;
	private ImageWindow iw;
	private static final int spacing = 5;
	private double len;
	
	public HilbertWalk()
	{
		//nothing to do here
	}
	
	public void walk(int step, int length)
	{
		iw = new ImageWindow(length+2*spacing, length+2*spacing);
		w= new Walker(iw);
		w.pressBallPen();
		w.setPos(spacing, length-spacing);
		w.setDir(0, -1);
		this.len=length/Math.pow(step, 1.95f);
		swalk(step,false);
	}
	
	private void turn(double angle, boolean counterclockwise)
	{
		if(counterclockwise)
			w.turn(angle);
		else
			w.turn(-angle);
	}
	
	public void show()
	{
		iw.openWindow("lalalal");
	}
	
	
	private void swalk(int num, boolean reversed)
	{
		if(num < 1) return;
		for(int i = 0; i != inst.length;i++)
		{
			switch(inst[i])
			{
				case rotateLeft:
					turn(90,reversed);					
					break;
				case rotateRight:
					turn(-90,reversed);
					break;
				case walk:
					w.move(this.len)	;				
					break;
				case doit:
					swalk(num-1,reversed);
					break;
				case doitReversed:
					swalk(num-1,!reversed);
					break;
			}
		}
	}

}

