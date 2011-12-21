package faltung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Panel;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ch.unibas.informatik.cs101.ImageWindow;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JProgressBar;

public class mainwindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909624500709994413L;
	private JPanel contentPane;
	private JFileChooser fc1 = new JFileChooser();
	private JFileChooser fc2 = new JFileChooser();
	private JProgressBar progressBar;
	
	private IMG iw;
	
    private float[][] kern;
    private float norm;
    private float gain;

    private boolean debug=true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainwindow frame = new mainwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainwindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 373);
		this.setTitle("Imageshizzle");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setMnemonic('o');
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenImg();
			}
		});
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		
		JMenuItem mntmLoadCore = new JMenuItem("Load Core");
		mntmLoadCore.setMnemonic('l');
		mntmLoadCore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadCore();
			}
		});
		mntmLoadCore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnFile.add(mntmLoadCore);
		
		JMenuItem mntmFalten = new JMenuItem("Falten");
		mntmFalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				falten(false);
			}
		});
		mntmFalten.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmFalten.setMnemonic('f');
		mnFile.add(mntmFalten);
		
		JMenuItem mntmInGraustufenFalten = new JMenuItem("In Graustufen falten");
		mntmInGraustufenFalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				falten(true);
			}
		});
		mntmInGraustufenFalten.setMnemonic('g');
		mntmInGraustufenFalten.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnFile.add(mntmInGraustufenFalten);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	//	progressBar = new JProgressBar();
	//	contentPane.add(progressBar, BorderLayout.SOUTH);
		
		fc1.setFileFilter(new FileFilter() 
		{
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".jpg") ||
                		f.getName().toLowerCase().endsWith(".png") ||
                		f.getName().toLowerCase().endsWith(".gif") ||
                		f.getName().toLowerCase().endsWith(".tif") ||
                		f.getName().toLowerCase().endsWith(".bmp") ||
                		f.isDirectory();
            }
            public String getDescription()
            {
                return "Images";
            }
		});
		
		fc2.setFileFilter(new FileFilter() 
		{
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".txt") ||
                		f.isDirectory();
            }
            public String getDescription()
            {
                return "Core files (*.txt)";
            }
		});
		fc1.setDialogTitle("Loading our image");
		fc2.setDialogTitle("Loading the core");
		fc1.setCurrentDirectory(new File(System.getProperty("user.dir")+"/blatt11/faltung/"));
		fc2.setCurrentDirectory(new File(System.getProperty("user.dir")+"/blatt11/faltung/"));
		
	
	}
	
	private void OpenImg()
	{
		if(fc1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			if(iw != null)contentPane.remove(iw);
			
			iw = new IMG(fc1.getSelectedFile().getAbsolutePath());
			iw.setIgnoreRepaint(false);
			contentPane.add(iw, BorderLayout.CENTER);
			this.pack();
		}
		
	}
	
	private void LoadCore()
	{
		if(fc2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				this.lade_kern(fc2.getSelectedFile().getAbsolutePath());	
			}
			catch(Exception e)
			{
				//true story				
			}
		}		
	}
	
	private void lade_kern(String filename) throws Exception{
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
	
	private void falten(boolean grey) {
		//#define FuckingInefficient this
		//luckily java doesn't understand #define and furthermore doesn't even care about comments
    	if (debug){System.out.println("begin falten");}
        	
    	if(iw == null)
    	{
    		System.out.println("PEBKAC: load picture first!");
    		return;
    	}
    	
    	if(kern == null)
    	{
    		System.out.println("PEBKAC: load core first!");
    		return;
    	}
    	
    	if (debug){System.out.println("-creating buffer");}
    	BufferedImage old = iw.im;
        BufferedImage buffer=new BufferedImage(old.getWidth(),old.getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        buffer = iw.deepCopy();
    	
    	if (debug){System.out.println("-buffer created");}
    	
    	
        float red;
        float green;
        float blue;
        
        int xPos;
        int yPos;
        int color;
        color = 0xFF000000;//setting alpha chain
        
        if (debug){System.out.println("-starting iteration");}
        //iterate through all Pixels
    	for(int x=0;x<old.getWidth();x++){
    		for(int y=0;y<old.getHeight();y++){
    			
    			//reset colors
    			red = 0;
    			green=0;
    			blue=0;
    			
    			//iterate through core (for each pixel)
    			for(int i=0;i<kern.length;i++){
    				for(int j=0;j<kern[i].length;j++){
    					
    					xPos=x-kern[i].length+j+1;
    					yPos=y-kern.length+i+1;
    					
    					//say no to out of bounds error
    				//	xPos=(xPos+old.getWidth())%old.getWidth();
    				//	yPos=(yPos+old.getHeight())%old.getHeight();
    					
    					//check if pixel is outside the buffer, if so set Position to the border
    					if(xPos<0){xPos=0;}
    					if(xPos>old.getWidth()){xPos=old.getWidth()-1;}
    					if(yPos<0){yPos=0;}
    					if(yPos>old.getHeight()){yPos=old.getHeight()-1;}
    					
    					
    				//	red +=(new Color(buffer.getRGB(xPos, yPos))).getRed()*kern[i][j];
    				//	green +=(new Color(buffer.getRGB(xPos, yPos))).getGreen()*kern[i][j];
    				//	blue +=(new Color(buffer.getRGB(xPos, yPos))).getBlue()*kern[i][j];
    					
    					red +=((buffer.getRGB(xPos, yPos)>>16)&0xFF)*kern[i][j];
    					green +=((buffer.getRGB(xPos, yPos)>>8)&0xFF)*kern[i][j];
    					blue +=(buffer.getRGB(xPos, yPos)&0xFF)*kern[i][j];
    					
    				}
    			}
    			//add gain to every channel
    			red +=gain;
    			green +=gain;
    			blue +=gain;
    			
    			
    			if(red>255){red=255;}
    			if(green>255){green=255;}
    			if(blue>255){blue=255;}
    			
    			if(grey)
    			{
    				red = green = blue = (red+green+blue)/3;
    			}
    			
    			color = color&0xFF000000;
    			color = color|(((int) red)<<16);
    			color = color|(((int) green)<<8);
    			color = color|(((int) blue));
    			
    			//set Pixel in the iw
    			old.setRGB(x, y, color);	
    		}
    //	this.progressBar.setValue(x*100/old.getWidth());
    //	this.repaint();
    	}
    	if (debug){System.out.println("-ended iteration");}
    	
    	this.repaint();
    	//somehow the repaint isn't fully doing the trick - quick & dirty workaround
    	this.setSize(100,100);
    	this.pack();
        
    	if (debug){System.out.println("finished falten");}
    }



}

class IMG extends Panel
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -4335642054878074768L;
	public BufferedImage  im;
	  public IMG(String imageName) 
	  {
		  try {
			  File input = new File(imageName);
			  im = ImageIO.read(input);
		  } catch (IOException e) {
			  System.out.println("Error:"+e.getMessage());
		  }
		  this.setIgnoreRepaint(false);
		  this.setPreferredSize(new Dimension(im.getWidth(),im.getHeight()));
	  }

	  public void paint(Graphics g) 
	  {
		  g.drawImage(im, 0, 0, null);
	  }
	  BufferedImage deepCopy() {
		  ColorModel cm = im.getColorModel();
		  boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		  WritableRaster raster = im.copyData(null);
		  return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		 }

}

