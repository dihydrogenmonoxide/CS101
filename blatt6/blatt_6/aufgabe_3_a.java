package blatt_6;

public class aufgabe_3_a {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
 			System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
			System.exit(-1);
 		}
		double a=Double.parseDouble(args[0]);
		int b=Integer.parseInt(args[1]);
		
		System.out.println("a^b = "+power(a, b));
		System.out.println("sinus = "+sinus(a));
		System.out.println("Java Sinus= "+Math.sin(a));
	}

	/**Function returns Factorial of int a.*/
	public static int factor(int a){
		if(a<0){return -1;} // not possible
		if(a==0){return 1;}
		if(a==1){return 1;}
		return a*factor(a-1);
	}
	
	/**returns power of a^b. Not overloaded.*/
	public static double power(double a, int b){
		if(b==0){return 1;} // a^0
		if(b==1){return a;} // a^1=a, Break Condition
		if(b<0){
			b=-b; 
			return 1/(a*power(a,b-1));
		}else{
			return a*power(a,b-1);
		}
	}
	
	public  static double sinus(double a){
		double res=0;
		double breakCond=1E-10;
		double frac;

		int i=1;
		int res_sign=0;
		
		//determine sign and adjust a so its between 0 and PI (better results because of the taylor-approx)
		while(a<0){
			res_sign++;
			a+=Math.PI;
		}
		while(a>Math.PI){
			res_sign++;
			a-=Math.PI;
		}
		
		do{
			frac=power(a,i)/(double)(factor(i)); //fraction described in script
			res=res+power(-1,i+1)*frac; //determine sign with power i+1 (because it starts positive)
			i++;
		}while(frac>=breakCond);
		
		//if uneven numbers of PI added to a, the result is the negation of the calculated res
		if(res_sign%2==1){
			return -res;
		}
		return res;
	}
}
