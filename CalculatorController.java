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
		
		// Tell the view that whenever a button is clicked,
		// to execute the actionPerformed method in the EqualsListener inner class
		theView.addButtonListener(new ButtonListener());
	}
	
	// inner class
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

			// Get recent button input and current display text
			String input = e.getActionCommand();
			String text = theView.getDisplayText();

			// FOR BUTTON WHOSE VALUE IS 'CLEAR':
			// Reset display text and memory
			if (input.matches("C")) {
				theView.setDisplayText("0");
				theModel.resetMemory();
			}

			// FOR BUTTONS WHOSE VALUES ARE NUMBERS OR PERIOD:
			else if (input.matches("[\\d\\.]")) {
				if (input.equals(".") && text.contains(".")) {
					// If input is a period and display text already contains period,
					// do nothing
				} else if (text.equals("0") && !input.equals(".")) {
					// If display text contains only a zero and input is not a period,
					// replace display text with input
					text = input;
				} else {
					// For everything else, 
					// add input to end of display text
					text = text + input;
				}
				theView.setDisplayText(text);
			} 

			// FOR BUTTON WHOSE VALUE IS 'EQUAL':
			// Add display text to memory & calculate
			else if (input.matches("=")) {
				theModel.addToMemory(text);
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
			// Add display text to memory along with the operation, 
			// and reset display text
			else if (operators.contains(input)) {
				theModel.addToMemory(text);
				theModel.addToMemory(input);
				theView.setDisplayText("0");
			}
		}
	}
}
