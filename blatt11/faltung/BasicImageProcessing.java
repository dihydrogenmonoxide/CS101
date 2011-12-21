package faltung;

import ch.unibas.informatik.cs101.*;
import java.util.Scanner;
import java.io.*;


public class BasicImageProcessing {

	
	 BasicImageProcessing(){
		Scanner in = new Scanner(System.in);
		ImageWindow iw= new ImageWindow(500,500);
		FaltungsKern k=new FaltungsKern();
		
		
		String path;
		File img = null;
		File core = null;
		
		while(true){
			int action=11;
			//recurring menu
			System.out.println("Your options: ");
			System.out.println(" 1. Load Image");
			System.out.println(" 2. Load Core");
			System.out.println(" 3. Apply Core");
			System.out.println(" 4. Apply Core in greyscale");
			System.out.println(" 10. Enable debug info");
			System.out.println(" 0. Exit");
			System.out.println("Enter number for action:");
			try{
				action=Integer.valueOf(in.nextLine());
			}catch(Exception e){
				System.out.println("I SAID NUMBAA");
			}
			
			switch(action){
				case 0:
					in.close();
					System.out.println("Exit");
					System.exit(0);
					
				case 1:
					System.out.println("Enter path to image");
					System.out.println("ex.: ./blatt11/faltung/horn.jpg");
					path = in.nextLine();
					img=new File(path);
					System.out.println("Load Image at "+path);
					
					if(img.exists()&&img.canRead()){
						try{
							iw.loadImage(img.getAbsolutePath());
							iw.openWindow();
							iw.resizeImage(iw.getImageWidth(), iw.getImageHeight());
						}catch(Exception e){
							System.out.println("y u no give valid input???");
						}
					}else{
						System.out.println("File path or permissions wrong");
					}
					break;
					
				case 2:
					System.out.println("Enter path to core");
					System.out.println("ex.: ./blatt11/faltung/kern2.txt");
					path = in.nextLine();
					core=new File(path);
					System.out.println("Load Core at "+path);
					if(core.exists()&&core.canRead()){
						try {
							k.lade_kern(core.getAbsolutePath());
						} catch (CoreLoadException e) {
							System.out.println(e.getMessage());
						}
					}else{
						System.out.println("File path or permissions wrong");
					}
					break;
					
				case 3:
					if( img!=null&&core!=null
						&&img.isFile()&&core.isFile()
						){
						System.out.println("Apply Core "+core.getName()+" to Image "+img.getName());
						k.falten(iw);
					}else{
						System.out.println("No image or core set");
					}
					break;
			
				case 4:
					if( img!=null&&core!=null
						&&img.isFile()&&core.isFile()
					){
						System.out.println("Apply Core "+core.getName()+" to Image "+img.getName());
						k.falten_grau(iw);
					}else{
						System.out.println("No image or core set");
					}
					break;
					
				case 10:
					k.debug=true;
					System.out.println("verbose mode enabled");
					break;	
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BasicImageProcessing();
		
	}
}
