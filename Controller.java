import javax.swing.JOptionPane;

public class Controller {
	private int numCountries;

	public void start() {
		numCountries = getNumberOfCountries();
		String title = "Country Government chooser";
		String message;
		if(numCountries == Integer.MIN_VALUE) {
			message = "No country count chosen.  Game stopped.";
			int messageType = JOptionPane.PLAIN_MESSAGE;
			JOptionPane.showMessageDialog(null, message, title, messageType);
			return;
		}

	}

	private int getNumberOfCountries() {

		int count = 0;
		final int min = 5, max = 20;
		String dialogTitle = String.format("Number of countries");
		String prompt = String.format("Enter number of countries that is between %d and %d", min, max);
		final String errorMessageOutsideRange = String
				.format("Invalid value - outside of allowable range of %d to %d. Try again.", min, max);
		// keep asking for a number until the number is within the valid range.

		boolean keepAsking = true;
		do {
			try {
								
				Object rawResponse = JOptionPane.showInputDialog(null, prompt, dialogTitle, JOptionPane.QUESTION_MESSAGE);
							
				// check for Cancel button click:
				if(rawResponse == null) {
					keepAsking = false;
					count = Integer.MIN_VALUE; // call should check this value as the indicator that Cancel was clicked
					continue;
				}
				
				count = Integer.parseInt((String)rawResponse);
				
				if (count < min || count > max) {
					JOptionPane.showMessageDialog(null, errorMessageOutsideRange, "Range Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					keepAsking = false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid value. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (keepAsking);

		return count;
	}

}
