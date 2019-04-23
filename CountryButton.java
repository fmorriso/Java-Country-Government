
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CountryButton extends JButton {

	private Government government;
	private String name;

	private static final Font normalFont = new Font(Font.MONOSPACED, Font.PLAIN, 36);
	private static final Font boldFont = normalFont.deriveFont(Font.BOLD);

	public CountryButton(String name, Government government, Controller controller) {
		this.setFont(boldFont);

		this.name = name;
		setGovernment(government);

		this.setBorder(BorderFactory.createBevelBorder(1));
		this.addMouseListener(new CountryButtonListener(this, controller));
	}

	public void reset() {
		this.government = Government.Unspecified;
		changeButtonText();
		changeButtonColor();
	}

	private void changeButtonColor() {

		switch (government) {
		case Capitalist:
			this.setBackground(Color.BLUE.darker());
			this.setForeground(Color.WHITE);
			break;

		case Socialist:
			this.setBackground(Color.RED.darker());
			this.setForeground(Color.WHITE);
			break;

		case Unspecified:
			this.setBackground(Color.GREEN.darker());
			this.setForeground(Color.WHITE);
			break;

		default:
			break;
		}

	}

	private void changeButtonText() {

		// center the text on two lines
		String centeredTwoLines = String.format("<html><center>%s<br/>%s</center></html>", this.getName(),
				this.government);
		this.setText(centeredTwoLines);

	}

	@SuppressWarnings("unused")
	private CountryButton() {
		/* prevent uninitialized instances */}

	public Government getGovernment() {
		return government;
	}

	public void setGovernment(Government government) {
		this.government = government;
		changeButtonText();
		changeButtonColor();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}