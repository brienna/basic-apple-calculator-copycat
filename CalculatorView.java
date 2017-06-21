package calculator;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/**
 * 
 * Displays what user sees. Performs no calculations, but passes 
 * information entered by the user to whomever needs it. 
 * Interface is the most complicated part of the MVC. 
 *
 */
public class CalculatorView {
	private JFrame frame;  // NOTE: Recommended to use composition over inheritance
	private JTextField display;
	private JPanel gui;
	private JPanel buttonPanel;

	CalculatorView() {
		customizeFrame();
		customizeTextDisplay();
		createMainPanel();
		createButtonPanel();
		addComponentsToFrame();
	}
	
	private void customizeFrame() {
		frame = new JFrame();

		// Set the look and feel to the cross-platform look and feel,
		// otherwise mac os will have quirks like gaps between jbuttons
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Unsupported look and feel.");
			e.printStackTrace();
		}

		// Let the OS set location, prevent user from resizing window, and exit app on close
		frame.setLocationByPlatform(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);  // must be undecorated for setbackground to work
		frame.setBackground(new Color(0, 0, 0, 100));  // to make display translucent
	}

	private void createMainPanel() {
		// Create the main panel, which by default covers the entire frame
		// NOTE: Good practice. Never put components directly onto a JFrame.
		gui = new JPanel();
		// Set the main panel's layout manager to BorderLayout
		gui.setLayout(new BorderLayout());
		gui.setBackground(new Color(0,0,0,100));  // to make display translucent
	}

	private void createButtonPanel() {
		// Create the button panel 
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(0, -1, 0, -1));
		// Set button panel's layout manager to GridBagLayout
		buttonPanel.setLayout(new GridBagLayout());

		// Create a GridBagConstraints object to control the layout of components
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 35;  // adjust vertical height of buttons
		c.weightx = 1; // needed or buttons will cluster at center w/ gap on sides

		// Add buttons to button panel
		String[][] buttonTexts = {
			{"AC", "+/-", "%", "÷"},
      		{"7", "8", "9", "x"},
      		{"4", "5", "6", "-"},
      		{"1", "2", "3", "+"},
      		{"0", "", ".", "="}
   		};

   		for (int i = 0; i < buttonTexts.length; i++) {
			for (int j = 0; j < buttonTexts[i].length; j++) {
				JButton btn = new JButton(buttonTexts[i][j]);
				// Set the zero button to span two cells & other buttons one cell
				if (buttonTexts[i][j].equals("0")) {
					c.gridwidth = 2;
				} else {
					c.gridwidth = 1;
				}
				// Set button position on grid and add it
				c.gridy = i;
				c.gridx = j;
				buttonPanel.add(btn, c);
				// Set background colors for top row & right column
				if (i == 0 && j <= 2) {
					btn.setBackground(Color.gray);
					btn.setOpaque(true);
				} 
				if (j == 3) {
					btn.setBackground(Color.ORANGE);
					btn.setOpaque(true);
				}
				// Set borders
				int left = 1;
				if (j != 0) {
					left = 0; 
				} 
				btn.setBorder(new MatteBorder(0, left, 1, 1, Color.BLACK));
         	}
         }
	}

	private void customizeTextDisplay() {
		display = new JTextField("0", 9);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("Arial", Font.PLAIN, 40));
		display.setBorder(new EmptyBorder(30, 0, 0, 25));
		display.setForeground(Color.WHITE);
		display.setBackground(new Color(0, 0, 0, 100));
	}

	private void addComponentsToFrame() {
		gui.add(display, BorderLayout.NORTH);
		gui.add(buttonPanel, BorderLayout.CENTER);
		frame.add(gui);
		frame.pack();
	}
	
	void addButtonListener(ActionListener al) {
		// Add the listener to every button in the buttonPanel
		for (Component comp : buttonPanel.getComponents()) {
			if (comp instanceof JButton) {
				JButton btn = (JButton)comp;  // unsure why I have to explicitly cast it
				btn.addActionListener(al);
			}
		}
		// NOTE: Might be better to have buttons as class variables? 
	}

	// Set text in the display
	void setTextDisplay(String text) {
		display.setText(text);
	}
	
	// Open a popup that contains the error message passed
	void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}

	// Get frame
	JFrame getFrame() {
		return frame;
	}
}



