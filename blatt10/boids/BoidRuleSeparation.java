package boids;

import java.util.Vector;

/*
 * Stay away from neighbours
 */
public class BoidRuleSeparation extends BoidRule {
	
	private double strength;
	private double falloff;
	
	BoidRuleSeparation(double strength, double falloff) {
		this.strength = strength;
		this.falloff  = falloff;
	}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		Vector2 result = new Vector2(0,0);
		for (int i=0; i<neighbours.size(); ++i) {
			Vector2 direction =  boid.getPosition().minus(neighbours.get(i).getPosition());
			double distance   =  direction.normL2();
			// Normalize
			if (distance>0)
				direction = direction.divide(distance);
			double rejection = strength/(1e-7*strength+falloff*distance);
			result = result.add(direction.multiply(rejection));
		}
		return result;
	}

	public void setStrength(double strength) { this.strength = strength; }
	public void setFalloff(double falloff)   { this.falloff = falloff; }
	public double getStrength() { return strength; }
	public double getFalloff()  { return  falloff; }

}
