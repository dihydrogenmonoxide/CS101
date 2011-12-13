package PLZ;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NewEntry extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPlz;
	private JTextField txtName;
	private JTextField txtKanton;
	
	PLZ p;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			NewEntry dialog = new NewEntry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewEntry(PLZ p) {
		this.p = p;
		setBounds(100, 100, 316, 116);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtPlz = new JTextField();
			txtPlz.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if(txtPlz.getText().equals("PLZ")) txtPlz.setText("");
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(txtPlz.getText().equals("")) txtPlz.setText("PLZ");
				}
			});
			txtPlz.setText("PLZ");
			contentPanel.add(txtPlz);
			txtPlz.setColumns(10);
		}
		{
			txtName = new JTextField();
			txtName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if(txtName.getText().equals("Name")) txtName.setText("");
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(txtName.getText().equals("")) txtName.setText("Name");
				}
			});
			txtName.setText("Name");
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtKanton = new JTextField();
			txtKanton.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if(txtKanton.getText().equals("Kanton")) txtKanton.setText("");
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(txtKanton.getText().equals("")) txtKanton.setText("Kanton");
				}
			});
			txtKanton.setText("Kanton");
			contentPanel.add(txtKanton);
			txtKanton.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addnewtothis();
					}
				});
				okButton.setActionCommand("Add");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
	private void addnewtothis()
	{
		try
		{
			p.addnew(Integer.parseInt(this.txtPlz.getText()), this.txtName.getText(), this.txtKanton.getText());
			this.setVisible(false);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Invalid input in the new entry dialog");
		}
		//remain open if it failed
	}

}
