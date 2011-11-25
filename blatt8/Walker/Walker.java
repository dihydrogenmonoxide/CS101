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
		
		HWalk w3 = new HWalk(1000,900);
		w3.walk(7,600);
		w3.show();
	}

}
