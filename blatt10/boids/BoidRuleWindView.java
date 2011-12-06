package boids;

import java.awt.event.*;
import java.awt.*;



public class BoidRuleWindView extends BoidRuleView{

	private BoidRuleWind rule;
	private Canvas c;
	private int size=140;		//size of the drawing field
	private double s_fact=1000; //scaling factor
	
	public BoidRuleWindView(BoidRuleWind _rule){
		this.rule=_rule;
		
		/*This canvas draws the directions and length of the wind-vector in itself
		 * */
		c=new Canvas(){
					public void paint(Graphics g){
						g.setColor(new Color(0,255,0));
						g.fillOval(size/2-3, size/2-3, 6, 6);
						g.setColor(new Color(0,0,0));
						g.drawRect(0, 0, c.getSize().width-1, c.getSize().height-1);
						g.drawString("Windy Windy Wind", 5, 10);
						
						//draws the wind-vector
						g.drawLine(
								size/2, size/2,
								size/2+(int)(rule.getDirection().x*s_fact), size/2+(int)(rule.getDirection().y*s_fact)
							);
					}
					};
		
					
		/* Allows for changing the wind-direction*/
		c.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				//calculate new wind-vector
				double x= (arg0.getX()-size/2)/s_fact;
				double y=(arg0.getY()-size/2)/s_fact;
				
				//set new wind-vector
				rule.setDirection(new Vector2(x,y));
				
				c.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {/*do nothing*/}
			
		});
		
		c.setSize(size, size);
		add(c);
	}

}
