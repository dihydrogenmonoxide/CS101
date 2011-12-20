package faltung;

import java.io.*;

import ch.unibas.informatik.cs101.ImageWindow;

public class FaltungsKern {

    float[][] kern;
    float norm;
    float gain;

    public boolean debug=false;
    
    public FaltungsKern() {
    	gain=0;
    	norm= 1/9F;
    	
    	//standard just softens image
    	kern= new float[][]{
    			{1,1,1},
    			{1,0,1},
    			{1,1,1}
    		}; 
    
    	
    }

    public FaltungsKern(String filename) throws Exception{
      lade_kern(filename);
    }

    public void falten(ImageWindow iw) {
    	if (debug){System.out.println("begin falten");}
        	
    	
    	if (debug){System.out.println("-creating buffer");}
        ImageWindow buffer=new ImageWindow(iw.getWidth(),iw.getHeight());
    	for(int x=0;x<iw.getWidth();x++){
    		for(int y=0;y<iw.getHeight();y++){
    			buffer.setPixel(x, y, iw.getPixelRed(x, y), iw.getPixelGreen(x, y), iw.getPixelBlue(x, y));
    		}
    	}
    	if (debug){System.out.println("-buffer created");}
    	
    	
        float red;
        float green;
        float blue;
        
        int xPos;
        int yPos;
        
        if (debug){System.out.println("-starting iteration");}
        //iterate through all Pixels
    	for(int x=0;x<iw.getWidth();x++){
    		for(int y=0;y<iw.getHeight();y++){
    			
    			//reset colors
    			red = 0;
    			green=0;
    			blue=0;
    			
    			//iterate through core (for each pixel)
    			for(int i=0;i<kern.length;i++){
    				for(int j=0;j<kern[i].length;j++){
    					
    					xPos=x-kern[i].length+j+1;
    					yPos=y-kern.length+i+1;
    					
    					//check if pixel is outside the buffer, if so set Position to the border
    					if(xPos<0){xPos=0;}
    					if(xPos>iw.getWidth()){xPos=iw.getWidth()-1;}
    					if(yPos<0){yPos=0;}
    					if(yPos>iw.getHeight()){yPos=iw.getHeight()-1;}
    					
    					red +=buffer.getPixelRed(xPos,yPos)*kern[i][j];
    					green +=buffer.getPixelGreen(xPos,yPos)*kern[i][j];
    					blue +=buffer.getPixelBlue(xPos,yPos)*kern[i][j];
    					
    				}
    			}
    			//add gain to every channel
    			red +=gain;
    			green +=gain;
    			blue +=gain;
    			
    			
    			if(red>255){red=255;}
    			if(green>255){green=255;}
    			if(blue>255){blue=255;}
    			
    			//set Pixel in the iw
    			iw.setPixel(x, y, (int)(red), (int)(green), (int)(blue));
    			
    			
    		}
    	}
    	if (debug){System.out.println("-ended iteration");}
    	iw.redraw();
        
    	if (debug){System.out.println("finished falten");}
    }

    public void falten_grau(ImageWindow iw) {
        //OPTIONAL 
        // berechne den Grauwert als (Rot+Grün+Blau)/3
        // Falte das Bild nur einmal pro (x/y) Stelle
        // schreibe in jeden Farbkanal diesen Wert
    	if (debug){System.out.println("begin falten_grau");}
        	
    	
    	if (debug){System.out.println("-creating buffer");}
        ImageWindow buffer=new ImageWindow(iw.getWidth(),iw.getHeight());
    	for(int x=0;x<iw.getWidth();x++){
    		for(int y=0;y<iw.getHeight();y++){
    			buffer.setPixel(x, y, iw.getPixelRed(x, y), iw.getPixelGreen(x, y), iw.getPixelBlue(x, y));
    		}
    	}
    	if (debug){System.out.println("-buffer created");}
    	
    	
        float grey;
        
        int xPos;
        int yPos;
        
        if (debug){System.out.println("-starting iteration");}
        //iterate through all Pixels
    	for(int x=0;x<iw.getWidth();x++){
    		for(int y=0;y<iw.getHeight();y++){
    			
    			//reset colors
    			grey=0;
    			
    			//iterate through core (for each pixel)
    			for(int i=0;i<kern.length;i++){
    				for(int j=0;j<kern[i].length;j++){
    					
    					xPos=x-kern[i].length+j+1;
    					yPos=y-kern.length+i+1;
    					
    					//check if pixel is outside the buffer, if so set Position to the border
    					if(xPos<0){xPos=0;}
    					if(xPos>iw.getWidth()){xPos=iw.getWidth()-1;}
    					if(yPos<0){yPos=0;}
    					if(yPos>iw.getHeight()){yPos=iw.getHeight()-1;}
    					
    					grey +=(buffer.getPixelBlue(xPos,yPos)+buffer.getPixelGreen(xPos,yPos)+buffer.getPixelRed(xPos,yPos))/3F*kern[i][j];
    					
    				}
    			}
    			//add gain to every channel
    			grey +=gain;
    			if(grey>255){grey=255;}
    			
    			//set Pixel in the iw
    			iw.setPixel(x, y, (int)(grey), (int)(grey), (int)(grey));
    			
    			
    		}
    	}
    	if (debug){System.out.println("-ended iteration");}
    	iw.redraw();
        
    	if (debug){System.out.println("finished falten");}
    	
    }

    public void median(ImageWindow iw) {
        //OPTIONAL
        // lies alle Werte aus dem Bild im Bereich des Faltungskerns
        // speichere die Werte in einer Liste
        // setze das Zielbild auf den Wert welcher in der Liste in der Mitte steht
    }

    public void lade_kern(String filename) throws Exception{
    	if (debug){System.out.println("parsing new core");}
    	try{
    	FileInputStream is = new FileInputStream(filename); 
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String[] size=br.readLine().substring(5).split(" ");
        String[] norm=br.readLine().substring(5).split(" ");
        String 	 gain=br.readLine().substring(5);
        String[] data=br.readLine().substring(5).split(" ");
      
       
        int x_size=Integer.valueOf(size[0]), y_size=Integer.valueOf(size[1]);
        float[][] _kern=new float[x_size][y_size];
        
        
        Float num=Float.valueOf(norm[0]), denum=Float.valueOf(norm[1]);
        float _norm = num/denum;
        
        float _gain=Float.valueOf(gain);
        
        for(int i=0;i<y_size;i++){
        	for(int j=0;j<x_size;j++){
        		_kern[i][j]=Float.valueOf(data[i*y_size+j])*_norm;
        	}
        }
        
        //everything must have worked
        this.kern=_kern;
        this.gain=_gain;
        this.norm=_norm;
        
        //catching errors
    	}catch(FileNotFoundException e){
    		throw(new Exception("Datei wurde nicht gefunden"));
    	}catch(IOException e){
    		throw(new Exception("Lesefehler der Datei"));
    	}catch(ArrayIndexOutOfBoundsException e){
    		throw(new Exception("Inkonsistente Datei"));
    	}catch(NumberFormatException e){
    		throw(new Exception("Falsche Zahlenformate"));
    	}catch(NullPointerException e){
    		throw(new Exception("Inkonsistente Datei"));
    	}
        
        if (debug){
        	System.out.println("norm= "+norm);
        	System.out.println("gain= "+gain);
        	
        	System.out.println("core= ");
        	for(int i=0;i<kern.length;i++){
        		for(int j=0;j<kern[i].length;j++){
        		System.out.print(kern[i][j]+" ");	
        		}
        		System.out.println();
        	}
        	
        	System.out.println("parsing core completed");
        }
    }
}
