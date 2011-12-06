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
	
	public Vector2 getDirection(){return direction;}
	public void setDirection(Vector2 _direction){this.direction=_direction;}
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		return direction;
	}

}

