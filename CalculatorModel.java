package calculator;

/**
 * 
 * Holds data and performs calculations. Simplest part of the MVC. 
 * Doesn't know about the existence of the view.
 * 
 */
public class CalculatorModel {
	// Holds the answer of operations
	private int answer;
	
	// Perform operations
	public void add(int firstNumber, int secondNumber) {
		answer = firstNumber + secondNumber;
	}
	public void divide(int firstNumber, int secondNumber) {
		answer = firstNumber / secondNumber;
	}
	public void multiply(int firstNumber, int secondNumber) {
		answer = firstNumber * secondNumber;
	}
	public void subtract(int firstNumber, int secondNumber) {
		answer = firstNumber - secondNumber;
	}
	
	// Provide access to data
	public int getCalculation() {
		return answer;
	}
}
