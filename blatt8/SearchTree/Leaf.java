package SearchTree;

import ch.unibas.informatik.cs101.BufferedImageWindow;

public class Leaf extends Node{
	String value;
	Leaf(int _key, String _value){
		this.key=_key;
		this.value=_value;
	}
	public String toString(){
		return "{"+key+" "+value+"}";
	}
	
	public void draw(BufferedImageWindow w,int depth,int step_height,int x_min,int x_max, int ancestor){
		//midle of the drawing sector
		int middle=(x_min+x_max)/2;
		
		//draw lines to ancestors if needed:
		/*About status:
		 * 0	this node is at the top, draw no lines
		 * 1	ancestor will be to the right of this node
		 * 2	ancestor will be to the left  of this node
		 * */
		switch(ancestor){
			case 1:
				w.drawLine(x_max,(depth-1)*step_height+40,middle,depth*step_height+10);
				break;
			case 2:
				w.drawLine(x_min,(depth-1)*step_height+40,middle,depth*step_height+10);
				break;
			}
		
		//draw key and value
		w.drawString(this.toString(), middle , depth*step_height+20);
		
	}
}
