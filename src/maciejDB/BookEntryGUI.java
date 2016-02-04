package maciejDB;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BookEntryGUI extends JDialog implements BookAttributes {

	// my variables required for MainBookLibrary
	static final String AUTHOR_LAST_NAME = "author last name";
	static final String AUTHOR_FIRST_NAME = "author first name";
	static final String TITLE_FOR_BOOK = "title for book";
	static final String CATEGORY_FOR_BOOK = "cathegory for book";
	static final String RATING_FOR_BOOK = "rating for book";
	static final String SUMMARY_FOR_BOOK = "summary for book";
	static final String ADD_NEW_BOOK_TO_LIBRARY = "add new book to library";
	
	String authorLastNameForBook;
	String authorFirstNameForBook;
	String titleForBook;	
	Category categoryForBook;
	Rating ratingForBook;
	String summaryForBook;
	// flag indicating if there is new book info to be sent
	Boolean addNewBookToLibrary;  
	JButton okButton;
	JButton btnNextBook;
	
	// my variables
	boolean firstNameSet;
	boolean lastNameSet;
	boolean titleSet;
	// end of my variables
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField titleTextField;
	private JComboBox categoryComboBox;
	private JComboBox ratingComboBox; 
	private JTextArea summaryTextArea;
	
	
	
	
	final static Logger loggerBookEntryGUI = Logger.getLogger(BookEntryGUI.class);
	
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BookEntryGUI() {
		initAndRunUI();	
		resetBookParameters();
		loggerBookEntryGUI.trace("BookEntryGUI() runs on Event Dispatching thread: "
				+ SwingUtilities.isEventDispatchThread());
	}		
		
private void initAndRunUI() {		
		setTitle("Muminek Enterprises: BookEnryGUI");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblFirstName = new JLabel("First Name:");
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();			
			firstNameTextField.getDocument().addDocumentListener(new DocumentListener() {
				/**
	             * If the text is changed then this event will be fired.
	             */
				public void changedUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::firstNameTextField::changedUpdateEvent");
					firstNameSet = true;
					buttonsEnabler();	
				}
				/**
	             * If some value is removed then this event is fired.
	             */
	            public void removeUpdate(javax.swing.event.DocumentEvent e) {
	            	loggerBookEntryGUI.debug("::firstNameTextField::removeUpdateEvent");
	            	firstNameSet = true;
					buttonsEnabler();	
	            }
	            /**
	             * If some value is auto set, this event will be called
	             * @param e The value change event
	             */
				@Override
				public void insertUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::firstNameTextField::insertUpdateEvent");
	            	firstNameSet = true;
					buttonsEnabler();	
				}	           				
			});

			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.getDocument().addDocumentListener(new DocumentListener() {
				/**
	             * If the text is changed then this event will be fired.
	             */
				public void changedUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::lastNameTextField::changedUpdateEvent");
					lastNameSet = true;
					buttonsEnabler();	
				}
				/**
	             * If some value is removed then this event is fired.
	             */
	            public void removeUpdate(javax.swing.event.DocumentEvent e) {
	            	loggerBookEntryGUI.debug("::lastNameTextField::removeUpdateEvent");
	            	lastNameSet = true;
					buttonsEnabler();	
	            }
	            /**
	             * If some value is auto set, this event will be called
	             * @param e The value change event
	             */
				@Override
				public void insertUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::lastNameTextField::insertUpdateEvent");
	            	lastNameSet = true;
					buttonsEnabler();	
				}	           				
			});

			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblTitle = new JLabel("Title:");
			contentPanel.add(lblTitle);
		}
		{
			titleTextField = new JTextField();
			titleTextField.getDocument().addDocumentListener(new DocumentListener() {
				/**
	             * If the text is changed then this event will be fired.
	             */
				public void changedUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::titleTextField::changedUpdateEvent");
					titleSet = true;
					buttonsEnabler();	
				}
				/**
	             * If some value is removed then this event is fired.
	             */
	            public void removeUpdate(javax.swing.event.DocumentEvent e) {
	            	loggerBookEntryGUI.debug("::titleTextField::removeUpdateEvent");
	            	titleSet = true;
					buttonsEnabler();	
	            }
	            /**
	             * If some value is auto set, this event will be called
	             * @param e The value change event
	             */
				@Override
				public void insertUpdate(DocumentEvent e) {
					loggerBookEntryGUI.debug("::titleTextField::insertUpdateEvent");
					titleSet = true;
					buttonsEnabler();	
				}	           				
			});
			
			contentPanel.add(titleTextField);
			titleTextField.setColumns(10);
		}
		{
			JLabel lblCategory = new JLabel("Category:");
			contentPanel.add(lblCategory);
		}
		{
			categoryComboBox = new JComboBox();
			// TODO need to set it to default - not set
			categoryComboBox.setModel(new DefaultComboBoxModel(Category.values()));
			
			contentPanel.add(categoryComboBox);
		}
		{
			JLabel lblRating = new JLabel("Rating:");
			contentPanel.add(lblRating);
		}
		{
			ratingComboBox = new JComboBox();
			// TODO need to set it to default - not set
			ratingComboBox.setModel(new DefaultComboBoxModel(Rating.values()));
			contentPanel.add(ratingComboBox);
		}
		{
			JLabel lblSummary = new JLabel("Summary:");
			contentPanel.add(lblSummary);
		}
		{
			summaryTextArea = new JTextArea();
			summaryTextArea.setRows(2);
			summaryTextArea.setColumns(5);
			contentPanel.add(summaryTextArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnNextBook = new JButton("Next Book");
				btnNextBook.setEnabled(false);
				btnNextBook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// design is to pass info from single book and continue to next
						loggerBookEntryGUI.info("::Next Book action");
						collectBookParametes();	
						addNewBookToLibrary = true;
						sentCollectedBookData();
					}
				});
				buttonPane.add(btnNextBook);
			}
			{
				okButton = new JButton("Finished");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// idea is to collect info and exit.... 
						// if more books to enter, Next Book button, not OK
						loggerBookEntryGUI.info("::OK action");
						collectBookParametes();	
						addNewBookToLibrary = true;
						sentCollectedBookData();
						exitProgram();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loggerBookEntryGUI.info("::Cancel action");
						exitProgram();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			{
				JButton NextBookButton = new JButton("OK");
				NextBookButton.setActionCommand("OK");
				getRootPane().setDefaultButton(NextBookButton);
			}
		}
	}

	private void exitProgram() {
		loggerBookEntryGUI.info("::exitProgram->getOwnedWindows has: "+getOwnedWindows().length);
		dispose();
		return;
	}
	private void resetBookParameters() {
		authorLastNameForBook = null;
		authorFirstNameForBook=null;
		titleForBook=null;
		categoryForBook=Category.NotSet;
		ratingForBook=Rating.NotSet;
		summaryForBook=null;
		addNewBookToLibrary=false;
		// now need to reset text fields
		lastNameTextField.setText(authorFirstNameForBook);
		firstNameTextField.setText(authorFirstNameForBook);
		titleTextField.setText(titleForBook);
		ratingComboBox.setSelectedItem(Rating.NotSet);
		summaryTextArea.setText(summaryForBook);
		firstNameSet = false;
		lastNameSet = false;
		titleSet = false;
		buttonsDisabler();
		return;
	}
	
	private void collectBookParametes() {
		authorLastNameForBook = lastNameTextField.getText();
		authorFirstNameForBook = firstNameTextField.getText();
		titleForBook = titleTextField.getText();	
		categoryForBook = (Category) categoryComboBox.getSelectedItem();
		ratingForBook = (Rating) ratingComboBox.getSelectedItem();
		summaryForBook = summaryTextArea.getText();	
		return;
	}
	
	private void sentCollectedBookData() {
		String oldValue = null;
		String nullAuthorLastNameForBook = null;
		Boolean oldAddNewBookToLibrary = false;
		
		firePropertyChange(AUTHOR_LAST_NAME, nullAuthorLastNameForBook, authorLastNameForBook);
		firePropertyChange(AUTHOR_FIRST_NAME, oldValue, authorFirstNameForBook);
		firePropertyChange(TITLE_FOR_BOOK, oldValue, titleForBook);
		firePropertyChange(CATEGORY_FOR_BOOK, oldValue, categoryForBook);
		firePropertyChange(RATING_FOR_BOOK, oldValue, ratingForBook);
		firePropertyChange(SUMMARY_FOR_BOOK, oldValue, summaryForBook);
		// TODO: Since it seems that it fires, even for null values, 
		// need to implement work around: not to fire if key properties are not set
		// so send the flag to specify if book has to be added or not
		if (!authorLastNameForBook.isEmpty() && !authorFirstNameForBook.isEmpty()&& !titleForBook.isEmpty())
			firePropertyChange(ADD_NEW_BOOK_TO_LIBRARY, oldAddNewBookToLibrary, addNewBookToLibrary);
		// after sending all the parameters, need to reset them
		resetBookParameters();
	}
	
	private void buttonsEnabler() {
		if (firstNameSet==true && lastNameSet==true &&titleSet==true) {
			okButton.setEnabled(true);
			btnNextBook.setEnabled(true);
		}
	}
	
	private void buttonsDisabler() {
		okButton.setEnabled(false);
		btnNextBook.setEnabled(false);
	}
}
