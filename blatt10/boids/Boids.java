/**
 * 
 */
package boids;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment; // for fullscreen whoah
import java.io.ObjectInputStream.GetField;


/**
 * Main Boids simulation program
 */
public class Boids {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BoidSimulation simulation = new BoidSimulation(80);
		for (int x=0; x<800; x+=80)
			for (int y=0; y<=600; y+=80)
				simulation.addBoid(x,y);
		
		BoidRuleAlignment alignmentRule = new BoidRuleAlignment(0.02);
		simulation.addRule(alignmentRule);
		
		BoidRuleCohesion cohesionRule = new BoidRuleCohesion(0.045);
		simulation.addRule(cohesionRule);
		
		BoidRuleSeparation separationRule = new BoidRuleSeparation(0.5, 0.02); 		
		simulation.addRule(separationRule);
		
		BoidRuleTarget targetRule1 = new BoidRuleTarget(new Vector2(200,200), 0.6, 0.003);
		BoidRuleTarget targetRule2 = new BoidRuleTarget(new Vector2(900,200), 0.6, 0.003);
		BoidRuleTarget targetRule3 = new BoidRuleTarget(new Vector2(500,600), 0.6, 0.003);		
		simulation.addRule(targetRule1);
		simulation.addRule(targetRule2);
		simulation.addRule(targetRule3);
		
		BoidRuleLimitedSpeed limitedSpeedRule = new BoidRuleLimitedSpeed(0.002);
		simulation.addRule(limitedSpeedRule);
		
		BoidRuleWind windRule = new BoidRuleWind(new Vector2(0.06,0));
		simulation.addRule(windRule);

		BoidWindow w = new BoidWindow(simulation);
	
		w.addRuleView(new BoidRuleAlignmentView(alignmentRule));
		w.addRuleView(new BoidRuleCohesionView(cohesionRule));
		w.addRuleView(new BoidRuleSeparationView(separationRule));
		/** Implement This **/
		/* Ergaenzen Sie die fehlenden Views f"ur die Regeln:
		 * - Alignment
		 * - LimitedSpeed
		 * - Target
		 * - Wind
		 * so dass alle Parameter dieser Regeln in der GUI gesetzt werden koennen.
		 */
		
		//fullscreen
		try{
			GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice screen=ge.getDefaultScreenDevice();
			//now set fullscreen
			if(screen.isFullScreenSupported()){screen.setFullScreenWindow(w);}
		}catch(Exception e){
			System.out.println("Sorry, no fullscreen for you");
		}
		
		
		// exercise
		 w.addRuleView(new BoidRuleLimitedSpeedView(limitedSpeedRule));
         w.addRuleView(new BoidRuleTargetView(targetRule1));
         w.addRuleView(new BoidRuleTargetView(targetRule2));
         w.addRuleView(new BoidRuleTargetView(targetRule3));
         w.addRuleView(new BoidRuleWindView(windRule));
		
		
		int iteration=0;
		while (true) {
			Thread.sleep(10);
			simulation.epoch();
			w.updateSimulation();
			iteration=iteration+1;
		}
	}

}
