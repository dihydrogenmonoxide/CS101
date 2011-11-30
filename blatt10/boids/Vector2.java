package boids;

/*
 * Simple 2D Vector Class
 */
public class Vector2 {
  double x, y;
  

  public Vector2(double x, double y) {
     this.x = x;
     this.y = y;
  }
  
  public Vector2 add(Vector2 o) {
	  return new Vector2(x+o.x, y+o.y);
  }

  public Vector2 multiply(Vector2 o) {
	  return new Vector2(x*o.x, y*o.y);
  }

  public Vector2 multiply(double s) {
	  return new Vector2(x*s, y*s);
  }

  public Vector2 divide(double s) {
	  return new Vector2(x/s, y/s);
  }

  public Vector2 minus(Vector2 o) {
	  return new Vector2(x-o.x, y-o.y);
  }

  public double normL2() {
	  return Math.sqrt(x*x + y*y);
  }
}
