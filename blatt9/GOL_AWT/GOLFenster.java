package GOL_AWT;

import java.awt.*;
import java.awt.event.*;

//JFrame != awt!

import javax.swing.*;


public class GOLFenster 
extends Component
implements ActionListener 
{
	//defines
	private static final long serialVersionUID = -7089995554612910352L;
	private static final int padding = 8;
	private static final int fieldsz = 20;
	private static final String dead = "";
	private static final String alive = "@";
	private static final Color cdead = new Color(230,230,230);
	private static final Color calive = new Color(0,200,255);
	//vars
	private int szx;
	private JFrame hwnd;
	private JMenuBar hmenu;
	private JMenu menu;
	private JMenuItem menuitem;
	private int num = 10;
	private JButton fields[][];
	private boolean buff[][];
	private boolean buffnext[][];
	 
	public GOLFenster(int num,int posx, int posy)
	{
		this.num = num;
		szx = this.num*fieldsz+padding;

		hwnd = new JFrame("Gäim of läif");
		
		hmenu = new JMenuBar();
		menu = new JMenu("Next Step");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("Do the next evolutionary step in the game of life.");
		
		menuitem = new JMenuItem("Do the next step!");
		menuitem.setMnemonic(KeyEvent.VK_N);
		menuitem.addActionListener(this);
		
		menu.add(menuitem);
		hmenu.add(menu);
		hwnd.setJMenuBar(hmenu);
		
		fields = new JButton[num][num];
		buff = new boolean[num][num];
		buffnext = new boolean[num][num];
		hwnd.setLayout(null);
		hwnd.setLocation(posx,posy);
		hwnd.getContentPane().setPreferredSize(getPreferredSize());
		hwnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		init();
		redraw();
		hwnd.pack();
		hwnd.setResizable(false);
		hwnd.setVisible(true);
		
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(szx,szx);
	}
	
	private void init()
	{
		java.util.Random r = new java.util.Random();
		for(int i = 0; i != num;i++)
		{
			for(int ii=0;ii!=num;ii++)
			{
				//"random" number between 0 and 100
				if(r.nextInt()%100 <= 30)
					mkfield(i,ii,true,true);
				else
					mkfield(i,ii,false,true);
			}
		}
		boolean[][] n = buff;
		buff = buffnext;
		buffnext = n;
	}
	
	private void mkfield(int x, int y, boolean alive, boolean create)
	{
		buffnext[x][y] = alive;
		if(create)
		{
			fields[x][y] = new JButton();
			fields[x][y].addActionListener(this);		
			fields[x][y].setLocation(padding+fieldsz*x, padding+fieldsz*y);
			fields[x][y].setSize(fieldsz,fieldsz);
	//		fields[x][y].setBackground(new Color(200,200,200));
			hwnd.add(fields[x][y]);
			
		}
		if(alive)
		{
			fields[x][y].setText(/*GOLFenster.alive*/"");
			fields[x][y].setBackground(calive);
		}
		else
		{
			fields[x][y].setText(GOLFenster.dead);
			fields[x][y].setBackground(cdead);
		}
		
	}
	private void mkfield(int x, int y, boolean alive)
	{
		mkfield(x,y,alive,false);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == menuitem)
		{
			NextStep();						
		}
		for(int i = 0; i != num; i++)
		{
			for(int ii= 0; ii != num; ii++)
			{
				if(e.getSource() == fields[i][ii])
				{
					buff[i][ii] = !buff[i][ii];
					mkfield(i,ii,buff[i][ii]);
				}
			}
			
		}
		
	}
	 
	public synchronized void redraw() 
	{
		if (hwnd != null) 
		{
			hwnd.repaint();
		} 
	}
	
	public boolean Get(int szx,int szy)
	{
		//this way it'll propperly work
		return buff[(szx+num)%num][(szy+num)%num];
		
	}
	
	public int neighbourCount(int szx, int szy)
	{
		int retval = 0;
		for (int i = szx-1;i != szx+2;i++)
		{
			for(int ii = szy-1;ii!=szy+2;ii++)
			{
				//doesn't matter if index out of bounds; that's checked by get
				if(Get(i,ii)) retval++;
			}
		}
		//substract 1 if the field itself is a cell
		if(Get(szx,szy))
			return --retval;
		else
			return retval;
	}
	
	public void NextStep()
	{
		boolean[][] n = buff;
		for(int i = 0; i !=num;i++)
		{
			for(int ii = 0; ii != num; ii++)
			{
				if(neighbourCount(i,ii) == 3)
						mkfield(i,ii,true);
					else if(neighbourCount(i,ii) == 2 && Get(i,ii) == true)
						mkfield(i,ii,true);
					else
						mkfield(i,ii,false);
			}
		}
		buff =buffnext;
		buffnext = n;
		redraw();
	}
	
	public void NextStepRand()
	{
		boolean[][] n = buff;
		java.util.Random r = new java.util.Random();
		for(int i = 0; i !=num;i++)
		{
			for(int ii = 0; ii != num; ii++)
			{
				if(r.nextInt()%100 <= 80)
				{
					if(neighbourCount(i,ii) == 3)
							mkfield(i,ii,true);
						else if(neighbourCount(i,ii) == 2 && Get(i,ii) == true)
							mkfield(i,ii,true);
						else
							mkfield(i,ii,false);
				}
				else
				{
					n[i][ii]=Get(i,ii);
				}
			}
		}
		buff =buffnext;
		buffnext = n;
		redraw();
	}
	
	public String toString()
	{
		String retval = "";
		for(int i = 0; i !=num; i++)	
		{
			for(int ii = 0; ii != num; ii++)
			{
				if(Get(i,ii))
					retval += "@ ";
				else
					retval += ". ";
			}
			retval += "\n";
		}
		return retval;
	}
	
	public static void main(String args[])
	{
		GOLFenster g = new GOLFenster(15,100,100);

		/*
		while(true)
		{
			System.out.println(g.toString());
			g.NextStep();
			try
			{
				Thread.sleep(2000);
			}
			catch(InterruptedException e)
			{
				//zeh fuck - as if I'd care
				//but honestly... what can go wrong with a sleep timer?
			}
		}//*/
		
	}
	
	

}
