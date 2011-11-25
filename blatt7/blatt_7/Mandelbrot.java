package blatt_7;



public class Mandelbrot {

  public static void show_mandelbrot(Complex c_origin, double c_step, int max_iter) {
    ImageWindow sourceWindow= new ImageWindow(640,480);
    sourceWindow.openWindow("mandelbrot",0,0);
    sourceWindow.resizeImage(640,480);

    /* Implementieren des Mandelbrot Algorithmus */
    
    //iterate through all pixels
    for(int pos_x=0;pos_x<sourceWindow.getImageWidth();pos_x++){
    	for(int pos_y=0;pos_y<sourceWindow.getHeight();pos_y++){
    		//now calculate escape velocity for every pixel
    		
    		//init expansion
    		Complex c=new Complex(c_origin.real()+pos_x*c_step,c_origin.imag()+pos_y*c_step);
    		Complex z=new Complex(0,0);
    		int n=0;
    		
    		//expansion  (reihenentwicklung)
    		while(z.abs_sqr()<=4&&n<max_iter){  //4 because we don't take the root of the .abs_sqr
    			z=c.add(z.sqr());
    			n++;
    		}
    		
    		//System.out.println(Math.log(n));
    		
    		//color pixel
    		sourceWindow.setPixel(pos_x, pos_y, (int)(Math.log(n)/Math.log(max_iter)*255) , (int)(n/(double)(max_iter)*255), 255-(int)(Math.log(n)/Math.log(max_iter)*255));
    	}
    }
    
    
    sourceWindow.redraw();	

  }

  public static void show_mandelbrot_inplace(Complex c_origin, double c_step, int max_iter) {
    ImageWindow sourceWindow= new ImageWindow(640,480);
    sourceWindow.openWindow("mandelbrot inplace",0,0);
    sourceWindow.resizeImage(640,480);

    //iterate through all pixels
    for(int pos_x=0;pos_x<sourceWindow.getWidth();pos_x++){
    	for(int pos_y=0;pos_y<sourceWindow.getHeight();pos_y++){
    		//now calculate escape velocity for every pixel
    		
    		//init expansion
    		Complex c=new Complex(c_origin.real()+pos_x*c_step,c_origin.imag()+pos_y*c_step);
    		Complex z=new Complex(0,0);
    		int n=0;
    		
    		//expansion  (reihenentwicklung)
    		while(z.abs_sqr()<=4&&n<max_iter){  //4 because we don't take the root of the .abs_sqr
    			z.sqr_inplace();
    			z.add_inplace(c);
    			n++;
    		}
    		
    		//System.out.println(Math.log(n));
    		
    		//color pixel
    		sourceWindow.setPixel(pos_x, pos_y, (int)(Math.log(n)/Math.log(max_iter)*255) , (int)(n/(double)(max_iter)*255), 255-(int)(Math.log(n)/Math.log(max_iter)*255));
    	}
    }
    

    sourceWindow.redraw();	
  }

  public static void main(String[] args) {
	long time1=System.currentTimeMillis();    
    show_mandelbrot(new Complex(-2.5, -1.3),   0.005, 1000);
    show_mandelbrot(new Complex(-0.755, -0.1), 0.00002, 1000);
    time1=System.currentTimeMillis()-time1;
   
    long time2=System.currentTimeMillis();
    show_mandelbrot_inplace(new Complex(-2.5, -1.3),   0.005, 1000);
    show_mandelbrot_inplace(new Complex(-0.755, -0.1), 0.00002, 1000);
    time2=System.currentTimeMillis()-time2;
   
    System.out.println("First time  ="+time1+"\nSecond time ="+time2);
  }


}

