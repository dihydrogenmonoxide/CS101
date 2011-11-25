package ch.unibas.informatik.cs101;

import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;

public class Walker {

    Point2D.Double pos;
    Point2D.Double dir;
    ImageWindow iw;
    int red, green, blue;
    boolean paint;
    
    /**
     * Initialize the walker in the middle of the ImageWindow.
     * The function stores the ImageWindow and initialize the walker
     * in the middle of the ImageWindow. The pen is away from the paper
     * and the looking direction of the walker is in direction of
     * the positive x-axis.
     **/
    public Walker (ImageWindow w) {
        paint = false;
        iw = w;
        pos = new Point2D.Double(w.getImageWidth()/2,w.getImageHeight()/2);
        dir = new Point2D.Double(1.0,0.0);
    }

    /**
     * Change state of the pen.
     * If the pen is up, the pen is moved down, onto the paper.
     * If the pen is down, the pen is moved up, away from the paper.
     **/
    public void pressBallPen() {
        paint = !paint;
    }

    /**
     * Set the walker to the position.
     * The position of the walker is set to the x and y coordinate.
     * If the ImageWindow is smaller no error occures but only ways
     * in the range of the ImageWindow will be visible of course.
     **/
    public void setPos (double x, double y) {
        pos.x = x;
        pos.y = y;
    }

    /**
     * Set the walker heading in the direction.
     * Set the walking direction like the vector with values x and
     * y. The direction will be normalized so that nothing bad
     * happens when moving forwart.
     **/
    public void setDir (double x, double y) {
        dir.x = x;
        dir.y = y;
        double len = dir.distance(0.0,0.0);
        dir.x /= len;
        dir.y /= len;
    }

    /**
     * Move a given length forward.
     * Moves the walker forward in the viewing direction. It is
     * assumed that the direction is given as a vector of length 1.
     **/
    public void move (double len) {
        Point2D.Double pos_n = new Point2D.Double(pos.x+len*dir.x,pos.y+len*dir.y);
        iw.setColor(red,green,blue);
        if (paint)
            iw.drawLine((int)Math.round(pos.x),(int)Math.round(pos.y),(int)Math.round(pos_n.x),(int)Math.round(pos_n.y));
        pos = pos_n;
    }
    
    /**
     * Turn around a given angle.
     * Turns the walker around his axis. The angle is specified in degrees.
     * Positive and negative angles are allowed and even angles which 
     * describe more than a full rotation.
     **/
    public void turn (double angle) {
        AffineTransform rot = AffineTransform.getRotateInstance(angle*(Math.PI / 180.0),0,0);
        rot.transform(dir,dir);
        double len = dir.distance(0.0,0.0);
        dir.x /= len;
        dir.y /= len;
    }

    /**
     * Set the color the walker uses to draw his way.
     * Set a specific color to be used when the next way is painted. It does
     * not change any color which was already used.
     **/
    public void setColor (int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public void redraw () {
        iw.redraw();
    }

}
