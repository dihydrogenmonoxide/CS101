package boids;

import java.util.Vector;

/*
 * Make boids move to where their neighbours are
 */
public class BoidRuleCohesion extends BoidRule {

	private double factor;
	
	BoidRuleCohesion(double factor) {
		this.factor= factor;
	}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		Vector2 center = new Vector2(0,0);
		for (int i=0; i<neighbours.size(); ++i) {
			if(neighbours.size() == 0)
		           return center;
			center = center.add(neighbours.get(i).getPosition());
		}
		center = center.divide(neighbours.size());
		Vector2 direction = center.minus(boid.getPosition());
		direction = direction.multiply(factor);
		return direction;
	}

	public void setFactor(double factor) { this.factor = factor; }
	public double getFactor() { return factor; }
}


