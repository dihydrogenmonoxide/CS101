package SearchTree;

import ch.unibas.informatik.cs101.BufferedImageWindow;

public abstract class Node {
	int key;
		
	public abstract String toString();
	public abstract void draw(BufferedImageWindow w,int depth,int step_height,int x_min,int x_max, int status);
}
