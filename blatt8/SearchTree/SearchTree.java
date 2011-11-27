package SearchTree;

import ch.unibas.informatik.cs101.BufferedImageWindow;

public class SearchTree {
		Node top;
		int counter;
		
		/**Adds a key,value pair to the Searchtree*/
		public void add(int key, String value){
			//increase counter
			counter++;
			
			//on the first insert create a leaf
			if(top==null){
				top=new Leaf(key,value);
				return;
			}
			
			//recursive insert function
			top=insert(key,value,top);
		}
		 /**inserts a key,value pair in a determined node recursively*/
		private Node insert(int key, String value, Node node){
			
			/*if we are at the ground of the tree e.g. a Leaf
			 * so we have to decide either to 
			 * insert the new Leaf on the right side
			 * or on the left side
			 */
			if(node instanceof Leaf){
				if(node.key<key){
					return new InnerNode(node.key,node,new Leaf(key,value)); //right side
					
				}else{
					return new InnerNode(key,new Leaf(key,value),node); //left side
				}
			}
			
			//now that we are sure we have a inner Node, we can cast it
			InnerNode iNode=(InnerNode)node;
			
			/*Because it's a inner Node, the value has to be inserted in the right or in the left Child*/
			if(iNode.key<key)
			{
				return new InnerNode(iNode.key,iNode.left,insert(key,value,iNode.right));//right
			}else{
				return new InnerNode(iNode.key,insert(key,value,iNode.left),iNode.right);//left
			}
			
		}
		
		/**searches a key in the Searchtree*/
		public String search(int key){
			if(top==null){return "tree is empty";}
			
			//search recursively
			return rec_search(key,top);
		}
		
		/**Searches a given key in a determined node of the tree recursively.*/
		private String rec_search(int key,Node node){
			//if it's a leaf compare keys
			if(node instanceof Leaf){
				if(node.key==key){
					return "Found: "+node.toString();
				}else
					return "value not found";
			}
			
			//now it has to be a InnerNode
			InnerNode iNode=(InnerNode)(node);
			
			//the wanted key has to be in the right or the left child
			if(iNode.key<key)
			{
				return rec_search(key,iNode.right); //right one
			}else{
				return rec_search(key,iNode.left); //left one
			}
		}
		
		
		public String toString(){
			if(top==null){return "tree is empty";}
			
			return top.toString();
		}
		
		public void drawTree(){
			
			//init 
			int step_height=50;//determines the decrease in height per node
			int width=counter*155;
			int height=counter*step_height;
			
			BufferedImageWindow	w= new BufferedImageWindow(width,height);
			w.setColor(0,0,0);
			
			//draw it recursively
			top.draw(w, 0,step_height, 0, width,0);
			
			w.redraw();
			w.openWindow("SearchTree",0,0);
		}
		
}
