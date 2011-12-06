package boids;

import java.util.Vector;

/*
 * Limit the speed of a boid by excerting a -factor*velocity force
 */
public class BoidRuleLimitedSpeed extends BoidRule {

	private double factor;
	
	BoidRuleLimitedSpeed(double factor) {
		this.factor = factor;
	}
	
	public double getFactor(){return factor;};
	public void setFactor(double _factor){this.factor=_factor;};
	
	@Override
	public Vector2 getUpdate(Boid boid, Vector<Boid> neighbours) {
		// TODO Auto-generated method stub
		return boid.getVelocity().multiply(-factor*boid.getVelocity().normL2());
	}

}
