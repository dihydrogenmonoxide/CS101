package boids;

import java.util.Vector;

/*
 * Abstract base for BoidRules, each rule acts only on the neighbours.
 */
public abstract class BoidRule {

	public abstract Vector2 getUpdate(Boid boid, Vector<Boid> neighbours);

}