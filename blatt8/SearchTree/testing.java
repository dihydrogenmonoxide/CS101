package SearchTree;

public class testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchTree searchie = new SearchTree();
		
			//testing adding, produces same output as in the script
			searchie.add(6000, "Schaffhausen");
			searchie.add(4000, "Basel");
			searchie.add(3000, "Bern");
			searchie.add(2000, "Lausanne");
			searchie.add(7000, "Zug");
			searchie.add(5000, "Luzern");
			
			
			for(int i=0;i<10;i++){
				//searchie.add(i*1000+23, "Whop");
			}
			
			System.out.println(searchie.toString());
			System.out.println(searchie.search(5000));
			
			searchie.drawTree();
	}

}
