package ch.unibas.informatik.cs101;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class ImageWindowMouseListener extends MouseInputAdapter {

	private boolean _mousePressed=false;
	private boolean _mouseInside=false;
	protected ImageWindowMouseListener() {

	}
	public void mousePressed(MouseEvent e) {
		_mousePressed=true;
	}

	public void mouseReleased(MouseEvent e) {
		_mousePressed=false;
	}

	public void mouseEntered(MouseEvent e) {
		_mouseInside=true;		
	}

	public void mouseExited(MouseEvent e) {
		_mouseInside=false;
	}

	protected boolean mousePressed() {
		return _mousePressed & _mouseInside;
	}
}
