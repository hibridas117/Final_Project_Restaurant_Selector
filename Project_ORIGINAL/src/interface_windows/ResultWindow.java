package interface_windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultWindow frame = new ResultWindow();
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
	public ResultWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	
				SecondWindow t=new SecondWindow();
				t.setVisible(true);
				dispose();	
				
			}
		});
		btnBack.setBounds(335, 227, 89, 23);
		contentPane.add(btnBack);
		
		//TODO delete
		/*JLabel lblNewLabel = new JLabel(" New labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew label");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(36, 46, 335, 151);
		contentPane.add(lblNewLabel);
		
		JLabel lblButtonpressedname = new JLabel("ButtonPressedName");
		lblButtonpressedname.setBounds(36, 11, 117, 14);
		contentPane.add(lblButtonpressedname);
		
		JTextPane txtpnNewLabelnewLabelnew = new JTextPane();
		txtpnNewLabelnewLabelnew.setText("New labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew labelNew label");
		txtpnNewLabelnewLabelnew.setBounds(10, 208, 340, 53);
		contentPane.add(txtpnNewLabelnewLabelnew);*/
	}
	
	public void createContent (String label, String buttonPressedName) {
		JTextPane lblNewLabel = new JTextPane();
		lblNewLabel.setText(label);
		lblNewLabel.setBounds(36, 46, 335, 151);
		contentPane.add(lblNewLabel);
		
		JLabel lblButtonpressedname = new JLabel(buttonPressedName);
		lblButtonpressedname.setBounds(36, 11, 335, 14);
		contentPane.add(lblButtonpressedname);
	}
}
