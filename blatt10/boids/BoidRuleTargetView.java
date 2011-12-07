package boids;

import java.awt.event.*;
import java.awt.*;

public class BoidRuleTargetView extends BoidRuleView implements AdjustmentListener{

		private BoidRuleTarget rule;
		
		private Canvas c;
		
		private double wMaxSize=1000; //max window size of the boids window
		
		private double speedScale=10000;
		private Scrollbar speedScrl;
		private Label speedLbl;
		
		private double fallOffScale=10000;
		private Scrollbar fallOffScrl;
		private Label fallOffLbl;
		
		public BoidRuleTargetView(BoidRuleTarget _rule){
			this.rule=_rule;
			
			/*
			 * Canvas for which draws the Location of the actual Target relative to the Window in itself*/
			c=new Canvas(){
						public void paint(Graphics g){
					
							g.setColor(new Color(255,0,0));
							
							//this draws the actual Target as rectangle
							g.fillOval((int)(rule.getTarget().x/wMaxSize*c.getSize().height)-3,(int)(rule.getTarget().y/wMaxSize*c.getSize().width)-3, 6, 6);
							
							g.setColor(new Color(0,0,0));
							g.drawString("Target", 5, 12);
							g.drawRect(0, 0, c.getSize().width-1, c.getSize().height-1);
						}
						};
			//this.getMaximumSize()
			/*Mouse Listener for moving the target*/
			c.addMouseMotionListener(new MouseMotionListener(){
				@Override
				public void mouseDragged(MouseEvent arg0) {
					double x=arg0.getX()/(double)c.getSize().height*wMaxSize;
					double y=arg0.getY()/(double)c.getSize().width*wMaxSize;
					rule.setTarget(new Vector2(x,y));
					c.repaint();
				}
				@Override
				public void mouseMoved(MouseEvent arg0) {}
			});
			
			//some tweaking so the canvas does't get to big, 
			//however, i cannot set any size permanently as GridLayout
			//ignores it
			c.setSize(140,140);
			c.setPreferredSize(new Dimension(140,140));
			c.setMaximumSize(new Dimension(140,140));
			
			/*Scrollbar for changing the speed value*/
			speedLbl  =	new Label(""+rule.getSpeed());
			speedScrl = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getSpeed()*speedScale), 10, 1, (int) (5*speedScale));
			speedScrl.addAdjustmentListener(this);
			
			/*Scrollbar for changing the falloff value*/
			fallOffLbl  =	new Label(""+rule.getFallOff());
			fallOffScrl = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getFallOff()*speedScale), 10, 1, (int) (1*speedScale));
			fallOffScrl.addAdjustmentListener(this);
			
			
			/*
			 *    Nice painting the GUI,
			 *    
			 *    Labels:
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    X LABEL  X   VALUE X
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    
			 *    Container:
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    X    Labels        X
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    X    Slider        X
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    
			 *    targetInput
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    X    Container     X
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    X    Container     X
			 *    XXXXXXXXXXXXXXXXXXXX
			 *    
			 *    
			 *    XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			 *    X               X           X
			 *    X targetInputs  X  Canvas   X
			 * 	  X               X           X
			 *    XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			 * 
			 * 
			 * */
			
			Panel speedLabels=new Panel();
			speedLabels.setLayout(new GridLayout(1,3));
			speedLabels.add(new Label("Speed"));
			speedLabels.add(speedLbl);
		
			Panel speedContainer=new Panel();
			speedContainer.setLayout(new GridLayout(3,1));
			speedContainer.add(new Label("Target Settings"));
			speedContainer.add(speedLabels);
			speedContainer.add(speedScrl);
			
			
			Panel fallOffLabels=new Panel();
			fallOffLabels.setLayout(new GridLayout(1,2));
			fallOffLabels.add(new Label("Falloff"));
			fallOffLabels.add(fallOffLbl);
			
			Panel fallOffContainer=new Panel();
			fallOffContainer.setLayout(new GridLayout(2,1));
			fallOffContainer.add(fallOffLabels);
			fallOffContainer.add(fallOffScrl);
			
			Panel targetInputs=new Panel();
			targetInputs.setLayout(new GridLayout(2,2));
			targetInputs.add(speedContainer);
			targetInputs.add(fallOffContainer);
			
			
			setLayout(new GridLayout(1,2));
			
			add(targetInputs);
			
			add(c);
			
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent arg0) {
			// TODO Auto-generated method stub
			rule.setSpeed(speedScrl.getValue()/speedScale);
		    speedLbl.setText("" + rule.getSpeed());
		    
		    rule.setFallOff(fallOffScrl.getValue()/fallOffScale);
		    fallOffLbl.setText("" + rule.getFallOff());
		    
		}
		
}
