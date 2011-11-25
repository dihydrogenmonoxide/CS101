package Priority_Queue;

public class FElement
extends AElement
{
	private int priority;
	private float data;
	
	public FElement(int priority, float data)
	{
		this.priority=priority;
		this.data=data;
	}
	
	public String getData()
	{
		return ""+this.data;
	}
	public int getPriority() 
	{
		return this.priority;
	}
}
