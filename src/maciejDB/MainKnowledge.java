package maciejDB;

import java.io.Serializable;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.*;

public class MainKnowledge implements Serializable {
	final static Logger logMainKnowledge = Logger.getLogger(MainKnowledge.class);
	//TODO - can't figure out how to set logging levels per file instead of per project
	//logMainKnowledge.setLevel(Level.debug);
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
	
		//TODO: disabled until I decide if I want to be able to access shared
		// memory, etc from console
		// MyUtilities.cli();
		logMainKnowledge.debug("main:: running");
	} // end of main
}
		




