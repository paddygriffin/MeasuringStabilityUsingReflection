package ie.gmit.sw;


/**
 * @author Patrick griffin G00314635
 *
 */
public class TestRun {
	/**
	 * @param args
	 * Starts the program
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppWindow();
			}
		});
	}
}
