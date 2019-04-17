
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CountryButton extends JButton {
    
    private Government government;
	private String name;
	private static final Font normalFont = new Font(Font.MONOSPACED, Font.PLAIN, 36);
	private static final Font boldFont = normalFont.deriveFont(Font.BOLD); 
			

    public CountryButton(String name, Government government, Controller controller) {
        this.name = name;
        this.government = government;
        this.setFont(boldFont);
        changeButtonText();
        changeButtonColor();
        this.setBorder( BorderFactory.createBevelBorder(1) );
        this.addMouseListener(new CountryButtonListener(this, controller));
    }
    
    public void reset() {
    	this.government = Government.Unspecified;
    	changeButtonText();
    	changeButtonColor();
    }

	private void changeButtonColor() {
		
		switch (government) {
			case Capitalist :
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

			default :
				break;
		}
		
	}

	private void changeButtonText() {
		
		this.setText(String.format("%s %s", this.getName(), this.government));
		
	}

	@SuppressWarnings("unused")
    private CountryButton(){/* prevent uninitialized instances */}

	public Government getGovernment() {
		return government;
	}

	public void setGovernment(Government government) {
		this.government = government;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}