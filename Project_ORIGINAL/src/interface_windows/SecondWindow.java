package interface_windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculations.SelectApp;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondWindow frame = new SecondWindow();
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
	public SecondWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClosest = new JButton("Closest");
		btnClosest.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SelectApp sapp= new SelectApp();
				//sapp.getMinDistance();
				
				ResultWindow rw= new ResultWindow();
				rw.createContent(sapp.getMinValueOf("Distance"),btnClosest.getLabel());
				rw.setVisible(true);
				dispose();
				
			}
		});
		btnClosest.setBounds(39, 42, 160, 38);
		contentPane.add(btnClosest);
		
		JButton btnCheapestDelivery = new JButton("Lowest Order Value");
		btnCheapestDelivery.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SelectApp sapp= new SelectApp();
				//sapp.getMinDistance();
				
				ResultWindow rw= new ResultWindow();
				rw.createContent(sapp.getMinValueOf("MinOrderValue"),btnCheapestDelivery.getLabel());
				rw.setVisible(true);
				dispose();
			}
		});
		btnCheapestDelivery.setBounds(39, 114, 160, 38);
		contentPane.add(btnCheapestDelivery);
		
		JButton btnNewButton = new JButton("Random");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				RandomSelector rs= new RandomSelector();
				rs.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(246, 42, 160, 38);
		contentPane.add(btnNewButton);
		
		JButton btnBestRated = new JButton("Best Rated");
		btnBestRated.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SelectApp sapp= new SelectApp();
				//sapp.getMinDistance();
				
				ResultWindow rw= new ResultWindow();
				rw.createContent(sapp.getMaxValueOf("RatingValue"),btnBestRated.getLabel());
				rw.setVisible(true);
				dispose();
			}
		});
		btnBestRated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBestRated.setBounds(246, 114, 160, 38);
		contentPane.add(btnBestRated);
	}

}
