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
		w3.walk(9,500);
		w3.show();
		
		HilbertWalk w4 = new HilbertWalk();
		w4.walk(3, 500);
		w4.show(); 
	}

}
