package PLZ;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class plzguigen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JFileChooser fc = new JFileChooser();
	private PLZ p = new PLZ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					plzguigen frame = new plzguigen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public plzguigen() {
		p.parse("blatt10/plz/plz.txt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		JMenuItem mntmNewEntry = new JMenuItem("New Entry");
		mntmNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//open the new menu
			}
		});
		mntmNewEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNewEntry);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				save();
				//save it				
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setToolTipText("Enter the PLZ here");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField);
		textField.setColumns(30);
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Find!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			//	cAddy[] result = p.HandleInput()
				
			}
		});
		panel.add(btnNewButton);
		
		
	}
	
	private void save()
	{
		if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			p.save(fc.getSelectedFile()+".txt");		
		}
	}

}
