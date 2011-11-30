package boids;

import java.util.Vector;

/*
 * Simulate the boids
 */
public class BoidSimulation {
	private
	Vector<Boid>     boids;
	Vector<BoidRule> rules;
	
	double range;
	
	public BoidSimulation(double range) {
		this.range = range;
		boids = new Vector<Boid>();
		rules = new Vector<BoidRule>();
	}
	
	public void addBoid(double x, double y) {
		boids.add(new Boid(x,y));
	}
	
	public void addRule(BoidRule rule) {
		rules.add(rule);
	}
	
	public int size() { return boids.size(); }
	public Boid get(int index) { return boids.get(index); }
	
	/*
	 * Calculate the next epoch of boids
	 */
	void epoch() {
		/** Implement This **/
		/* Loop over all boids, find the neighbours and apply all rules on all boids (boid.accelerate) */
	}

	/* Give back a  list of Boid neighbours, where a neighbour is any boid closer than range */
	private Vector<Boid> getNeighbours(Boid boid) {
		/** Implement This **/
	}
}
