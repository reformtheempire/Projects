package gui.messenger.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import gui.messenger.auth.UserAuth;

import javax.swing.JCheckBox;

public class MessengerApp {

	
	private JFrame frmAgnesIm;
	public static String username = "";
	public static String server = "http://server.mather.ch:25569/";
	
	private JButton refreshButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessengerApp window = new MessengerApp();

					UserAuth userAuthPanel = new UserAuth();
					userAuthPanel.setModal(true);
					userAuthPanel.setVisible(true);

					window.frmAgnesIm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MessengerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgnesIm = new JFrame();
		frmAgnesIm.setResizable(false);
		frmAgnesIm.setTitle("AGNES - Accretive Globalised Non-Encrypted Semantics");
		frmAgnesIm.setBounds(100, 100, 636, 546);
		frmAgnesIm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgnesIm.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 600, 384);
		frmAgnesIm.getContentPane().add(scrollPane);

		JTextPane chatBox = new JTextPane();
		scrollPane.setViewportView(chatBox);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Mando Mensajes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 405, 452, 98);
		frmAgnesIm.getContentPane().add(panel);
		panel.setLayout(null);

		JTextArea messageBox = new JTextArea();
		messageBox.setBounds(12, 21, 428, 65);
		panel.add(messageBox);

		refreshButton = new JButton("Refresh Chat");
		
		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// On click - clear box, get chat.
				
				chatBox.setText("");
				
				InputStream input = null;
				try {
					input = new URL(server + "getAll?userauth=" + username).openStream();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String chat = "unknown";

				try {
					chat = IOUtils.toString(input);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					IOUtils.closeQuietly(input);
				}

				chatBox.setText(chat);
				chatBox.setCaretPosition(chatBox.getText().length());
			}
		});
		
		refreshButton.setBounds(476, 439, 134, 23);
		frmAgnesIm.getContentPane().add(refreshButton);

		JButton sendButton = new JButton("Send Message");

		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				InputStream input = null;
				try {
					
					
					String message = messageBox.getText();
//					message = message.replaceAll(" ", "+");
					
					message = URLEncoder.encode(message, "UTF-8");
					
					System.out.println(message);
					input = new URL(server + "send?userauth=" + username + "&message=" + message  ).openStream();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String status = "unknown";

				try {
					status = IOUtils.toString(input);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					IOUtils.closeQuietly(input);
				}

				if (status != null && status != "unknown") {
					// done. Update box.
					messageBox.setText("");
					refreshButton.doClick();
				}

			}
		});

		sendButton.setBounds(476, 473, 134, 23);
		frmAgnesIm.getContentPane().add(sendButton);

		JCheckBox chckbxReturnSends = new JCheckBox("Enter to send");
		chckbxReturnSends.setEnabled(false);
		chckbxReturnSends.setBounds(476, 407, 134, 24);
		frmAgnesIm.getContentPane().add(chckbxReturnSends);
		startLoop();
	}
	
	public void startLoop(){
		Thread thread = new Thread(new Runnable() {
			public void run() {
				
				while(true){
					refreshButton.doClick();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
	}
}
