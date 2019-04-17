
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CountryButton extends JButton {
    
    private Government government;
	private String name;
	private Controller controller;

    public CountryButton(String name, Government government, Controller controller) {
        this.name = name;
        this.setText(this.name);
        this.government = government;
        this.controller = controller;
        
        this.addMouseListener(new CountryButtonListener(this, controller));
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