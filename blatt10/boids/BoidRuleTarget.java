package boids;

import java.util.Vector;

/*
 * Make boids fly towards a given target
 */
public class BoidRuleTarget extends BoidRule {

	private Vector2 target;
	private double speed;
	private double falloff;
	
	BoidRuleTarget(Vector2 target, double speed, double falloff) {
	    this.speed  = speed;
		this.target = target;
		this.falloff = falloff;
	}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		Vector2 direction = target.minus(boid.getPosition());
		double distance = direction.normL2();
		if (distance>0) direction = direction.divide(distance);
		direction = direction.multiply(speed*Math.exp(-(distance*falloff)));
		return direction;
	}

}

