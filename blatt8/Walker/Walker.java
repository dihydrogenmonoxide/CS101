package Walker;

public class Walker
{
	public static void main(String[] args)
	{
		RandomWalk w = new RandomWalk();
		w.walk();
		
		KochWalk w2 = new KochWalk();
		w2.DrawSnowflake(3, 300);
		w2.resetpos();
		w2.walk(3, 800);
		w2.show();
		
		HWalk w3 = new HWalk();
		w3.walk(5,500);
		w3.show();
		
		HilbertWalk w4 = new HilbertWalk();
		w4.walk(1, 500);
		w4.show(); 
	/*	HilbertWalk w5 = new HilbertWalk();
		w5.walk(2, 500);
		w5.show(); 
		HilbertWalk w6 = new HilbertWalk();
		w6.walk(3, 500);
		w6.show(); 
		HilbertWalk w7 = new HilbertWalk();
		w7.walk(4, 500);
		w7.show(); 
		HilbertWalk w8 = new HilbertWalk();
		w8.walk(5, 500);
		w8.show(); 
		HilbertWalk w9 = new HilbertWalk();
		w9.walk(6, 500);
		w9.show(); */
	}

}
