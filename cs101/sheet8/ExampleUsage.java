import ch.unibas.informatik.cs101.Walker;
import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.BufferedImageWindow;

public class ExampleUsage {

    public static void main (String[] args) {

        ImageWindow iw = new ImageWindow(100,100);
        Walker w = new Walker(iw);
        BufferedImageWindow biw = new BufferedImageWindow(200,200);

        biw.openWindow("BufferedImageWindow");
        biw.setColor(0,255,0);
        biw.drawLine(25,25,175,175);
        biw.setColor(255,0,0);
        biw.setFontSize(20);
        biw.drawString("Hello World! *", 25, 100);
        biw.setFontSize(10);
        biw.drawString("* Footnote!!!", 5, 190);
        biw.redraw();


        iw.openWindow("ImageWindow");
        w.setPos(10,10);
        w.setDir(0,1);
        w.move(10);
        w.pressBallPen();
        w.setColor(0,0,0);
        w.move(10);
        w.setColor(255,0,0);
        w.move(10);
        w.turn(-90);
        w.move(20);
        iw.redraw();

    }

}
