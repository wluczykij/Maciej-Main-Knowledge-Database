package maciejDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WelcomeGUI extends JFrame{

	final static Logger logger = Logger.getLogger(WelcomeGUI.class);
	// my variables
	private String welcomeGUIChoice;
	private Boolean isMainBookLibraryGUIActive;
	// end of my variables
	
	private JPanel contentPane;
		
	/**
	 * Create the frame.
	 */
	public WelcomeGUI() {
		welcomeGUIChoice = "";
		isMainBookLibraryGUIActive = false;
		initUI();
		logger.info("WelcomeGUI() runs on Event Dispatching thread: "
				+ SwingUtilities.isEventDispatchThread());
	}
	private void initUI() {
		setTitle("Muminek Enterprises: WelcomeGUI");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblMaciejKnowledge = new JLabel("What would you like to do");
		lblMaciejKnowledge.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMaciejKnowledge, BorderLayout.CENTER);
		
		JLabel lblWelcome = new JLabel("WELCOME to \r\nMaciej Knowledge");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcome, BorderLayout.NORTH);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("::Cancel action");
				exitProgram();
			}
		});
		contentPane.add(btnCancel, BorderLayout.SOUTH);
		
		JComboBox mainChoiceComboBox = new JComboBox();
		
		mainChoiceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Book Database"}));
		mainChoiceComboBox.setToolTipText("");
		contentPane.add(mainChoiceComboBox, BorderLayout.EAST);
		mainChoiceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomeGUIChoice = mainChoiceComboBox.getSelectedItem().toString();
				logger.debug("mainChoiceComboBox: "+welcomeGUIChoice);
				if (welcomeGUIChoice!="Choose") {	
					if ((welcomeGUIChoice=="Book Database") && (isMainBookLibraryGUIActive==false)) {						
						isMainBookLibraryGUIActive = true; 
						// MainBookLibraryInputDialog
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {	
								invokeMainBookLibraryGUI();								
							}
						});
					}				
				}
				
			}
		});
	}
	
	private void invokeMainBookLibraryGUI() {
		MainBookLibraryGUI mainBookLibraryDialog = new MainBookLibraryGUI();
		mainBookLibraryDialog.setVisible(true);
		// register listener with MainBookLibraryGUI
		mainBookLibraryDialog.addPropertyChangeListener(new PropertyChangeListener() {
			// @Override
			public void propertyChange(PropertyChangeEvent pcEvt) {
				// is the property being changed the one we're interested in?
				if (mainBookLibraryDialog.ACTIVE_MAIN_BOOK_LIBRARY_GUI.equals(pcEvt.getPropertyName())) {
					// check if MainBookLibraryGUI is active
					isMainBookLibraryGUIActive = (Boolean) pcEvt.getNewValue();
					// then we can do with it what we want
					logger.trace ("::propertyChange::isMainBookLibraryGUIActive "
							+ isMainBookLibraryGUIActive);
				}
			}			
		});
	}
	
	private void exitProgram() {
		logger.debug("::exitProgram->getOwnedWindows has: "+getOwnedWindows().length);
		
		// for some reason this works on JDialogue as well
		for (Frame f: Frame.getFrames()) {
			f.dispose();
		}
		dispose();
	}
}
