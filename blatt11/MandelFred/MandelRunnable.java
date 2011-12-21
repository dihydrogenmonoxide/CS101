package MandelFred;


public class MandelRunnable 
extends Thread
{
	Complex c_origin;
	double c_step;
	int max_iter;
	int start;
	int end;
	MainWindow frame;
	public void setOptions(Complex c_origin,double c_step,int max_iter,int start,int end,MainWindow frame)
	{
		this.c_origin=c_origin;
		this.c_step=c_step;
		this.max_iter=max_iter;
		this.start=start;
		this.end=end;
		this.frame=frame;
	}
	public void run() 
	{
		if(frame == null)
		{
			System.out.printf("PEBKAC\n");
		}
		/* Implementieren des Mandelbrot Algorithmus */

		//iterate through all pixels
		for(int pos_x=0;pos_x<frame.getszx();pos_x++)
		{
			for(int pos_y=start;pos_y<=end;pos_y++)
			{
				//now calculate escape velocity for every pixel

				//init expansion
				Complex c=new Complex(c_origin.real()+pos_x*c_step,c_origin.imag()+pos_y*c_step);
				Complex z=new Complex(0,0);
				int n=0;

				//expansion  (reihenentwicklung)
				while(z.abs_sqr()<=4&&n<max_iter)//4 because we don't take the root of the .abs_sqr
				{  
					z=c.add(z.sqr());
					n++;
				}
				//color pixel
				frame.setPixel(pos_x, pos_y, (int)(Math.log(n)/Math.log(max_iter)*255) , (int)(n/(double)(max_iter)*255), 255-(int)(Math.log(n)/Math.log(max_iter)*255));
			}
		}
	}
}

class MandelRunnable_inplace 
extends Thread
{
	Complex c_origin;
	double c_step;
	int max_iter;
	int start;
	int end;
	MainWindow frame;
	public void setOptions(Complex c_origin,double c_step,int max_iter,int start,int end,MainWindow frame)
	{
		this.c_origin=c_origin;
		this.c_step=c_step;
		this.max_iter=max_iter;
		this.start=start;
		this.end=end;
		this.frame=frame;
	}
	public void run() 
	{
		if(frame == null)
		{
			System.out.printf("PEBKAC\n");
		}
		/* Implementieren des Mandelbrot Algorithmus */

		//iterate through all pixels
		for(int pos_x=0;pos_x<frame.getszx();pos_x++)
		{
			for(int pos_y=start;pos_y<=end;pos_y++)
			{
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
				frame.setPixel(pos_x, pos_y, (int)(Math.log(n)/Math.log(max_iter)*255) , (int)(n/(double)(max_iter)*255), 255-(int)(Math.log(n)/Math.log(max_iter)*255));
			}
		}
	}
}

