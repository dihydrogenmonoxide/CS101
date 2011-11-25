package Priority_Queue;

//2)b)
public abstract class AElement
implements IElement
{
	public String toString()
	{
			return "Priority="+this.getPriority()+" Inhalt="+this.getData();
	}
	
}
