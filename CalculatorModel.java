package calculator;

import java.util.ArrayList;
import java.math.BigDecimal;
/**
 * 
 * Holds data and performs calculations. Simplest part of the MVC. 
 * Doesn't know about the existence of the view.
 * 
 */
public class CalculatorModel {
	// Holds numbers
	private BigDecimal answer = BigDecimal.ZERO;
	private ArrayList<String> memory;

	public CalculatorModel() {
		memory = new ArrayList<String>();
	}
	
	// Perform operations
	public void add(BigDecimal firstNumber, BigDecimal secondNumber) {
		answer = firstNumber.add(secondNumber);
	}
	public void divide(BigDecimal firstNumber, BigDecimal secondNumber) {
		// NOTE: Must provide scale to account for weird divisions like 8/6 or 8/9 with infinity decimal places
		answer = firstNumber.divide(secondNumber, 5, BigDecimal.ROUND_HALF_UP);
	}
	public void multiply(BigDecimal firstNumber, BigDecimal secondNumber) {
		answer = firstNumber.multiply(secondNumber);
	}
	public void subtract(BigDecimal firstNumber, BigDecimal secondNumber) {
		answer = firstNumber.subtract(secondNumber);
	}

	public void calculate() {		
		// Loop through memory, incrementing by 4
		//for (int i = 0; i < memory.size(); i += 3) {
			// Grab first number, operation, and second number
		int i = 0;
			BigDecimal firstNumber = new BigDecimal(memory.get(i));
			String operation = memory.get(i+1);
			BigDecimal secondNumber = new BigDecimal(memory.get(i+2));

			// Perform appropriate calculation according to operation
			try {
				switch (operation) {
					case "+":
						add(firstNumber, secondNumber);
						break;
					case "-":
						subtract(firstNumber, secondNumber);
						break;
					case "x":
						multiply(firstNumber, secondNumber);
						break;
					case "÷":
						divide(firstNumber, secondNumber);
						break;
				}
			} catch (Exception e) {
				// Catch errors such as trying to divide by zero
				answer = null;
			}
		//}
	}
	
	// Provide access to answer, in String form
	public String getAnswer() {
		if (answer == null) {
			return "NaN";
		} else {
			return answer.stripTrailingZeros().toPlainString();
		}
	}

	// Provide access to memory
	public ArrayList<String> getMemory() {
		return memory;
	}

	// Add to memory
	void addToMemory(String str) {
		memory.add(str);
	}

	// Reset memory
	void resetMemory() {
		memory.clear();
	}
}
