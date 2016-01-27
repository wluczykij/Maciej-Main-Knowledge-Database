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

	final static Logger logger = Logger.getLogger(MainBookLibraryGUI.class);
	
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
		logger.info("MainBookLibraryGUI() runs on Event Dispatching thread: "
				+ SwingUtilities.isEventDispatchThread());
	}
	
	private void resetLibrary() {
		mainBookLibraryNameForMainBookLibrary = null;
		mainBookLibraryChoiceMade = false;
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
		setTitle("Muminek Enterprises");
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						mainBookLibraryNameForMainBookLibrary = mainLibraryChoicetextField.getText();
						if (libraryChoiceComboBoxValue.isEmpty()) {
							lblFeedbackToUser.setText("You did not make any choice");
						}						
						else if (mainBookLibraryNameForMainBookLibrary.isEmpty())
						{
							lblFeedbackToUser.setText("You did not enter book library name");
						}
						else if (mainBookLibraryChoiceMade == false){
							//TODO - creating MainBookLibrary in this thread
							// is questionable
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
						logger.info("::Cancel action");
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
		logger.info("::exitProgram->getOwnedWindows has: "+getOwnedWindows().length);
		
		// for some reason this works on JDialogue as well
		// this below kills all the windows, not just ownedWindows - need to 
		// kill only owned windows
//		for (Frame f: Frame.getFrames()) {
//			f.dispose();
//		}
		dispose();
	}
	
	private void invokeBookEntryGUI() {
		BookEntryGUI BookEntryDialog = new BookEntryGUI();
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
					logger.trace ("::propertyChange::AuthorLastName "
							+ authorLastName);
				}
				else if (BookEntryDialog.AUTHOR_FIRST_NAME.equals(pcEvt.getPropertyName())) {
					// get user input
					authorFirstName = pcEvt.getNewValue().toString();
					logger.trace("::propertyChange::AuthorFirstName "
							+ authorFirstName);
				}
				else if (BookEntryDialog.TITLE_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					title = pcEvt.getNewValue().toString();
					logger.trace("::propertyChange::Title "
							+ title);
				}
				else if (BookEntryDialog.CATEGORY_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					category = (Category) pcEvt.getNewValue();
					logger.trace("::propertyChange::Category "
							+ category);
				}
				else if (BookEntryDialog.RATING_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					rating = (Rating) pcEvt.getNewValue();
					logger.trace("::propertyChange::Rating "
							+ rating);
				}
				else if (BookEntryDialog.SUMMARY_FOR_BOOK.equals(pcEvt.getPropertyName())) {
					// get user input
					summary = pcEvt.getNewValue().toString();
					logger.trace("::propertyChange::Summary "
							+ summary);
				}
				else if (BookEntryDialog.ADD_NEW_BOOK_TO_LIBRARY.equals(pcEvt.getPropertyName())) {
					addNewBookToLibraryFlag = (boolean) pcEvt.getNewValue();
					logger.info("Adding New Book to Library: "+addNewBookToLibraryFlag);
					addNewBookToLibrary();
				}
			}			
		});
	}
	
	private void addNewBookToLibrary() {
		Thread addNewBookToLibrary = new Thread() {
			public void run() {
				logger.info("::addNewBookToLibrary runs of EventDispatchThread: "
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
				}
				// something went wrong, I don't have library created
				else { 
					logger.error("addNewBookToLibrary(): library is null!");
					return;
				}
				//TODO - wrong place to test
				MyBookLibrary.testMainBookLibrary();
			}
		};
		addNewBookToLibrary.start();	
	}
}

