package blatt_6;

public class aufgabe_1_a {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 2) {
 			System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
			System.exit(-1);
 		}
		double a=Double.parseDouble(args[0]);
		int b=Integer.parseInt(args[1]);
		
		System.out.println("a^b = "+Math.pow(a, b));
		System.out.println("sinus = "+Math.sin(a));
	}

	/**Function returns Factorial of int a.*/
	public static int factor(int a){
		if(a==1){return 1;}
		return a*factor(a-1);
	}
}
