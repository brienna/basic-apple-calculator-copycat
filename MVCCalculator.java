package calculator;

import javax.swing.*;

/**
 * 
 * Runs the MVC.
 *
 */
public class MVCCalculator {
	public static void main(String[] args) {
		// Create view 
		CalculatorView theView = new CalculatorView();
		// Create model
		CalculatorModel theModel = new CalculatorModel();
		// Create controller
		CalculatorController theController = new CalculatorController(theView, theModel);
		// Show the view on the screen
		JFrame frame = theView.getFrame();
		frame.setVisible(true);
	}
}
