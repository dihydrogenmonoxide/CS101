package ch.unibas.informatik.cs101;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ch.unibas.informatik.cs101.ImageWindowMouseListener;
import ch.unibas.informatik.cs101.KeyListener;

/**
 * A class that manages a java swing window, allowing
 * for easy manipulation of a framebuffer as well
 * as mouse and keyboard polling.
 * @author matthias
 *
 */
@SuppressWarnings("serial")
public class ImageWindow extends Component {
	

	/**
	 * the simulated framebuffer 
	 */
	private BufferedImage _image;
	/**
	 * the java2D graphics context of above framebuffer
	 */
	private Graphics2D  _imageGraphics;
	/**
	 * the Swing window this class manages
	 */
	private JFrame _jFrame;
	/**
	 * listener class for mouse actions
	 */
	private ImageWindowMouseListener _imageWindowMouseListener;
	/**
	 * listener class for keyboard actions
	 */
	private KeyListener _keyListener;

	
	
	/**
	 * Creates a new instance of the ImageWindow class
	 * @param width the width of the paint (image) area
	 * @param height the height of the paint (image) area
	 */
	public ImageWindow(int width, int height) {
		_image= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		_imageGraphics=(Graphics2D)_image.getGraphics();
		_imageWindowMouseListener= new ImageWindowMouseListener();
		_keyListener = new KeyListener();
		clearImage();
	}
	/**
	 * Resizes the paint (image) area to the given size. Also resizes the
	 * window border around it.<br>The old paint area will be copied to the
	 * new paint area and clipped if necessary.
	 * @param newWidth the resized width of the paint area
	 * @param newHeight the resized height of the paint area
	 */
	public void resizeImage(int newWidth,int newHeight) {
		BufferedImage newImage = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
		newImage.getGraphics().setColor(new Color(255,255,255,255));
		((Graphics2D)(newImage.getGraphics())).fill(new Rectangle2D.Float(0, 0,newWidth,newHeight));
		newImage.getGraphics().drawImage(_image, 0,0,null);
		_image=newImage;
		_imageGraphics=(Graphics2D)_image.getGraphics();
		_jFrame.pack();
	}
	/**
	 * Resets the image to the color white.
	 */
	public void clearImage() {
		 fillImage(255, 255, 255);
	}
	/**
	 * Fills the image with the color values given.
	 * @param red the red part of the color 0-255
	 * @param green the green part of the color 0-255
	 * @param blue the blue part of the color 0-255
	 */
	public void fillImage(int red,int green,int blue){
		 Color color = new Color(red, green, blue, 255);
		  _imageGraphics.setColor(color);
		  _imageGraphics.fill(new Rectangle2D.Float(0, 0, _image.getWidth(),_image.getHeight()));
	}
	
	/**
	 * Loads images from file and copies it to the framebuffer.
	 * Supports BMP, GIF, JPEG, PNG, TIFF and maybe even some others.
	 * @param filePath the path, including the filename to the image to be loaded
	 */
	public void loadImage(String filePath) {
		File imageFile= new File(filePath);
		if (!imageFile.exists()) {
			System.err.println("File not found: "+filePath);
		}
		try {
			//load image
			BufferedImage img = ImageIO.read(imageFile);
			//draw image
			_imageGraphics.drawImage(img, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Sets a pixel to the given color value. 
	 * The x and y coordinates are horizontal and vertical positions in the image.<br>
	 * The (0,0) pixel is located at the top left corner of the image, whereas
	 * the (image_width-1,image_height-1) pixel is located at the bottom right corner.
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @param red the red part of the color 0-255
	 * @param green the green part of the color 0-255
	 * @param blue the blue part of the color 0-255
	 */
	public void setPixel(int x,int y,int red,int green,int blue) {
		try {
			_image.setRGB(x, y,  new Color(red, green, blue).getRGB());
		} catch (ArrayIndexOutOfBoundsException e) {
			//someone painted outside the window: do nothing
		}
	}
	
	/**
	 * Returns the color of the image at the specified position
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @return a java.awt.Color object; i.e. the red channel can be read like this
	 * from the object : object.getRed()
	 */
	public Color getPixel(int x, int y) {
		try {
			return new Color(_image.getRGB(x, y));
		} catch (ArrayIndexOutOfBoundsException e) {
			//return white if outside
			return new Color(255,255,255,255);
		}
	}
	/**
	 * Return the red part of the RGB color at the specified location
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @return the red part of the color (int from 0 - 255)
	 */
	public int getPixelRed(int x, int y) {
		return getPixel(x,y).getRed();
	}
	/**
	 * Return the blue part of the RGB color at the specified location
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @return the blue part of the color (int from 0 - 255)
	 */
	public int getPixelBlue(int x, int y) {
		return getPixel(x,y).getBlue();
	}
	/**
	 * Return the green part of the RGB color at the specified location
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @return the red green of the color (int from 0 - 255)
	 */
	public int getPixelGreen(int x, int y) {
		return getPixel(x,y).getGreen();
	}

        /**
         * Set the color of the graphics environment.
         * Set the color for the further drawings. The value are
         * passed as integers for the red, green and blue component.
         * @param r the red part of the color 0-255
         * @param g the green part of the color 0-255
         * @param b the blue part of the color 0-255
         */
        public void setColor(int r, int g, int b) {
                _imageGraphics.setColor(new Color(r,g,b,255));
        }
        /**
         * Draws a line between two coordinates.
         * Daws a line from the point specified by the x- and y-
         * coordinate to an endpoint specified too by coordinates.
         * @param xfrom the x coordinate of the startpoint
         * @param yfrom the y coordinate of the startpoint
         * @param xto the x coordinate of the endpoint
         * @param yto the y coordinate of the endpoint
         */
        public void drawLine(int xfrom, int yfrom, int xto, int yto) {
                _imageGraphics.drawLine(xfrom,yfrom,xto,yto);
        }

	/**
	 * Opens the window at the x,y location on screen with the given
	 * Windowname
	 * @param windowName the windowname
	 * @param x x-position on screen
	 * @param y y-position on screen
	 */
	public void openWindow(String windowName, int x, int y) {
		if (_jFrame != null) {
			_jFrame.dispose();
		}
		_jFrame = new JFrame(windowName);
		_jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (_jFrame!=null) {
					_jFrame.dispose();
					_jFrame=null;
				}
			}
		});
		this.addMouseListener(_imageWindowMouseListener);
		_jFrame.setLocation(x, y);
		_jFrame.addKeyListener(_keyListener);
		_jFrame.add(this);
		_jFrame.pack();
		_jFrame.setVisible(true);
		
		
	}
	/**
	 * Opens the image window.
	 */
	public void openWindow() {
		openWindow("ImageWindow",0,0);
	}
	/**
	 * Closes the image window.
	 */
	public void closeWindow() {
		if (_jFrame==null) return;
		_jFrame.dispose();
		_jFrame=null;
	}
	/**
	 * Checks whether the window is currently open or closed
	 * @return the window open state
	 */
	public boolean isWindowOpen() {
		if (_jFrame==null) return false;
		return true;
	}
	/**
	 * Forces swing to redraw the current image
	 */
	public synchronized void redraw() {
		if (_jFrame != null) {
			_jFrame.repaint();
		} 
	}
	/**
	 * Returns the x pixel position of the mouse in relation
	 * to the top left image pixel.<br>  
	 * If the ImageWindow is not open -1,-1 is returned
	 * @return the x position of the mouse
	 */
	public int getMouseXPos() {
		return getMousePos().x;
	}
	/**
	 * Returns the y pixel position of the mouse in relation
	 * to the top left image pixel.<br> 
	 * If the ImageWindow is not open -1,-1 is returned
	 * @return the y position of the mouse
	 */
	public int getMouseYPos() {
		return getMousePos().y;
	}
	/**
	 * Returns the mouse coordinates relative to the image.<br> 
	 * If the ImageWindow is not open -1,-1 is returned
	 * @return java.awt.Point
	 */
	public Point getMousePos() {
		try {
			Point mousePoint=MouseInfo.getPointerInfo().getLocation();
			Point componentPoint=this.getLocationOnScreen();
			Point p = new Point(mousePoint.x-componentPoint.x,mousePoint.y-componentPoint.y);
			return p;
		} catch (IllegalComponentStateException e) {
			return new Point (-1,-1);
		}
	}
	
	/**
	 * Moves the mouse pointer to the x,y location (relative to the image)
	 * This might not work on all platforms as stated in the corresponding 
	 * javadoc:
	 * "Note that some platforms require special privileges or extensions to 
	 * access low-level input control. If the current platform configuration 
	 * does not allow input control, an AWTException will be thrown when trying 
	 * to construct Robot objects. For example, X-Window systems will throw the 
	 * exception if the XTEST 2.2 standard extension is not supported (or not 
	 * enabled) by the X server."
	 * 
	 * @param x the x Position of the mouse
	 * @param y the y Position of the mouse
	 */
	public void setMousePos(int x, int y) {
		Robot robot;
		Point imageLocation=this.getLocationOnScreen();	
		try {
			robot = new Robot();
			robot.mouseMove(imageLocation.x+x, imageLocation.y+y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Checks whether the left mouse button is currently pressed.<br>
	 * Note this only works accurately if the mouse is over the image.
	 * @return the left mouse button state
	 */
	public boolean mousePressed() {
		return _imageWindowMouseListener.mousePressed();
	}
	
	/**
	 * Checks whether the key with the corresponding keycode is
	 * currently held down (pressed).
	 * @param keyCode the keycode of the key. <br>Use constants from java.awt.event.KeyEvent.<br>
	 * Letters and number are <br>KeyEvent.VK_A - KeyEvent.VK_Z and KeyEvent.VK_0 - KeyEvent.VK_9
	 * <br>More constants are defined in the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/awt/event/KeyEvent.html">
	 * Key Event</a> javadoc.
	 * @return whether the key is currently epressed (true) or not (false)
	 */
	public boolean isKeyDown(int keyCode) {
    	return _keyListener.isKeyDown(keyCode);
    }
	
	/**
	 * Pauses this Thread for the number of milliseconds
	 * @param milliseconds the pause length
	 */
	public void pause(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the current width of the image in pixels.
	 * @return the width of the image in pixels
	 */
	public int getImageWidth() {
		return _image.getWidth();
	}
	
	/**
	 * Returns the current height of the image in pixels.
	 * @return the height of the image in pixels
	 */
	public int getImageHeight() {
		return _image.getHeight();
	}
	//functions needed by java from this component 
	
	/*
	 * needed by java to paint this window (should not be
	 * called directly)
	 * (non-Javadoc)
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g) {
		g.drawImage(_image,0,0,null);	
	}
	/*
	 * needed by java to place this window correctly
	 * (non-Javadoc)
	 * @see java.awt.Component#getPreferredSize()
	 */
	public Dimension getPreferredSize() {
		return new Dimension(_image.getWidth(),_image.getHeight());
	}

        public void setImage(BufferedImage img) {
	    _imageGraphics.drawImage(img, 0, 0, null);
        }

}
