import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller {
	
	private JFrame frame;
	private Dimension frameSize;
	private JPanel mainPanel;
	private static final int MAX_COLUMNS = 5;
	
	private int numCountries;
	
	public Controller(Dimension frameSize) {
		this.frameSize = frameSize;
		
		frame = new JFrame("Country Government Chooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(frameSize);
		frame.setSize(frameSize);

	}

	public void start() {
		numCountries = getNumberOfCountries();
		String title = "Country Government chooser";
		String message;
		if(numCountries == Integer.MIN_VALUE) {
			message = "No country count chosen.  Program stopped.";
			int messageType = JOptionPane.PLAIN_MESSAGE;
			JOptionPane.showMessageDialog(null, message, title, messageType);
			return;
		}
		
		mainPanel = createMainPanel();
		
		populatePanel();
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.pack();
		
		// put the JFrame in the middle of the physical screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void populatePanel() {
		for(int i = 0; i < this.numCountries; i++) {
			String name = String.format("Test%d", i);
			CountryButton cb = new CountryButton(name, Government.Capitalist, this);
			mainPanel.add(cb);	
		}
		
	}

	private JPanel createMainPanel() {		
		int numRows = 4;
		if(this.numCountries <= 5) numRows = 1;
		else if(this.numCountries <= 10) numRows = 2;
		else if(this.numCountries <= 15) numRows = 3;
		JPanel pnl = new JPanel(new GridLayout(numRows, MAX_COLUMNS));
		pnl.setSize(frameSize);
		pnl.setPreferredSize(frameSize);
		return pnl;
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

	public void reactToMouseClickedEvent(CountryButton countryButton, MouseButton mouseButton) {
		System.out.format("Button %s was clicked by the %s mouse button%n", countryButton.getName(), mouseButton);
		
	}

}
