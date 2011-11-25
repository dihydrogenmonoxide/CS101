package ch.unibas.informatik.cs101;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	boolean[] _keyDown = new boolean[65536];
	public KeyListener() {

		
	}
	
	public void keyTyped(KeyEvent e) {
		//System.out.println("k "+e);
	}
    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_UNDEFINED) return;
    	_keyDown[e.getKeyCode()] =true;
    }

    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_UNDEFINED) return;
    	_keyDown[e.getKeyCode()] =false;;
    }
    
    protected boolean isKeyDown(int keyCode) {
    	return _keyDown[keyCode];
    }
}
