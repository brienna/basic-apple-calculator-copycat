package calculator;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

/**
 * 
 * Displays what user sees. Performs no calculations, but passes 
 * information entered by the user to whomever needs it. 
 * Interface is the most complicated part of the MVC. 
 *
 */
public class CalculatorView extends JFrame {
	private JTextField display = new JTextField("0", 10);

	// Number buttons
	private JButton zeroButton = new JButton("0");
	private JButton oneButton = new JButton("1");
	private JButton twoButton = new JButton("2");
	private JButton threeButton = new JButton("3");
	private JButton fourButton = new JButton("4");
	private JButton fiveButton = new JButton("5");
	private JButton sixButton = new JButton("6");
	private JButton sevenButton = new JButton("7");
	private JButton eightButton = new JButton("8");
	private JButton nineButton = new JButton("9");
	private JButton periodButton = new JButton(".");

	// Operation buttons
	private JButton additionButton = new JButton("+");
	private JButton subtractionButton = new JButton("-");
	private JButton divisionButton = new JButton("÷");
	private JButton multiplicationButton = new JButton("x");
	private JButton signChangeButton = new JButton("+/-");
	private JButton percentButton = new JButton("%");
	private JButton clearButton = new JButton("AC");
	private JButton equalButton = new JButton("=");
	
	CalculatorView() {
		// Create the main panel, which by default covers the entire frame
		// NOTE: Good practice. Never put components directly onto a JFrame.
		JPanel gui = new JPanel();
		// Set the main panel's layout manager to BorderLayout
		gui.setLayout(new BorderLayout());

		// Create the button panel 
		JPanel buttonPanel = new JPanel();
		// Set button panel's layout manager to GridBagLayout
		buttonPanel.setLayout(new GridBagLayout());
		// Create a GridBagConstraints object to control the layout of components
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0); 
		c.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1d;
		constraints.weighty = 1d;
		// Position buttons on the grid 
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(clearButton, c);
		clearButton.setFocusPainted(false);  // set on all buttons later
		c.gridx = 1;
		buttonPanel.add(signChangeButton, c);
		c.gridx = 2; 
		buttonPanel.add(percentButton, c);
		c.gridx = 3;
		buttonPanel.add(divisionButton, c);
		c.gridx = 0;
		c.gridy = 1;
		buttonPanel.add(sevenButton, c);
		c.gridx = 1;
		buttonPanel.add(eightButton, c);
		c.gridx = 2;
		buttonPanel.add(nineButton, c);
		c.gridx = 3;
		buttonPanel.add(multiplicationButton, c);
		c.gridx = 0;
		c.gridy = 2;
		buttonPanel.add(fourButton, c);
		c.gridx = 1;
		buttonPanel.add(fiveButton, c);
		c.gridx = 2;
		buttonPanel.add(sixButton, c);
		c.gridx = 3;
		buttonPanel.add(subtractionButton, c);
		c.gridx = 0;
		c.gridy = 3;
		buttonPanel.add(oneButton, c);
		c.gridx = 1;
		buttonPanel.add(twoButton, c);
		c.gridx = 2;
		buttonPanel.add(threeButton, c);
		c.gridx = 3;
		buttonPanel.add(additionButton, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2; // spans 2 cells
		buttonPanel.add(zeroButton, c);
		c.gridx = 2;
		c.gridwidth = 1; 
		buttonPanel.add(periodButton, c);
		c.gridx = 3;
		buttonPanel.add(equalButton, c);

		// Customize display field
		display.setHorizontalAlignment(JTextField.RIGHT);
		Font dFont = new Font("Arial", Font.BOLD, 30);
		display.setFont(dFont);
		display.setBorder(new EmptyBorder(0, 0, 0, 0));

		// Prevent user from resizing the window
		this.setResizable(false);
		// Exit application when user selects close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add display and button panel to main panel, then main panel to frame
		gui.add(display, BorderLayout.NORTH);
		gui.add(buttonPanel, BorderLayout.CENTER);
		this.add(gui);
		this.pack();
	}
	
	public int getCalcSolution() {
		return Integer.parseInt(display.getText());
	}
	
	// Sets solution, this is going to be called by the controller
	public void setCalcSolution(int solution) {
		display.setText(Integer.toString(solution));
	}
	
	// Most complicated part is below
	
	// If the calculateButton is clicked, execute a method
	// in the Controller named actionPerformed
	// NOTE: Controller, not view, handles actual actions
	// NOTE: no modifier (private, public) means this is package-private (default)
	void addEqualListener(ActionListener listenForEqual) {
		equalButton.addActionListener(listenForEqual);
	}
	
	// Open a popup that contains the error message passed
	void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}



