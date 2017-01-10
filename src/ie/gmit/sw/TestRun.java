package ie.gmit.sw;


/**
 * @author Patrick griffin G00314635
 * 
 * The Main Application runner class
 */
public class TestRun {
	/**
	 * @param args
	 * Starts the program GUI
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppWindow();
			}
		});
	}
}
