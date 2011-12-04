package OLI_GOL;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;



public class GOL_Fenster extends Component implements MouseListener{

	
	private static final long serialVersionUID = 1L;
	
	private JFrame w;
	private int sz_cell=10;		//size per Cell
	private int pad_y = 5;		//padding
	private int pad_x = 5;
	private boolean run=true;	//if it's running
	private int timeout=100;	
	
	private int cells;		
	private GameOfLife game;

	public  GOL_Fenster(){
		this(50); //default is 50 cells
	}
	
	public GOL_Fenster(int _cells){
		cells=_cells;
		game= new GameOfLife(cells);
		//makes new JFrame, make some settings and add itself and menu
		w = new JFrame("Game of life");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.getContentPane().add(this,BorderLayout.CENTER);
		makeMenu();
		w.pack();
		w.setVisible(true);
			     
		addMouseListener(this);
				
		//now the infinite loop
		run();
	}
	

	/** runs the game as long */
	public void run(){
		while(true){
			try
			{
				Thread.sleep(timeout);
			}
			catch(InterruptedException e)
			{
				//nothing to do
			}
				if(run){nextStep();}
		}
	}
	
	/**Mo Mo Mo MONSTERKILL*/
	public void reset(){
		game.reset(); //clears all cells
		redraw();
	}

	/**return a nice fitted size for the window, with a padding*/
	public Dimension getPreferredSize(){
		return new Dimension(cells*sz_cell+2*pad_x,cells*sz_cell+2*pad_y);
	}
	
	/**redraws JFrame*/
	public synchronized void redraw(){
		w.repaint();
	}
	
	/**next step in the Animation*/
	public void nextStep(){
		game.NextStep();
		redraw();
	}
	
	/**paint method, is called by JFrame on redraw.
	 * Iterates through all cells and draws a rectangle, 
	 * colored green if they're alive and red if they're dead*/
	public void paint(Graphics g){
	
		for(int x=0;x<cells;x++){
			for(int y=0;y<cells;y++){
				if(game.getCell(x, y)){
					g.setColor(new Color(0,255,0));
				}else{
					g.setColor(new Color(255,0,0));
				}
				g.fillRect(pad_x+x*sz_cell, pad_y+y*sz_cell, sz_cell, sz_cell);
			}
		}
	}

	//Mouselistener
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	/**Changes the state of a cell to the opposite*/
	public void mouseReleased(MouseEvent e) {
		//be careful because there's a padding
		int x=e.getX()-pad_x;
		int y=e.getY()-pad_y;
		
		//determine x and y of the cell clicked in
		x=(x-x%sz_cell)/sz_cell;
		y=(y-y%sz_cell)/sz_cell;
		
		//set cell
		game.setCell(x, y, !game.getCell(x, y));
		
		redraw();
	}
	
	
	
	//Now the MENU
	public void makeMenu(){
		//toggle Button
	   JButton toggle = new JButton("Stop");
		toggle.addActionListener(
				new ActionListener(){ 
					public void actionPerformed(ActionEvent ae) 
					{ 
						run=!run;
						JButton b = (JButton)ae.getSource();
						if(run){
							b.setText("Stop");
						}else{
							b.setText("Start");
						}
					}}
		);
		
		//velocity slider
		JSlider velocity = new JSlider();
	    velocity.setBorder(BorderFactory.createTitledBorder("Velocity"));
	    velocity.setMajorTickSpacing(20);
	    velocity.setMinorTickSpacing(5);
	    velocity.setPaintTicks(true);
	    velocity.setPaintLabels(true);
	    
	    velocity.addChangeListener(new ChangeListener(){
	    	public void stateChanged(ChangeEvent e) {
	            JSlider source = (JSlider)e.getSource();
	            if (!source.getValueIsAdjusting()) {
	                timeout = 20*((100-(int)source.getValue())+2);
	            }
	    	}
	    });
	    
	    //clear button
		 JButton reset = new JButton("clear");
		 reset.addActionListener(
					new ActionListener(){ 
						public void actionPerformed(ActionEvent ae) 
						{ 
							reset();
						}}
			);
		 
		 //add it all to the main JFrame
	     JPanel panel= new JPanel();
	     w.getContentPane().add(panel,BorderLayout.SOUTH);
	     panel.add(toggle,BorderLayout.EAST);
	     panel.add(velocity,BorderLayout.CENTER);
	     panel.add(reset,BorderLayout.WEST);
	     panel.setMaximumSize(new Dimension(cells*sz_cell+2*pad_x,50));
	}
	

	
public static void main(String[] args) {
		
		GOL_Fenster w=new GOL_Fenster();
		
		
	}


	

}
