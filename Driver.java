
//Country Government chooser
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

public class Driver {

	public static void main(String[] args) {

		DialogHelper.makeDialogsEasierToSee(33);

		// capture size of screen we're using
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Define the size of the JFrame as a square that is a percentage of the
		// available screen area and a multiple of 100.
		final int frameHeight = (int) (screenSize.height * 85.00 / 100) / 100 * 100;
		final int frameWidth = frameHeight;

		Dimension frameSize = new Dimension(frameWidth, frameHeight);
		System.out.format("frame width=%d, height=%d%n", frameWidth, frameHeight);
		
		
		EventQueue.invokeLater(() -> {
		    Controller ctrl = new Controller(frameSize);
			ctrl.start();
		});


	}

}
