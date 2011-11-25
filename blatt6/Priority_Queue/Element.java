package Priority_Queue;

class Element 
extends AElement
{
	private int priority;
	private String data;
	
	public Element(int priority, String data) {
		this.priority=priority;
		this.data=data;
	}
	
	public String getData() {
		return this.data;
	}
	public int getPriority() {
		return this.priority;
	}
	
/*	//same as getData?
	public String toString() {
		return "Priority="+this.priority+" Inhalt="+this.data;
	}*/
}
