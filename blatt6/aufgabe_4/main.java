package aufgabe_4;


class PriorityShizzle
{
	static final int sz = 20000; // test size
	public static void main(String argv[])
	{
		
		//create random test data
		int q[] = new int[sz/2];
		java.util.Random r = new java.util.Random();
		
		for(int i = 0; i < sz/2; i++)
		{
			q[i]=r.nextInt();
		}
		
		
		// First try with our Priority Queue
		long ticks = System.currentTimeMillis();
		PriorityQueue p = new PriorityQueue();
		for(int i = 0; i < sz; i++)
		{
			if(i < sz/2) 
				p.put(new CElement(i,"same content everywhere"));
			else
				p.get();						
		}
		System.out.println("time with own class(adding everythign first, then removing everything): "+(System.currentTimeMillis()-ticks)+"MS");
		
		
		// Second Try with java Class
		ticks = System.currentTimeMillis(); //reset time
		java.util.PriorityQueue<CElement> pp = new java.util.PriorityQueue<CElement>(sz/2);
		
		for(int i = 0; i < sz; i++)
		{
			if(i < sz/2) 
				pp.add(new CElement(i,"same content everywhere"));
			else
				pp.remove();						
		}
		System.out.println("time with java class(adding everythign first, then removing everything): "+(System.currentTimeMillis()-ticks)+"MS");
	}
}