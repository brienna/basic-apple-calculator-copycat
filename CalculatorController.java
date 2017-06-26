package calculator;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;

/**
 * 
 * Coordinates interactions between the view and model.
 *
 */

public class CalculatorController {
	private CalculatorView theView;
	private CalculatorModel theModel;
	private ArrayList<String> operators;
	
	public CalculatorController(CalculatorView view, CalculatorModel model) {
		theView = view;
		theModel = model;

		operators = new ArrayList<String>();
			operators.add("+");
			operators.add("=");
			operators.add("÷");
			operators.add("x");
			operators.add("-");
		
		// Add a listener to every button in the view, so that whenever a button is pressed,
		// execute the actionPerformed method in the EqualsListener inner class 
		theView.addButtonListener(new ButtonListener());
	}
	
	// inner class
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			// Get recent button input and current displayText
			String input = e.getActionCommand();
			String displayText = theView.getDisplayText();

			// FOR BUTTON WHOSE VALUE IS 'CLEAR':
			// Reset displayText and memory
			if (input.matches("C")) {
				theView.setDisplayText("0");
				theModel.resetMemory();
			}

			// FOR BUTTONS WHOSE VALUES ARE NUMBERS OR PERIOD:
			else if (input.matches("[\\d\\.]")) {
				if (input.equals(".") && displayText.contains(".")) {
					// If input is a period and  displayText already contains period,
					// do nothing
				} else if ((displayText.equals("0") && !input.equals(".")) || operators.contains(theModel.getMemory().get((theModel.getMemory().size() - 1)))) {
					// If displayText contains only a zero and input is not a period,
					// or if the last input in the memory was an operation, 
					// replace displayText with input
					displayText = input;
				} else {
					// For everything else, 
					// add input to end of  displayText
					displayText = displayText + input;
				}
				theView.setDisplayText(displayText);
			} 

			// FOR BUTTON WHOSE VALUE IS 'EQUAL':
			// Add displayText to memory & calculate
			else if (input.matches("=")) {
				theModel.addToMemory(displayText);
				theModel.addToMemory(input);
				System.out.println(theModel.getMemory());
				// If memory contains 3 or more elements, calculate
				if (theModel.getMemory().size() >= 3) {
					theModel.calculate();
					theView.setDisplayText(theModel.getAnswer());
				}
				theModel.resetMemory();
			}

			// FOR BUTTONS WHOSE VALUES ARE OPERATIONS:
			// Add displayText to memory along with the operation
			else if (operators.contains(input)) {
				theModel.addToMemory(displayText);
				theModel.addToMemory(input);
			}
		}
	}
}
