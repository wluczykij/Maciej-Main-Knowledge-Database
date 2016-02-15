package maciejDB;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class MainBookLibraryGUI extends JDialog implements BookAttributes {

	final static Logger loggerMainBookLibraryGUI = Logger.getLogger(MainBookLibraryGUI.class);
	
	// my variables required for MainBookLibrary
	private String mainBookLibraryNameForMainBookLibrary;
	static final String ACTIVE_MAIN_BOOK_LIBRARY_GUI = "active main book library gui";

	// my variables required for Single Book
	String authorLastName;
	String authorFirstName;
	String title;
	Category category;
	Rating rating;
	String summary;
	
	// my variables
	private String libraryChoiceComboBoxValue;
	private boolean mainBookLibraryChoiceMade;
	private boolean addNewBookToLibraryFlag;
	MainBookLibrary MyBookLibrary;
	Boolean activeMainBookLibraryGUI; // indicates if this GUI window is active (up)
	BookEntryGUI BookEntryDialog;		// reference to the BookEntryGUI child object
	
	// end of my variables

	private final JPanel contentPanel = new JPanel();
	private JTextField mainLibraryChoicetextField;
	private JLabel lblEnterNewLibrary;
	private JLabel lblFeedbackToUser;
	private boolean mainBookLibraryGUIInputExit; //exit from here, regardless if anything set or not


	/**
	 * Create the dialog.
	 */
	public MainBookLibraryGUI() {
		resetLibrary();
		resetBook();
		mainBookLibraryGUIInputExit = false; 
		libraryChoiceComboBoxValue = null;
		activeMainBookLibraryGUI = true; //window is up
		initAndRunUI();
		loggerMainBookLibraryGUI.trace("MainBookLibraryGUI() runs on Event Dispatching thread: "
				+ SwingUtilities.isEventDispatchThread());
	}
	
	private void resetLibrary() {
		mainBookLibraryNameForMainBookLibrary = null;
		mainBookLibraryChoiceMade = false;
		// reset child window - BookEntryGUI
		BookEntryDialog = null;
	}
	
	private void resetBook() {		
		authorLastName = null;
		authorFirstName = null;
		title = null;
		category = Category.NotSet;
		rating = Rating.NotSet;
		summary = null;
		addNewBookToLibraryFlag = false;
		//TODO: this is later to do, in case if I want to create new book library
		// this would require another option - create another book library..
		//TODO: also need to add another text field, but read only where I display current library
		// name
		// mainLibraryChoicetextField.setText(mainBookLibraryNameForMainBookLibrary);		
	}

	private void initAndRunUI() {
		setTitle("Muminek Enterprises: MainBookLibraryGUI");
		setBounds(100, 100, 450, 300);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JComboBox libraryChoicesComboBox = new JComboBox();
			libraryChoicesComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					libraryChoiceComboBoxValue = libraryChoicesComboBox.getSelectedItem().toString();
					switch (libraryChoiceComboBoxValue) {
						case "Choose":
							break;
						case "Create New Book Database": {
							lblFeedbackToUser.setText("");
							lblEnterNewLibrary.setVisible(true);
							mainLibraryChoicetextField.setVisible(true);		
							break;
						}
						default: 
							break;
					}
				}
			});
			libraryChoicesComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Create New Book Database"}));
			contentPanel.add(libraryChoicesComboBox);
		}
		{
			lblEnterNewLibrary = new JLabel("Enter New Library Name");
			lblEnterNewLibrary.setVisible(false);
			lblFeedbackToUser = new JLabel("");
			contentPanel.add(lblEnterNewLibrary);
			contentPanel.add(lblFeedbackToUser);
		}
		{
			mainLibraryChoicetextField = new JTextField();
			contentPanel.add(mainLibraryChoicetextField);
			mainLibraryChoicetextField.setColumns(10);
			mainLibraryChoicetextField.setVisible(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validateOKAction() == true) {
							loggerMainBookLibraryGUI.info("::Create action");
							mainBookLibraryNameForMainBookLibrary = mainLibraryChoicetextField.getText();
								mainBookLibraryChoiceMade = true;
								MyBookLibrary = new MainBookLibrary(mainBookLibraryNameForMainBookLibrary);
								lblFeedbackToUser.setText("");
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										invokeBookEntryGUI();
									}
								});
							}
					} //end of actionPerformed()
				}); // end of ActionListener()
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainBookLibraryGUIInputExit = true;
						loggerMainBookLibraryGUI.info("::Cancel action");
						// need to pass info to the parent (WelcomeGUI.java), that window is closing
						sendInfoToParentGUI();
						exitProgram();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void sendInfoToParentGUI() {
		// for now I have to hardcode it in order to fire, I use it only when closing the window
		Boolean localActiveMainBookLibraryGUI = true;
		activeMainBookLibraryGUI = false;
		firePropertyChange(ACTIVE_MAIN_BOOK_LIBRARY_GUI, localActiveMainBookLibraryGUI, activeMainBookLibraryGUI);
	}
	private void exitProgram() {
		loggerMainBookLibraryGUI.trace("::exitProgram");

		if (BookEntryDialog!=null) {
			BookEntryDialog.dispose();
		}	
		dispose();
	}
	
	private void invokeBookEntryGUI() {
		BookEntryDialog = new BookEntryGUI();
		BookEntryDialog.setVisible(true);

		// adding the listener, according to this:
		// http://stackoverflow.com/questions/12398887/access-static-variable-from-another-class/12406454#12406454
		// this allows MainBookLibraryGUI to listen to BookEntryDialog
		BookEntryDialog.addPropertyChangeListener(new PropertyChangeListener() {
			// @Override
			public void propertyChange(PropertyChangeEvent pcEvt) {
				// is the property being changed the one we're interested in?
				if (BookEntryDialog.AUTHOR_LAST_NAME.equals(pcEvt.getPropertyName())) {
					// get user input:
					authorLastName = pcEvt.getNewValue().toString();

					// then we can do with it what we want
					loggerMainBookLibraryGUI.trace ("::propertyChange::AuthorLastName "
							+ authorLastName);
				}
				else if (BookEntryDialog.AUTHOR_FIRST_NAME.equals(pcEvt.getPropertyName())) {
					// get user input
					authorFirstName = pcEvt.getNewValue().toString();
					loggerMainBookLibraryGUI.trace("::propertyChange::AuthorFirstName "
							+ authorFirstName);
				}
				else if (BookEntryDialog.TITLE_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					title = pcEvt.getNewValue().toString();
					loggerMainBookLibraryGUI.trace("::propertyChange::Title "
							+ title);
				}
				else if (BookEntryDialog.CATEGORY_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					category = (Category) pcEvt.getNewValue();
					loggerMainBookLibraryGUI.trace("::propertyChange::Category "
							+ category);
				}
				else if (BookEntryDialog.RATING_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					rating = (Rating) pcEvt.getNewValue();
					loggerMainBookLibraryGUI.trace("::propertyChange::Rating "
							+ rating);
				}
				else if (BookEntryDialog.SUMMARY_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					summary = pcEvt.getNewValue().toString();
					loggerMainBookLibraryGUI.trace("::propertyChange::Summary "
							+ summary);
				}
				else if (BookEntryDialog.ADD_NEW_BOOK_TO_LIBRARY.equals(pcEvt.getPropertyName())) {
					addNewBookToLibraryFlag = (boolean) pcEvt.getNewValue();
					loggerMainBookLibraryGUI.info("Adding New Book to Library: "+addNewBookToLibraryFlag);
					addNewBookToLibraryTask();
				}
			}			
		});
	}
	
	private void addNewBookToLibraryTask() {
		//this method runs on a normal thread
		Thread addNewBookToLibraryThread = new Thread() {
			public void run() {
				loggerMainBookLibraryGUI.trace("::addNewBookToLibrary runs of EventDispatchThread: "
						+ SwingUtilities.isEventDispatchThread());
				if (MyBookLibrary != null) {
					MyBookLibrary.addBookToLibrary(authorLastName,
													authorFirstName,
													title,
													category,
													rating,
													summary);
					//TODO - this printStatus() is temporarily for debugging
					MyBookLibrary.printStatus();
					MyUtilities.saveMyLibraryToXMLFile(mainBookLibraryNameForMainBookLibrary, MyBookLibrary);
				}
				// something went wrong, I don't have library created
				else { 
					loggerMainBookLibraryGUI.error("addNewBookToLibrary(): library is null!");
					return;
				}
				//TODO - wrong place to test
				// MyBookLibrary.testMainBookLibrary();
			}
		};
		addNewBookToLibraryThread.start();	
	}
	
	private boolean validateOKAction() {
	/* 
	 - can not press OK without choosing what to do
	 - must specify the main library name
	 - mainBookLibraryChoiceMade must be false - that means it is new book library, not the used one
	 */
		boolean returnValue = false;
		// can not press OK without choosing what to do
		if ((libraryChoiceComboBoxValue == null) || (libraryChoiceComboBoxValue.isEmpty()) || (libraryChoiceComboBoxValue == "Choose")) {
			lblFeedbackToUser.setText("You have to make a choice");
		} else if (mainLibraryChoicetextField.getText().isEmpty()) {
				lblFeedbackToUser.setText("You did not enter book library name");			
			}
			else if (mainBookLibraryChoiceMade == true) {
				lblFeedbackToUser.setText("Library is already created");	
			}
			else
				returnValue = true; 

		return returnValue;
	}
}


