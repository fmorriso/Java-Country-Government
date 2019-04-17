import javafx.application.Platform;
//Country Government chooser
import javafx.embed.swing.JFXPanel;

public class Driver {

	public static void main(String[] args) {
		
		DialogHelper.makeDialogsEasierToSee(32);
		
		// fake out Java to avoid the dreaded "Toolkit not initialized" error:
		new JFXPanel(); // this will prepare JavaFX toolkit and environment
		
		Platform.runLater(() -> {
			try {
				Controller ctrl = new Controller();
				ctrl.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

}
