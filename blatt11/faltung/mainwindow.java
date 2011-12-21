package faltung;

import java.awt.BorderLayout;
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

public class mainwindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909624500709994413L;
	private JPanel contentPane;
	private JFileChooser fc1 = new JFileChooser();
	private JFileChooser fc2 = new JFileChooser();

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

		int[][] bufRed =new int[old.getWidth()][old.getHeight()];
		int[][] bufGreen =new int[old.getWidth()][old.getHeight()];
		int[][] bufBlue =new int[old.getWidth()][old.getHeight()];

		for(int x=0;x<old.getWidth();x++){
			for(int y=0;y<old.getHeight();y++){
				bufRed[x][y]=(old.getRGB(x, y)>>16)&0xFF;
				bufGreen[x][y]=(old.getRGB(x, y)>>8)&0xFF;
				bufBlue[x][y]=old.getRGB(x, y)&0xFF;
			}
		}

		if (debug){System.out.println("-buffer created");}


		/*
		 * Iterate through all Pixels, the color values of the momentary pixel is saved in pix* 
		 * the coordinates of the pixels are x,y
		 * and the actual core position in xPos resp. yPos
		 * 
		 * */

		float pixRed;
		float pixGreen;
		float pixBlue;

		int xPos;
		int yPos;
		int color;

		if (debug){System.out.println("-starting iteration");}
		for(int x=0;x<iw.getWidth();x++){
			for(int y=0;y<iw.getHeight();y++){

				//reset colors to gain
				pixRed 	=gain;
				pixGreen=gain;
				pixBlue	=gain;

				//apply core to the actual pixel)
				for(int i=0;i<kern.length;i++){
					for(int j=0;j<kern[i].length;j++){

						xPos=x-kern[i].length+j+1;
						yPos=y-kern.length+i+1;

						//check if pixel is outside the buffer, if so set Position to the border
						if(xPos<0){xPos=0;}
						if(xPos>iw.getWidth()){xPos=iw.getWidth()-1;}
						if(yPos<0){yPos=0;}
						if(yPos>iw.getHeight()){yPos=iw.getHeight()-1;}

						pixRed		+=bufRed[xPos][yPos]*kern[i][j];
						pixGreen 	+=bufGreen[xPos][yPos]*kern[i][j];
						pixBlue		+=bufBlue[xPos][yPos]*kern[i][j];

					}
				}

				//check if colors are inside the range 
				if(pixRed>255){pixRed=255;}
				if(pixGreen>255){pixGreen=255;}
				if(pixBlue>255){pixBlue=255;}

				if(grey)
				{
					pixBlue = pixRed = pixGreen = (pixBlue + pixRed +pixGreen)/3;
				}

				//set Pixel in the iw
				color = 0xFF000000;
				color |=((int) pixRed)<<16;
				color |=((int) pixGreen)<<8;
				color |=(int) pixBlue;

				//set Pixel in the iw
				old.setRGB(x, y, color);
			}
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

