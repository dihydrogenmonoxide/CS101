package SearchTree;

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
			
			//left branch
			res += left!=null ? "("+key+" "+left.toString()+" ":"";
			//separator
			res+= " | ";
			//right branch
			res += right!=null ? " "+right.toString()+" "+key+")":"";
			
			return res;
		}
}
