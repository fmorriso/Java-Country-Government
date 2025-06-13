
//Country Government chooser
import java.awt.Dimension;

public class Driver {

	public static void main(String[] args) {
		String javaVersion = getJavaVersion();
		System.out.format("Using Java version %s%n", javaVersion);

		DialogHelper.makeDialogsEasierToSee(22);

		Dimension frameSize = SwingScreenUtilities.getScaledSize(.45, 100, true);
		int frameWidth = frameSize.width;
		int frameHeight = frameSize.height;
		System.out.format("frame width=%d, height=%d%n", frameWidth, frameHeight);

		javax.swing.SwingUtilities.invokeLater(() -> {
		    new Controller(frameSize, javaVersion);
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
