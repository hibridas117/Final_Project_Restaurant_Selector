package interface_windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculations.Restaurant;
import calculations.SelectApp;

import java.awt.GridLayout;
import javax.swing.JSlider;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JTextPane;

public class RandomSelector extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomSelector frame = new RandomSelector();
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
	public RandomSelector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaxDistance = new JLabel("Max distance: 3 km");
		lblMaxDistance.setBounds(5, 3, 124, 14);
		contentPane.add(lblMaxDistance);

		JSlider slider = new JSlider();
		slider.setBounds(5, 22, 424, 45);
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				lblMaxDistance.setText("Max distance: " + slider.getValue() + " km");
				// lblM."Max distance "+slider.getValue()+" km
			}
		});
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setValue(3);
		slider.setPaintLabels(true);
		slider.setMaximum(10);
		contentPane.add(slider);

		JLabel lblMinRating = new JLabel("Min rating: 4/5");
		lblMinRating.setBounds(5, 72, 118, 14);
		contentPane.add(lblMinRating);
		java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(50), new JLabel("5.00"));
		labelTable.put(new Integer(45), new JLabel("4.50"));
		labelTable.put(new Integer(40), new JLabel("4.00"));
		labelTable.put(new Integer(35), new JLabel("3.50"));
		labelTable.put(new Integer(30), new JLabel("3.00"));
		labelTable.put(new Integer(25), new JLabel("2.50"));
		labelTable.put(new Integer(20), new JLabel("2.00"));
		labelTable.put(new Integer(15), new JLabel("1.50"));
		labelTable.put(new Integer(10), new JLabel("1.00"));
		labelTable.put(new Integer(5), new JLabel("0.50"));
		labelTable.put(new Integer(0), new JLabel("0.00"));

		JSlider slider_2 = new JSlider(0, 50, 40);
		slider_2.setBounds(5, 91, 424, 45);
		slider_2.setMinorTickSpacing(1);
		slider_2.setMajorTickSpacing(5);
		slider_2.setPaintTicks(true);
		slider_2.setLabelTable(labelTable);
		slider_2.setPaintLabels(true);
		contentPane.add(slider_2);

		JLabel lblResult = new JLabel("Result:");
		lblResult.setVisible(false);
		lblResult.setBounds(5, 156, 419, 14);
		contentPane.add(lblResult);

		JTextPane textPaneResult = new JTextPane();
		textPaneResult.setVisible(false);
		textPaneResult.setBounds(5, 181, 419, 23);
		contentPane.add(textPaneResult);

		JButton btnRandomise = new JButton("Randomize");
		btnRandomise.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SelectApp sapp = new SelectApp();
				Restaurant rest= new Restaurant();
				System.out.println(slider.getValue());
				System.out.println(slider_2.getValue() / 10.0);
				System.out.println(sapp.getRandomRestaurant(3, 3.8));
				// System.out.println(sapp.getRandomRestaurant(slider.getValue(),
				// slider_2.getValue()/10.0));
				rest=sapp.getRandomRestaurant(slider.getValue(), (slider_2.getValue() / 10.0));
				
				lblResult.setText("Result of "+rest.getRestaurantsNumber()+" restaurants");
				lblResult.setVisible(true);
				
				
				
				rest.getName();
				rest.getRestaurantsNumber();
				textPaneResult.setText(rest.getName());
				textPaneResult.setVisible(true);
				/*
				 * ResultWindow rw = new ResultWindow();
				 * rw.createContent(sapp.getRandomRestaurant(slider.getValue(),
				 * (slider_2.getValue() /
				 * 10.0)),"Random restaurant Distance <= "+slider.getValue()+" km Rating >= "+
				 * (slider_2.getValue() / 10.0)); rw.setVisible(true); dispose();
				 */

			}

		});

		btnRandomise.setBounds(306, 214, 118, 23);
		contentPane.add(btnRandomise);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				SecondWindow t = new SecondWindow();
				t.setVisible(true);
				dispose();

			}
		});
		btnBack.setBounds(5, 215, 89, 23);
		contentPane.add(btnBack);

		slider_2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent ce) {
				lblMinRating.setText("Min rating: " + slider_2.getValue() / 10.0 + "/5");
			}
		});
	}

}
