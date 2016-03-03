package gui.messenger.auth;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.messenger.main.MessengerApp;

public class UserAuth extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserAuth dialog = new UserAuth();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UserAuth() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Login to AGNES");
		setBounds(100, 100, 272, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAgnes = new JLabel("AGNES");
		lblAgnes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgnes.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblAgnes.setBounds(71, 11, 123, 62);
		contentPanel.add(lblAgnes);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Username", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(137, 68, 124, 43);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		usernameBox = new JTextField();
		usernameBox.setBounds(5, 18, 114, 20);
		panel.add(usernameBox);
		usernameBox.setColumns(10);
		
		JLabel lblAccretiveGlobalisedNonencrypted = new JLabel("Accretive Globalised Non-Encrypted Semantics");
		lblAccretiveGlobalisedNonencrypted.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccretiveGlobalisedNonencrypted.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		lblAccretiveGlobalisedNonencrypted.setBounds(11, 11, 244, 16);
		contentPanel.add(lblAccretiveGlobalisedNonencrypted);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton connectButton = new JButton("Connect");
				
				connectButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(usernameBox.getText().equals("")){
							JOptionPane.showMessageDialog(null, "CANNOT LOG IN WITHOUT A USERNAME");
						} else {
							MessengerApp.username = usernameBox.getText();
							dispose();
						}
					}
				});
				
				buttonPane.add(connectButton);
				getRootPane().setDefaultButton(connectButton);
			}
			{
				JButton exitButton = new JButton("Leave AGNES");
				exitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
				});
				buttonPane.add(exitButton);
			}
		}
	}
}
