package blatt_6;
public class aufgabe_1_b {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(args.length);
		if (args.length != 3) {
 			System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
			System.exit(-1);
 		}
		// a
		System.out.println("Das erste Vorkommen von "+args[1]+" in "+args[0]+" ist an "+args[0].indexOf(args[1])+" Stelle");
		
		// b
		String no_spaces=args[0].replaceAll("^[ ]+", "");
		no_spaces=no_spaces.replaceAll("[ ]+$", "");
		System.out.println("String without leading or trailing Spaces:|"+no_spaces+"|");
		
		// c
		System.out.println(args[0].replaceAll(args[1], args[2]));
		
	}

}
