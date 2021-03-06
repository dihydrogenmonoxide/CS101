package aufgabe_4;

public class PriorityQueue {
	static final int SIZE = PriorityShizzle.sz;
	CElement[] q = new CElement[SIZE];
	int len = 0;
	
	// Insert an element into the queue according to its priority
	public void put(CElement x) 
	{
		if(len == SIZE)
		{
			System.out.println("can't add an element becuse the boat is full - Data:"+x.toString());
			return;
		}

		if(len == 0)
		{
			q[0]=x;
			len++;
			return;
		}
		boolean done = false;
		for(int i = 0;i < len; i++)
		{
			if(q[i].getPriority() >= x.getPriority())
			{
				//moving all higher priorities one up to make space for the new element (could be better solved using dynamic list, but w/e)
				for(int ii = len; ii != (i-1) ;ii--)
				{
					q[ii+1]=q[ii];
				}
				//since whe have space now, we can insert eh new element
				q[i]=x;	
				done = true;
				break;
			}
		}
		if(!done)q[len]=x;
		//as we added an emelent, we gotta increment the length
		len++;		
	}
	
	// Return the element with the highest priority from the queue
	public CElement get()
	{
		if(len <= 0)
		{
			System.out.println("cant take out more than you give into it");
			return (CElement)null;
		}
		//decrement the length
		len--;
		//since the length starts at 1 we would have to substract one, but I decremented it first -> solved
		return q[len];
	}
	
	// Return the queue length
	public int length()
	{
		
		//too complex, no clue what I am doing here
		return len;		
	}
	
	// Print the queue contents
	public String toString() 
	{
		if(len == 0)
		{
			return "";
		}
		//initializing a new string and making sure it's empty
		String str_retVal= "";
		//adding all elements to that string
		for(int i = 0; i != len; i++)
		{
			str_retVal += q[i].toString()+"\n";
		}
		return str_retVal;		
	}
}
