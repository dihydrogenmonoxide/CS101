package MandelFred;


import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909624500709994413L;
	private JPanel contentPane;

	private IMG iw;

	/**
	 * Create the frame.
	 */
	public MainWindow(int x, int y,int szx,int szy) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 373);
		this.setTitle("Mandelbröt");
		this.setLocation(x, y);

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

