package ch.unibas.informatik.cs101;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;

/**
 * A class that manages a java swing window, allowing
 * for easy manipulation of a framebuffer as well
 * as mouse and keyboard polling.
 * @author matthias
 *
 */
@SuppressWarnings("serial")
public class BufferedImageWindow extends ImageWindow {

    BufferedImage buffer;
    Graphics2D bufferGraphics;
    int w, h;

    public BufferedImageWindow (int w, int h) {
        super(w,h);
        this.w = w;
        this.h = h;
        buffer = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
        bufferGraphics = (Graphics2D)buffer.getGraphics();
        //should be done although when resizing if implemented
        Color color = new Color(255, 255, 255, 255);
	bufferGraphics.setColor(color);
	bufferGraphics.fill(new Rectangle2D.Float(0, 0, w,h));
    }

    public void setPixel(int x, int y, int r, int g, int b) {
        try {
        	buffer.setRGB(x, y,  new Color(r, g, b).getRGB());
        } catch (ArrayIndexOutOfBoundsException e) {
        	//someone painted outside the buffer: do nothing
        }

    }

    public void setColor(int r, int g, int b) {
        bufferGraphics.setColor(new Color(r,g,b,255));
    }

    public void drawLine(int x, int y, int dx, int dy) {
        bufferGraphics.drawLine(x,y,dx,dy);
    }

    public void drawString(String s, int x, int y) {
        try {
            bufferGraphics.drawString(s,x,y);
        } catch (NullPointerException e) {
            // nullpointer as string passed to the function
        }
    }

    public void setFontSize(int size) {
        bufferGraphics.setFont(new Font(bufferGraphics.getFont().getName(),Font.PLAIN,size));
    }

    public void redraw() {
        setImage(buffer);
        super.redraw();
    }

    public void clearBuffer() {
        bufferGraphics.setColor(new Color(255,255,255,255));
        bufferGraphics.fill(new Rectangle2D.Float(0,0,w,h));
    }

    public void resizeImage(int nw, int nh) {
        System.out.println("WARNING: resizing a BufferedImageWindow not yet supported!!!!");
    }

    public void pause(int i) {
        super.pause(i);
    }

}
