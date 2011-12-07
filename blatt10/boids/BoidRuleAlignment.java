package boids;

import java.util.Vector;

/*
 * Make boids go into the same direction as their neighbours
 */
public class BoidRuleAlignment extends BoidRule {

	private double factor;
	
	BoidRuleAlignment(double factor) {
		this.factor = factor;
	}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		Vector2 average_velocity = new Vector2(0,0);
		for (int i=0; i<neighbours.size(); ++i) {
			if(neighbours.size() == 0)
		           return average_velocity;
			average_velocity = average_velocity.add(neighbours.get(i).getVelocity());
		}
		average_velocity = average_velocity.divide(neighbours.size());
		return (average_velocity.minus(boid.getVelocity())).multiply(factor);
	}
	
	public void setFactor(double factor) { this.factor = factor; }
	public double getFactor() { return factor; }

}

