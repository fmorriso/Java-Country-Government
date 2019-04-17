import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CountryButtonListener implements MouseListener {

	private CountryButton countryButton;
	private static final int LEFT_MOUSE_BUTTON = 1;
	private static final int RIGHT_MOUSE_BUTTON = 3;

	public CountryButtonListener(CountryButton countryButton) {
		this.countryButton = countryButton;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int whichButton = e.getButton();
		System.out.format("%s was clicked by the mouse %d (%s) button%n", countryButton.getName(), whichButton, getMouseButtonName(whichButton));

	}

	private String getMouseButtonName(int buttonNumber) {
		switch (buttonNumber) {
			case 1 :
				return "left";

			case 3 :
				return "right";

			default :
				return "unknown";
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int whichButton = e.getButton();
		System.out.format("%s was pressed by the mouse %d (%s) button%n", countryButton.getName(), whichButton, getMouseButtonName(whichButton));

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
