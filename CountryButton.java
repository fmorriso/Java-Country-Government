
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CountryButton extends JButton {
    
    private Government government;
	private String name;

    public CountryButton(String name, Government government) {
        this.name = name;
        this.setText(this.name);
        this.government = government;
        
        this.addMouseListener(new CountryButtonListener(this));
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