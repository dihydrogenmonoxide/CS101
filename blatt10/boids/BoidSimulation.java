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
		for (Boid boid :boids ){
			
			//get neighbours
			Vector <Boid> neighbours=getNeighbours(boid);	
			
			//apply all rules
			for(BoidRule rule:rules){
				boid.accelerate(rule.getUpdate(boid, neighbours));
			}
			
			//move the boid to the calculated position
			boid.epoch();
		}
		
	}

	/* Give back a  list of Boid neighbours, where a neighbour is any boid closer than range */
	private Vector<Boid> getNeighbours(Boid boid) {
		/** Implement This **/

		Vector<Boid> result = new Vector<Boid>();
		Vector2 middle=boid.getPosition();
		
		for (Boid neighbour :boids ){
			
			/*Calculates if neighbour is withing range, does this by 
			 * using (m-r)^2<R^2 (circle for vectors)
			 * point r, middlepoint m and radius R
			 * 
			 * if true add it to the result list, doesn't add itself to the list
			 */ 
			if( !neighbour.equals(boid)&&
				middle.minus(neighbour.getPosition()).normL2()<range*range)
			{
				result.add(neighbour);
			}
			
		}
		return result;
	}
}
