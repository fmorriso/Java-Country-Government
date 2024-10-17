
//Country Government chooser
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

public class Driver {

	public static void main(String[] args) {

		DialogHelper.makeDialogsEasierToSee(33);

		Dimension frameSize = SwingScreenUtilities.getScaledSize(.55, 100, true);
		int frameWidth = frameSize.width;
		int frameHeight = frameSize.height;
		System.out.format("frame width=%d, height=%d%n", frameWidth, frameHeight);

		javax.swing.SwingUtilities.invokeLater(() -> {
		    new Controller(frameSize);
		});
	}

	/** get the java version that is running the current program
	 * @return string containing the java version running the current program
	 */
	private static String getJavaVersion()
	{
		Runtime.Version rtv = Runtime.version();
		return String.format("%s.%s.%s.%s", rtv.feature(), rtv.interim(), rtv.update(), rtv.patch());
	}

}
