package maciejDB;

import java.io.Serializable;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

public class MainKnowledge implements Serializable {
	final static Logger logger = Logger.getLogger(MainKnowledge.class);
	String mainChoice; 
	public MainKnowledge() {
		mainChoice = "Choose";
	}
			
	public static void main(String[] args) {
		
		MainKnowledge myKnowledge = new MainKnowledge();
		
		SwingUtilities.invokeLater (new Runnable() {
			@Override
			public void run() {
				WelcomeGUI welcomeJFrame = new WelcomeGUI();
			
				// Start the input
				welcomeJFrame.setVisible(true);
			}
		});
		logger.info("MainKnowledge::main:: exits");
	} // end of main
}
		




