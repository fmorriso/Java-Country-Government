import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Platform;
//Country Government chooser
import javafx.embed.swing.JFXPanel;

public class Driver {

	public static void main(String[] args) {

		DialogHelper.makeDialogsEasierToSee(32);

		// capture size of screen we're using
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Define the size of the JFrame as a square that is a percentage of the
		// available screen area and a multiple of 100.
		final int frameHeight = (int) (screenSize.height * 85.00 / 100) / 100 * 100;
		final int frameWidth = frameHeight;

		Dimension frameSize = new Dimension(frameWidth, frameHeight);
		System.out.format("frameSize=%s%n", frameSize);

		// fake out Java to avoid the dreaded "Toolkit not initialized" error:
		new JFXPanel(); // this will prepare JavaFX toolkit and environment

		Platform.runLater(() -> {
			try {
				Controller ctrl = new Controller(frameSize);
				ctrl.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

}
