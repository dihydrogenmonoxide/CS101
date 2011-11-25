package aufgabe_4;

public class CElement 
extends Element
implements Comparable<CElement>
{
	public CElement(int priority, String data)
	{
		super(priority, data);//calling the 'Element()' constructor
	}
	
	/**Needed Method for Interface Comparable. 
	 * This class sorts only by Priority, which should be sufficient for fifo 
	 * have fun with it*/
	public int compareTo(CElement e)
	{
		if(/*this.getData().compareTo(e.getData()) == 0 && */this.getPriority() == e.getPriority())
			return 0;
		else if(this.getPriority() > e.getPriority())
			return 1;
		else 
			return -1;
		
	}
}
