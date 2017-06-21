package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Coordinates interactions between the view and model.
 *
 */

public class CalculatorController {
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController(CalculatorView view, CalculatorModel model) {
		theView = view;
		theModel = model;
		
		// Tell the view that whenever the equal button is clicked,
		// to execute the actionPerformed method in the EqualsListener inner class
		theView.addButtonListener(new ButtonListener());
	}
	
	// inner class
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			int firstNumber, secondNumber = 0;
			
			// Surround interactions with the view with 
			// a try block in case numbers weren't properly entered
			try {
				/*firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				theModel.addTwoNumbers(firstNumber, secondNumber);
				theView.setDisplay(theModel.getCalculation());
				*/
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("You need to enter 2 integers");
			}
		}
	}
}
