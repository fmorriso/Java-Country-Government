import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CountryButtonListener implements MouseListener {

	private CountryButton countryButton;
	private Controller controller;
	
	public CountryButtonListener(CountryButton countryButton, Controller controller) {
		this.countryButton = countryButton;
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// NOTE: do NOT put code here because we prefer mousePressed event in order to
		// 1. avoid duplicate reactions (pressed first, followed by clicked second)
		// to left mouse click.
		// 2. ignore Enter key (this program is designed to be used only with a mouse)
	}
	
	private MouseButton getMouseButton(int buttonNumber) {
		switch (buttonNumber) {
			case 1 :
				return MouseButton.Left;

			case 3 :
				return MouseButton.Right;

			default :
				return MouseButton.Unknown;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// we don't care about this event

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// we don't care about this event

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int whichButton = e.getButton();
		System.out.format("%s was pressed by the mouse %d (%s) button%n", countryButton.getName(), whichButton, getMouseButton(whichButton));
		// relay which country button was clicked and which mouse button (left or right) was clicked.
		controller.reactToCountryMouseClickEvent(this.countryButton, getMouseButton(whichButton));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// we don't care about this event

	}

}
