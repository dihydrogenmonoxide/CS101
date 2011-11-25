package Priority_Queue;

class PriorityShizzle
{
	public static void main(String argv[])
	{
		if(argv.length <2 || argv.length%2 != 0)
		{
			System.out.println("how2use:\n\"content\" \"Priority(can be any int value)\"");
			return;
		}
		
		PriorityQueue p = new PriorityQueue();
		for(int i = 0; i < argv.length;i+=2)
		{
			//reading out the vars from the console, making elements outta it & adding them to the priority queue
			try
			{
				p.put(new FElement(Integer.parseInt(argv[i+1]),Float.parseFloat(argv[i])));				
			}
			catch(NumberFormatException e)
			{
				p.put(new Element(Integer.parseInt(argv[i+1]),argv[i]));
			}
		}
		System.out.print(p.toString()+"\n\n");	
		
		float sum = 0;
		int notfloat = 0;
		IElement temp;
		for(int i = p.length()-1;i!=-1;i--)
		{
			temp=p.get();
			if(temp instanceof FElement) //prevents from 3.212323 in Element beeing treated as FElement
			{
				sum += Float.parseFloat(temp.getData());	
			}
			else
			{
				notfloat++;
			}
		}
		System.out.println("summe: "+sum+" with "+notfloat+" Entrys that were no float");
	}
}