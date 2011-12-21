package MandelFred;


import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileFilter;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909624500709994413L;
	private JPanel contentPane;
	private JFileChooser fc1 = new JFileChooser();

	private IMG iw;

	/**
	 * Create the frame.
	 */
	public MainWindow(int x, int y,int szx,int szy) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 494, 293);
		this.setTitle("Mandelbröt");
		this.setLocation(x, y);
		
		fc1.setFileFilter(new FileFilter() 
		{
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".png") ||
                		f.isDirectory();
            }
            public String getDescription()
            {
                return "Portäibl netwörk gräfiks (*.png)";
            }
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setMnemonic('s');
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);	

		iw = new IMG(szx,szy);
		contentPane.add(iw, BorderLayout.CENTER);
		this.pack();
	}

	public int getszx()
	{
		return iw.im.getWidth();
	}

	public int getszy()
	{
		return iw.im.getHeight();
	}
	public void setPixel(int x, int y,int r, int g, int b)
	{
		int color = 0xFF000000;
		color |=(r<<16)|(g<<8)|b;
		iw.im.setRGB(x, y, color);
	}
	
	private void save()
	{
		if(fc1.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				if(fc1.getSelectedFile().getAbsolutePath().toLowerCase().endsWith(".png"))
					ImageIO.write(iw.im, "png", fc1.getSelectedFile());
				else
					ImageIO.write(iw.im, "png",new File(fc1.getSelectedFile().getAbsolutePath()+".png"));
			}
			catch(IOException e)
			{
				//true story			
			}
		}		
	}
}

class IMG extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4335642054878074768L;
	public BufferedImage  im;
	public IMG(int szx, int szy) 
	{
		im = new BufferedImage(szx,szy,BufferedImage.TYPE_INT_ARGB);
		this.setIgnoreRepaint(false);
		this.setPreferredSize(new Dimension(szx,szy));
	}


	public void paint(Graphics g) 
	{
		g.drawImage(im, 0, 0, null);
	}
}

