package OLI_GOL;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;



public class GOL_Fenster extends Component implements MouseListener{

	
	private static final long serialVersionUID = 1L;
	
	//used for painting
	private JFrame w;
	private int size_cell=10;	
	private int pad_y = 5;
	private int pad_x = 5;
	
	private boolean makeBorder=false;
	private Color cAlive = new Color(0,255,0);
	private Color cDead  = new Color(255,0,0);
	private Color cBorder= new Color(0,0,0);
	
	//settings of the game
	private boolean running=true;
	private int delay=100;	
	
	private int cells;		
	private GameOfLife game;

	
	/**Starts GOL_Fenster with a default of 50 cells*/
	public  GOL_Fenster(){
		this(50); 
	}
	
	/**Constructor of GOL_Fenster, specify how many cells you want (int).*/
	public GOL_Fenster(int _cells){
		cells=_cells;
		
		game= new GameOfLife(cells);
		
		w = new JFrame("Game of life");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setBackground(new Color(255,255,255));
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		w.getContentPane().add(this,BorderLayout.CENTER);
		
		makeMenu();
		w.pack();
		w.setVisible(true);
		
		
		addMouseListener(this);
				
		//now to the infinite loop
		while(true){
			/*sleep*/
			try{Thread.sleep(delay);}catch(InterruptedException e){/*nothing to do*/}
			
			if(running){nextStep();}
		}
	}
	
	

	/**return a nice fitted size for the window, with a padding*/
	public Dimension getPreferredSize(){
		return new Dimension(cells*size_cell+2*pad_x,cells*size_cell+2*pad_y);
	}
	
	/**redraws and updates the JFrame and all components*/
	public synchronized void redraw(){
		w.repaint();
	}
	
	/**next step in the Animation*/
	public void nextStep(){
		game.NextStep();
		redraw();
	}
	
	/**paint method, is called by JFrame on redraw.
	 * Iterates through all cells and draws a rectangle for each cell.
	 * Cell is colored green if they're alive and red if they're dead*/
	public void paint(Graphics g){
		for(int x=0;x<cells;x++){
			for(int y=0;y<cells;y++){
				
				if(game.getCell(x, y)){
					g.setColor(cAlive);
				}else{
					g.setColor(cDead);
				}
				
				g.fillRect(pad_x+x*size_cell, pad_y+y*size_cell, size_cell, size_cell);
				
				if(makeBorder){
					g.setColor(cBorder);
					g.drawRect(pad_x+x*size_cell, pad_y+y*size_cell, size_cell, size_cell);
				}
				
			}
		}
	}
	
	/**Mo Mo Mo MONSTERKILL*/
	public void reset(){
		game.reset(); //clears all cells
		redraw();
	}
	
	/**Changes the state of a cell to the opposite*/
	
	public void mouseReleased(MouseEvent e) {
		//be careful because there's a padding
		int x=e.getX()-pad_x;
		int y=e.getY()-pad_y;
		
		//determine the cell clicked in by the coordinates (return the position in the array)
		x=(x-x%size_cell)/size_cell;
		y=(y-y%size_cell)/size_cell;
		
		//set cell
		game.setCell(x, y, !game.getCell(x, y));
		
		redraw();
	}
	
	
	/**********************			NOT IMPORTANT FOR THE EXERCISE			*****************************/
	
	//Mouselistener not used 
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
	private void makeMenu(){
		
	   JButton toggleAnim = new JButton("Stop animation");
		toggleAnim.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			running=!running;
			JButton b = (JButton)ae.getSource();
			if(running){
				b.setText("Stop Animation");
			}else{
				b.setText("Start Animation");
			}
		}});
		
		//velocity slider
		JSlider velocity = new JSlider();
	    velocity.setBorder(BorderFactory.createTitledBorder("Velocity, delay="+String.valueOf(delay)+"ms"));
	    velocity.setMajorTickSpacing(20);
	    velocity.setMinorTickSpacing(5);
	    velocity.setPaintTicks(true);
	    
	    velocity.addChangeListener(new ChangeListener(){public void stateChanged(ChangeEvent e) {
	            JSlider source = (JSlider)e.getSource();
	            if (!source.getValueIsAdjusting()) {
	                delay = 20*((100-(int)source.getValue())+1); 
	                source.setBorder(BorderFactory.createTitledBorder("Velocity delay="+String.valueOf(delay)+"ms"));
	            }
    	}});
	    
		JButton MONSTERKILL = new JButton("Monsterkill");
		MONSTERKILL.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			reset();
			JButton b = (JButton)ae.getSource();
			b.setText("Murderer");
			b.setToolTipText("YOU SHALL DIE");
		}});
		 
		JButton nextStep = new JButton("next step in life");
		nextStep.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			nextStep();
		}});
		
		JButton toggleBorder = new JButton("toggle Border");
		toggleBorder.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			makeBorder=!makeBorder;
			redraw();
		}});
		 
		JButton randColor = new JButton("random colors");
		randColor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			java.util.Random n = new java.util.Random();
			cAlive=new Color(n.nextInt(255),n.nextInt(255),n.nextInt(255));
			cDead=new Color(n.nextInt(255),n.nextInt(255),n.nextInt(255));
			cBorder=new Color(n.nextInt(255),n.nextInt(255),n.nextInt(255));
			redraw();
		}});
		 
		JButton resetColor = new JButton("reset Colors");
		resetColor.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			cAlive = new Color(0,255,0);
			cDead  = new Color(255,0,0);
			cBorder= new Color(0,0,0);
			redraw();
		}});
		 
		JButton randSet = new JButton("set random cells");
		randSet.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent ae){ 
			game.randSet();
			redraw();
			JButton b = (JButton)ae.getSource();
			b.setText("GOD");
		}});
		 
		JPanel menu= new JPanel(new GridLayout(0,2));
	     
	    menu.add(velocity);
	    menu.add(toggleAnim);
	    menu.add(nextStep);
	    menu.add(toggleBorder);
	    menu.add(MONSTERKILL);
	    menu.add(randSet);
	    menu.add(randColor);
	    menu.add(resetColor);
	   
	    menu.setIgnoreRepaint(true);
	    w.getContentPane().add(menu,BorderLayout.SOUTH);
	}
	

	
public static void main(String[] args) {
		
		GOL_Fenster w=new GOL_Fenster();
		
		
	}


	

}
