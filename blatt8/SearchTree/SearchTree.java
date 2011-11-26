package SearchTree;

public class SearchTree {
		Node top;
		
		public void add(int key, String value){
			
			//on the first insert
			if(top==null){
				top=new Leaf(key,value);
			}
			
			
			
		}
		public void search(int key){
			
		}
}
