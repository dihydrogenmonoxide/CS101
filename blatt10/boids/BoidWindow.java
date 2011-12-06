package boids;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

/*
 * Display the boids
 */
public class BoidWindow extends Frame implements WindowListener {
	private static final long serialVersionUID = -4343423991744327814L;
	
	private BoidSimulation boids;
	private Canvas graphics;
	private Panel controls; 

	BoidWindow(BoidSimulation boids) {
		this.boids = boids;		
		setSize(1000, 1000);
		setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Create Canvas to draw on
		graphics = new Canvas();
		controls = new Panel();
		graphics.setBackground(new Color(255,255,255));
		controls.setLayout(new GridBagLayout());	
		
		c.weightx = 100;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(graphics, c);
		c.weighty = 1;
		add(controls, c);
		graphics.createBufferStrategy(2);
		
		validate();
		
		addWindowListener(this);
	}

	public void updateSimulation() {
		BufferStrategy strategy = graphics.getBufferStrategy();

		do {
			// The following loop ensures that the contents of the drawing buffer
			// are consistent in case the underlying surface was recreated
			do {
				// Get a new graphics context every time through the loop
				// to make sure the strategy is validated
				Graphics g = strategy.getDrawGraphics();

				g.clearRect(0,0,this.getWidth(), this.getHeight());
				g.setColor(Color.red);
				for (int i=0; i<boids.size(); ++i) {
					Vector2 p = boids.get(i).getPosition();
					Vector2 v = boids.get(i).getVelocity();
					double speed = v.normL2();
					if (speed==0) {
						speed = 1; 
						v=new Vector2(0.1,0);			
					}
					Vector2 nov = new Vector2(-v.y, v.x);
					nov = nov.multiply(5.0/speed);
					Vector2 nv = v.multiply(10.0/speed);
					int[] xPoints = {(int) (p.x + nov.x - nv.x), (int) (p.x + 0.5*nov.x), (int) (p.x+v.x), (int) (p.x - 0.5*nov.x), (int) (p.x - nov.x - nv.x)};
					int[] yPoints = {(int) (p.y + nov.y - nv.y), (int) (p.y + 0.5*nov.y), (int) (p.y+v.y), (int) (p.y - 0.5*nov.y), (int) (p.y - nov.y - nv.y)};
					g.fillPolygon(xPoints, yPoints, 5);
				}

				// Dispose the graphics
				g.dispose();

				// Repeat the rendering if the drawing buffer contents 
				// were restored
			} while (strategy.contentsRestored());

			// Display the buffer
			strategy.show();

			// Repeat the rendering if the drawing buffer was lost
		} while (strategy.contentsLost());
	}
	
	 public void 	windowActivated(WindowEvent e) {}
	 public void 	windowClosed(WindowEvent e) {}
	 public void 	windowClosing(WindowEvent e) { 
		 System.exit(0); 
	 }
	 public void 	windowDeactivated(WindowEvent e) {}
	 public void 	windowDeiconified(WindowEvent e) {}
	 public void 	windowIconified(WindowEvent e) {}
	 public void 	windowOpened(WindowEvent e) {}

	public void addRuleView(BoidRuleView v) {
		GridBagConstraints c = new GridBagConstraints();
		c.weightx=1;
		c.weighty=0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill=GridBagConstraints.BOTH;
		controls.add(v, c);
		v.setVisible(true);
		validate();
	}
}
