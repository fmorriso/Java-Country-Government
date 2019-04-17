import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller {

	private JFrame frame;	
	private JPanel mainPanel;
	private JPanel controlPanel;
	private List<CountryButton> countries;

	private static final int MAX_COLUMNS = 5;

	private int numCountries;

	public Controller(Dimension frameSize) {

		frame = new JFrame("Country Government Chooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(frameSize);
		frame.setSize(frameSize);

	}

	public void start() {
		numCountries = getNumberOfCountries();
		String title = "Country Government chooser";
		String message;
		if (numCountries == Integer.MIN_VALUE) {
			message = "No country count chosen.  Program stopped.";
			int messageType = JOptionPane.PLAIN_MESSAGE;
			JOptionPane.showMessageDialog(null, message, title, messageType);
			return;
		}

		frame.setLayout(new GridLayout(0, 1));

		mainPanel = createMainPanel();

		populateCountryGridPanel();

		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		controlPanel = populateControlPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.CENTER);

		frame.pack();

		// put the JFrame in the middle of the physical screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void populateCountryGridPanel() {
		this.countries = new ArrayList<CountryButton>();
		for (int i = 0; i < this.numCountries; i++) {
			
			boolean needUniqueName = true;
			String name = null;
			do {
				name = CountryNameGenerator.getRandomCountryName();
				if(isUniqueCountryName(name)) {
					needUniqueName = false;
				}
			}
			while(needUniqueName);
						
			CountryButton cb = new CountryButton(name, Government.Capitalist, this);
			this.countries.add(cb);
			mainPanel.add(cb);
		}
	}

	private boolean isUniqueCountryName(String name) {
		if(this.countries.size() == 0) return true;
		boolean isUnique = true;
		for(CountryButton cb: this.countries) {
			if(cb.getName().equals(name)) return false;
		}
		return isUnique;
	}

	private JPanel createMainPanel() {
		
		int numRows = 4;
		if (this.numCountries <= 5)
			numRows = 1;
		else if (this.numCountries <= 10)
			numRows = 2;
		else if (this.numCountries <= 15)
			numRows = 3;
		
		GridLayout layout = new GridLayout(numRows, MAX_COLUMNS);
		layout.setHgap(10);
		layout.setVgap(10);

		JPanel pnl = new JPanel(layout);

		return pnl;
	}

	private JPanel populateControlPanel() {
		JPanel pnl = new JPanel(new GridLayout(1, 0));

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(ae -> resetButtons(ae));
		pnl.add(resetButton);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(ae -> exitProgram(ae));
		pnl.add(exitButton);

		return pnl;
	}

	private void exitProgram(ActionEvent ae) {
		JOptionPane.showMessageDialog(null, "stopping program");
		System.exit(0);
	}

	private void resetButtons(ActionEvent ae) {
		System.out.format("Resetting %d buttons%n", this.countries.size());
		for (CountryButton cb : this.countries) {			
			cb.reset();
		}
		this.mainPanel.paintComponents(this.mainPanel.getGraphics());

	}

	private int getNumberOfCountries() {

		int count = 0;
		final int min = 5, max = 20;
		String dialogTitle = String.format("Number of countries");
		String prompt = String.format("Enter number of countries that is between %d and %d", min, max);
		final String errorMessageOutsideRange = String.format("Invalid value - outside of allowable range of %d to %d. Try again.", min, max);
		// keep asking for a number until the number is within the valid range.

		boolean keepAsking = true;
		do {
			try {

				Object rawResponse = JOptionPane.showInputDialog(null, prompt, dialogTitle, JOptionPane.QUESTION_MESSAGE);

				// check for Cancel button click:
				if (rawResponse == null) {
					keepAsking = false;
					count = Integer.MIN_VALUE; // call should check this value as the indicator that Cancel was clicked
					continue;
				}

				count = Integer.parseInt((String) rawResponse);

				if (count < min || count > max) {
					JOptionPane.showMessageDialog(null, errorMessageOutsideRange, "Range Error", JOptionPane.ERROR_MESSAGE);
				} else {
					keepAsking = false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid value. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (keepAsking);

		return count;
	}

	public void reactToCountryMouseClickEvent(CountryButton countryButton, MouseButton mouseButton) {
		System.out.format("Button %s was clicked by the %s mouse button%n", countryButton.getName(), mouseButton);
		switch (mouseButton) {
			case Left :
				showCountry(countryButton);
				break;

			case Right :
				changeGovernment(countryButton);
				break;

			default :
				break;
		}
	}

	private void changeGovernment(CountryButton countryButton) {
		// TODO Auto-generated method stub

	}

	private void showCountry(CountryButton countryButton) {
		// TODO Auto-generated method stub

	}

}
