package interface_windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculations.DistanceCalculator;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JLabel;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(24, 85, 376, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println(textField.getText());
				DistanceCalculator dc = new DistanceCalculator();
				String s=textField.getText();
				
				try {
					
					final long startTime = System.currentTimeMillis();

					DistanceCalculator.TESTupdateDistancesFromAddress(s);

					final long endTime = System.currentTimeMillis();

					System.out.println("Total execution time: " + (endTime - startTime));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SecondWindow t=new SecondWindow();
				t.setVisible(true);
				dispose();
				
			
			}
		});
		btnNewButton.setBounds(311, 135, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblEnterYourAddress = new JLabel("Enter your address");
		lblEnterYourAddress.setBounds(24, 60, 149, 14);
		contentPane.add(lblEnterYourAddress);
		
		
	}
}
