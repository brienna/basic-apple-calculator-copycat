package calculator;

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
		theView.setVisible(true);
	}
}
