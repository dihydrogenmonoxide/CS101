package PLZ;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class PLZGUI 
extends JFrame
{
	private static final long serialVersionUID = 1L;
	PLZ p = new PLZ();
	
	private JFrame hwnd;
	private JButton add;
	
	
	private JMenuBar hmenu;
	private JMenu menu;
	private JMenuItem newEntry;
	private JMenuItem save;
	private JButton search;
	private JTextField output;
	private JTextField input;
	
	public PLZGUI()
	{
		p.parse("blatt10/plz/plz.txt");
		this.setTitle("Some PLZ thingy\nlolol");
		this.setSize(500,500);
		this.setLocation(10, 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		initAddnew();
		this.pack();
	}
	
	private void init()
	{
		output = new JTextField();
		output.setAutoscrolls(true);
		output.setSize(500,100);
		this.getContentPane().add(output,BorderLayout.SOUTH);
		input = new JTextField();
		search = new JButton("Find!");
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a)
			{
				cAddy[] ret = p.HandleInput(input.getText());
				input.setText("");
				output.setText(p.makeString(ret));
			}
		}); 
		
		newEntry = new JMenuItem("Add a new Entry");
		newEntry.setMnemonic(KeyEvent.VK_N);
		newEntry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a)
			{
				OpenAdd();
			}
		}); 
		
		menu.add(newEntry);
		menu.add(save);
		hmenu.add(menu);
		this.setJMenuBar(hmenu);
		
	}
	
	private void initAddnew()
	{
		hwnd = new JFrame("New entry");
		hwnd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
	private void OpenAdd()
	{
		
	}
}
