/*
 * Nakida Azevedo 
 * 21 03 2017 
 * 
 * A program which helps NuPack give customers an estimate on pricing for their repackaging based on different fees added
 * 
 */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class NuPack extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton radioFood, radioElectronics, radioPharmaceuticals, radioOther;
	private JButton calculateButton;
	private ButtonGroup buttonGroup;
	private JTextField initialCostTF, numPeopleTF;
	private JFrame f;
	private JPanel p1, p2, p3;
	private JLabel cost;

	public NuPack() {
		//initialize and assign radioButtons
		radioFood = new JRadioButton("Food Item");
		radioFood.setActionCommand("food");
		radioElectronics = new JRadioButton("Electronics");
		radioElectronics.setActionCommand("electronics");
		radioPharmaceuticals = new JRadioButton("Pharmaceuticals");
		radioPharmaceuticals.setActionCommand("pharmaceuticals");
		radioOther = new JRadioButton("Other");
		radioOther.setActionCommand("other");

		buttonGroup = new ButtonGroup();
		// add radio buttons to button group
		buttonGroup.add(radioFood);
		buttonGroup.add(radioElectronics);
		buttonGroup.add(radioPharmaceuticals);
		buttonGroup.add(radioOther);
		
		//instantiate text fields, label, and button
		initialCostTF = new JTextField("Initial Cost");
		numPeopleTF = new JTextField("Number of People");
		calculateButton = new JButton("Calculate Cost");
		cost = new JLabel("");

		//create frame
		f = new JFrame("NuPack Pricing");
		f.setVisible(true);
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//instantiate panels and set background colors
		p1 = new JPanel();
		p2 = new JPanel(new GridLayout(0, 1));
		p3 = new JPanel();
		p1.setBackground(Color.GRAY);
		p2.setBackground(Color.GRAY);
		p3.setBackground(Color.GRAY);

		//add to panels and frame
		f.setLocationRelativeTo(null);
		p1.add(initialCostTF);
		p1.add(numPeopleTF);
		p2.add(radioFood);
		p2.add(radioElectronics);
		p2.add(radioPharmaceuticals);
		p2.add(radioOther);
		p3.add(calculateButton, BorderLayout.NORTH);
		p3.add(cost, BorderLayout.SOUTH);
		f.add(p1, BorderLayout.NORTH);
		f.add(p2, BorderLayout.CENTER);
		f.add(p3, BorderLayout.SOUTH);

		//action event for when calculate button is pressed
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String materials;
				if (radioFood.isSelected()) {
					materials = "food";
					calculate(materials);
				} else if (radioElectronics.isSelected()) {
					materials = "electronics";
					calculate(materials);
				} else if (radioPharmaceuticals.isSelected()) {
					materials = "pharmaceuticals";
					calculate(materials);
				} else if (radioOther.isSelected()) {
					materials = "other";
					calculate(materials);
				}
			}
		});

	}

	public void calculate(String materials) {
		//handles base markup of 5%
		double initialCost = Double.parseDouble(initialCostTF.getText());
		double total = initialCost * 1.05;
		double initial = total;

		//handles the cases of number of people involved
		double numPeople = Double.parseDouble(numPeopleTF.getText());
		double peopleMarkUp = total * (numPeople * 0.012);
		total = total + peopleMarkUp;

		//handles the cases of different markups for materials
		switch (materials) {
		case "food":
			total = total + (initial * 0.13);
			break;

		case "electronics":
			total = total + (initial * 0.02);
			break;

		case "pharmaceuticals":
			total = total + (initial * 0.075);
			break;

		case "other":
			break;

		default:
			return;
		}
		cost.setText("Total Cost: $" + String.format("%.2f", total));
		cost.setVisible(true);
	}

	//main method to run swing event 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new NuPack();
			}
		});
	}

}