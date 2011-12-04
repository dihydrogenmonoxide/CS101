package boids;

/**
 * The Boids
 */
public class Boid {
	private Vector2 position;
	private Vector2 velocity;
	
	Boid(double x, double y) {
		position     = new Vector2(x,y);
		velocity     = new Vector2(0,0);
	}
	
	public Vector2 getPosition() { return position; }
	public Vector2 getVelocity() { return velocity; }
	
	/*
	 * Apply a force to the boid, thereby changing its speed
	 */
	public void accelerate(Vector2 force) {
		velocity = velocity.add(force);
	}
	
	/*
	 * Simulate one timestep by forwards integration of position
	 */
	public void epoch() {
		position = position.add(velocity);
	}
}
