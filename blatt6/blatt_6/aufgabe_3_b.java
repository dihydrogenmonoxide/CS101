package blatt_6;

public class aufgabe_3_b {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(args.length);
		if (args.length != 3) {
 			System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
			System.exit(-1);
 		}
		// a
		System.out.println("Das erste Vorkommen von "+args[1]+" in "+args[0]+" ist an "+searchIn(args[0],args[1])+" Stelle");
		
		// b
		System.out.println("String without leading or trailing Spaces:|"+removeSpaces(args[0])+"|");
		
		// c
		System.out.println(replace(args[0],args[1],args[2]));
		
	}
	
	/**Searches String b in String a and returns it's position.*/
	public static int searchIn(String a, String b){
		//break condition
		if(a.length()<b.length()){return -1;}
		
		//init
		int res=-1;

		//iterate through base and check for occurence of the first char of search
		for(int i=0;i<a.length()-b.length();i++){
			if(a.charAt(i)==b.charAt(0)){
				boolean equals=true;
				for(int j=0;j+i<a.length()&&j<b.length();j++){
					equals=a.charAt(i+j)==b.charAt(j)&&equals;
				}
				if(equals){return i;}
			}
			
		}
		return res;
	}
	
	/**removes leading or trailing spaces.*/
	public static String removeSpaces(String a){
	
		
		int leadOffset=0;
		int trailOffset=a.length();
		
		for(;a.charAt(leadOffset)==32;leadOffset++){} 	//leadOffset++ as long as there are spaces in the beginning
		for(;a.charAt(trailOffset-1)==32;trailOffset--){}//trailOffset-- as long as there are spaces at the end
		
		//assemble result string from leadOffset to trailOffset
		String res="";
		for(int i=leadOffset;i<trailOffset;i++){
			res+=a.charAt(i);
		}
		return res;
	}
	
	/**searches first substring of b in a and replaces it with c.*/
	public static String replace(String a, String b, String c){
		//easiest case
		if(searchIn(a,b)==-1){return a;}
		
		//init
		int end=a.length()-b.length();
		String res="";
		
		//iterate through a
		for(int i=0;i<end;i++){
			if(a.charAt(i)==b.charAt(0)){ //searches for substring b, see in searchIn()
				boolean equals=true;
				for(int j=0;j+i<a.length()&&j<b.length();j++){
					equals=a.charAt(i+j)==b.charAt(j)&&equals;
				}
				if(equals){ // substring b found
					i+=b.length(); //skip b in a (bi adjusting i)
					end+=c.length()-b.length(); //adjust new length of result
					res+=c; //write c instead of b
				}
			}
			//if substring not found, add the next char to the string
			res+=a.charAt(i);
		}
		return res;
	}
}
