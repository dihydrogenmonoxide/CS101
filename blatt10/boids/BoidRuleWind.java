package boids;

import java.util.Vector;

/*
 * Apply a constant force
 */
public class BoidRuleWind extends BoidRule {

	private Vector2 direction;
	
	BoidRuleWind(Vector2 direction) {
		this.direction = direction;
	}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		return direction;
	}

}

