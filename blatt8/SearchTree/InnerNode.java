package SearchTree;

import ch.unibas.informatik.cs101.BufferedImageWindow;

public class InnerNode extends Node{
		Node left;
		Node right;
		
		InnerNode(int _key,Node _left,Node _right){
			this.key=_key;
			this.left=_left;
			this.right=_right;
		}
		
		public String toString(){
			String res="";
			
			/* we don't need any checking because if it is an innernode it has to have two childrens,
			 * otherwise it wouldn't be one
			*/
			
			//left branch
			res += "("+key+" "+left.toString()+" ";
			//separator
			res+= " | ";
			//right branch
			res += " "+right.toString()+" "+key+")";
			
			return res;
		}
		
		public void draw(BufferedImageWindow w,int depth,int step_height,int x_min,int x_max, int ancestor){
			
			//the middle of the drawing sector and decrease fontsize
			int middle=(x_min+x_max)/2;
			if(depth<4)w.setFontSize(15-depth*2);
			
			
			//draw lines to ancestors if needed:
			/*About status:
			 * 0	this node is at the top, draw no lines
			 * 1	ancestor will be to the right of this node
			 * 2	ancestor will be to the left  of this node
			 * */
			switch(ancestor){
				case 1:
					w.drawLine(x_max,(depth-1)*step_height+40,middle,depth*step_height+40);
					break;
				case 2:
					w.drawLine(x_min,(depth-1)*step_height+40,middle,depth*step_height+40);
					break;
			}
			
			//draw key
			w.drawString("["+key+"]", middle-19 , depth*step_height+step_height/2);
			
			//draw Child nodes
			left.draw(w, depth+1,step_height, x_min, middle,1);
			right.draw(w, depth+1,step_height, middle, x_max,2);
		}
		
}
