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
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	
	
	private final JPanel contentPanelFlow = new JPanel();
	private final JPanel contentPanelGridBag1 = new JPanel();
	private final JPanel contentPanelGridBag2 = new JPanel();

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
		setBounds(100, 100, 606, 441);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanelFlow.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelFlow, BorderLayout.CENTER);
		contentPanelFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{ //TODO - not sure if this parenthesis should be here!!!
			contentPanelFlow.add(contentPanelGridBag1);
			GridBagLayout gbl_contentPanel1 = new GridBagLayout();
//			gbl_contentPanel1.columnWidths = new int[]{64, 0, 61, 56, 58, 5, 101, 16, 26, 0};
//			gbl_contentPanel1.rowHeights = new int[]{28, 28, 44, 0};
//			gbl_contentPanel1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//			gbl_contentPanel1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			
			gbl_contentPanel1.columnWidths = new int[]{64, 0, 61, 56, 58, 5, 101, 16, 26, 0};
			gbl_contentPanel1.rowHeights = new int[]{28, 28, 0};
			gbl_contentPanel1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};			
			
			
			contentPanelGridBag1.setLayout(gbl_contentPanel1);
			{
				{
					JLabel lblFirstName = new JLabel("First Name:");
					GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
					gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
					gbc_lblFirstName.gridx = 1;
					gbc_lblFirstName.gridy = 0;
					gbc_lblFirstName.gridwidth = 1;
					contentPanelGridBag1.add(lblFirstName, gbc_lblFirstName);
				}
				
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
				firstNameTextField.setColumns(10);
				GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
				gbc_firstNameTextField.anchor = GridBagConstraints.NORTHWEST;
				gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_firstNameTextField.gridwidth = 2;
				gbc_firstNameTextField.gridx = 2;
				gbc_firstNameTextField.gridy = 0;
				gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
				contentPanelGridBag1.add(firstNameTextField, gbc_firstNameTextField);				
			}
			contentPanelFlow.add(contentPanelGridBag2);
			GridBagLayout gbl_contentPanel2 = new GridBagLayout();
			gbl_contentPanel2.columnWidths = new int[]{64, 0, 61, 56, 58, 5, 101, 16, 26, 0};
			gbl_contentPanel2.rowHeights = new int[]{28, 28, 44, 0};
			gbl_contentPanel2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPanelGridBag2.setLayout(gbl_contentPanel2);
			{
				{
					JLabel lblLastName = new JLabel("Last Name:");
					GridBagConstraints gbc_lblLastName = new GridBagConstraints();
					gbc_lblLastName.anchor = GridBagConstraints.EAST;
					gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
					gbc_lblLastName.gridwidth = 1;
					gbc_lblLastName.gridx = 1;
					gbc_lblLastName.gridy = 0;
					contentPanelGridBag2.add(lblLastName, gbc_lblLastName);
				}			
			}
			
			
			
			
			
			
//			{
//				JLabel lblFirstName = new JLabel("First Name:");
//				contentPanelFlow.add(lblFirstName);
//			}
//			
//			firstNameTextField = new JTextField();			
//			firstNameTextField.getDocument().addDocumentListener(new DocumentListener() {
//				/**
//	             * If the text is changed then this event will be fired.
//	             */
//				public void changedUpdate(DocumentEvent e) {
//					loggerBookEntryGUI.debug("::firstNameTextField::changedUpdateEvent");
//					firstNameSet = true;
//					buttonsEnabler();	
//				}
//				/**
//	             * If some value is removed then this event is fired.
//	             */
//	            public void removeUpdate(javax.swing.event.DocumentEvent e) {
//	            	loggerBookEntryGUI.debug("::firstNameTextField::removeUpdateEvent");
//	            	firstNameSet = true;
//					buttonsEnabler();	
//	            }
//	            /**
//	             * If some value is auto set, this event will be called
//	             * @param e The value change event
//	             */
//				@Override
//				public void insertUpdate(DocumentEvent e) {
//					loggerBookEntryGUI.debug("::firstNameTextField::insertUpdateEvent");
//	            	firstNameSet = true;
//					buttonsEnabler();	
//				}	           				
//			});
//			firstNameTextField.setColumns(10);
//			GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
//			gbc_firstNameTextField.anchor = GridBagConstraints.NORTHWEST;
//			gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
//			gbc_firstNameTextField.gridwidth = 2;
//			gbc_firstNameTextField.gridx = 2;
//			gbc_firstNameTextField.gridy = 0;
//			gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
//			contentPanel.add(firstNameTextField, gbc_firstNameTextField);
//			
//			{
//				JLabel lblLastName = new JLabel("Last Name:");
//				GridBagConstraints gbc_lblLastName = new GridBagConstraints();
//				gbc_lblLastName.anchor = GridBagConstraints.EAST;
//				gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
//				gbc_lblLastName.gridwidth = 1;
//				gbc_lblLastName.gridx = 4;
//				gbc_lblLastName.gridy = 0;
//				contentPanel.add(lblLastName, gbc_lblLastName);
//			}
//			
//			lastNameTextField = new JTextField();
//			lastNameTextField.getDocument().addDocumentListener(new DocumentListener() {
//				/**
//	             * If the text is changed then this event will be fired.
//	             */
//				public void changedUpdate(DocumentEvent e) {
//					loggerBookEntryGUI.debug("::lastNameTextField::changedUpdateEvent");
//					lastNameSet = true;
//					buttonsEnabler();	
//				}
//				/**
//	             * If some value is removed then this event is fired.
//	             */
//	            public void removeUpdate(javax.swing.event.DocumentEvent e) {
//	            	loggerBookEntryGUI.debug("::lastNameTextField::removeUpdateEvent");
//	            	lastNameSet = true;
//					buttonsEnabler();	
//	            }
//	            /**
//	             * If some value is auto set, this event will be called
//	             * @param e The value change event
//	             */
//				@Override
//				public void insertUpdate(DocumentEvent e) {
//					loggerBookEntryGUI.debug("::lastNameTextField::insertUpdateEvent");
//	            	lastNameSet = true;
//					buttonsEnabler();	
//				}	           				
//			});
//			lastNameTextField.setColumns(10);
//			GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
//			gbc_lastNameTextField.anchor = GridBagConstraints.NORTHWEST;
//			gbc_lastNameTextField.insets = new Insets(0, 0, 5, 5);
//			gbc_lastNameTextField.gridwidth = 2;
//			gbc_lastNameTextField.gridx = 5;
//			gbc_lastNameTextField.gridy = 0;
//			gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
//			contentPanel.add(lastNameTextField, gbc_lastNameTextField);			
//			
//			{
//				JLabel lblTitle = new JLabel("Title:");
//				GridBagConstraints gbc_lblTitle = new GridBagConstraints();
//				gbc_lblTitle.anchor = GridBagConstraints.WEST;
//				gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
//				gbc_lblTitle.gridx = 1;
//				gbc_lblTitle.gridy = 1;
//				contentPanel.add(lblTitle, gbc_lblTitle);
//			}
//			{
//				titleTextField = new JTextField();
//				titleTextField.getDocument().addDocumentListener(new DocumentListener() {
//					/**
//		             * If the text is changed then this event will be fired.
//		             */
//					public void changedUpdate(DocumentEvent e) {
//						loggerBookEntryGUI.debug("::titleTextField::changedUpdateEvent");
//						titleSet = true;
//						buttonsEnabler();	
//					}
//					/**
//		             * If some value is removed then this event is fired.
//		             */
//		            public void removeUpdate(javax.swing.event.DocumentEvent e) {
//		            	loggerBookEntryGUI.debug("::titleTextField::removeUpdateEvent");
//		            	titleSet = true;
//						buttonsEnabler();	
//		            }
//		            /**
//		             * If some value is auto set, this event will be called
//		             * @param e The value change event
//		             */
//					@Override
//					public void insertUpdate(DocumentEvent e) {
//						loggerBookEntryGUI.debug("::titleTextField::insertUpdateEvent");
//						titleSet = true;
//						buttonsEnabler();	
//					}	           				
//				});
//				titleTextField.setColumns(10);
//				GridBagConstraints gbc_titleTextField = new GridBagConstraints();
//				gbc_titleTextField.anchor = GridBagConstraints.NORTHEAST;
//				gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
//				gbc_titleTextField.gridwidth = 2;
//				gbc_titleTextField.gridx = 2;
//				gbc_titleTextField.gridy = 1;
//				gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
//				contentPanel.add(titleTextField, gbc_titleTextField);
//			}			
//			
//			{
//				JLabel lblCategory = new JLabel("Category:");
//				GridBagConstraints gbc_lblCategory = new GridBagConstraints();
//				gbc_lblCategory.anchor = GridBagConstraints.WEST;
//				gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
//				gbc_lblCategory.gridx = 1;
//				gbc_lblCategory.gridy = 2;
//				contentPanel.add(lblCategory, gbc_lblCategory);
//			}
//			{
//				categoryComboBox = new JComboBox();
//				// TODO need to set it to default - not set
//				categoryComboBox.setModel(new DefaultComboBoxModel(Category.values()));
//				GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
////				gbc_categoryComboBox.anchor = GridBagConstraints.WEST;
//				gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
//				gbc_categoryComboBox.gridwidth = 1;
//				gbc_categoryComboBox.gridx = 3;
//				gbc_categoryComboBox.gridy = 2;
//				contentPanel.add(categoryComboBox, gbc_categoryComboBox);
//			}			
//			{
//				JLabel lblRating = new JLabel("Rating:");
//				GridBagConstraints gbc_lblRating = new GridBagConstraints();
//				gbc_lblRating.anchor = GridBagConstraints.WEST;
//				gbc_lblRating.insets = new Insets(0, 0, 5, 0);
//				gbc_lblRating.gridwidth = 2;
//				gbc_lblRating.gridx = 2;
//				gbc_lblRating.gridy = 2;
//				contentPanel.add(lblRating, gbc_lblRating);
//			}
//			{
//				ratingComboBox = new JComboBox();
//				// TODO need to set it to default - not set
//				ratingComboBox.setModel(new DefaultComboBoxModel(Rating.values()));
//				GridBagConstraints gbc_ratingComboBox = new GridBagConstraints();
//				gbc_ratingComboBox.anchor = GridBagConstraints.EAST;
//				gbc_ratingComboBox.insets = new Insets(0, 0, 0, 5);
//				gbc_ratingComboBox.gridwidth = 2;
//				gbc_ratingComboBox.gridx = 2;
//				gbc_ratingComboBox.gridy = 3;
//				contentPanel.add(ratingComboBox, gbc_ratingComboBox);
//			}			
//			{
//				JLabel lblSummary = new JLabel("Summary:");
//				GridBagConstraints gbc_lblSummary = new GridBagConstraints();
//				gbc_lblSummary.anchor = GridBagConstraints.EAST;
//				gbc_lblSummary.insets = new Insets(0, 0, 0, 5);
//				gbc_lblSummary.gridx = 3;
//				gbc_lblSummary.gridy = 0;
//				contentPanel.add(lblSummary, gbc_lblSummary);
//			}
//			{
//				summaryTextArea = new JTextArea();
//				summaryTextArea.setRows(2);
//				summaryTextArea.setColumns(5);
//				GridBagConstraints gbc_summaryTextArea = new GridBagConstraints();
//				gbc_summaryTextArea.anchor = GridBagConstraints.NORTHWEST;
//				gbc_summaryTextArea.insets = new Insets(0, 0, 0, 5);
//				gbc_summaryTextArea.gridwidth = 2;
//				gbc_summaryTextArea.gridx = 3;
//				gbc_summaryTextArea.gridy = 1;
//				contentPanel.add(summaryTextArea, gbc_summaryTextArea);
//			}
//			{
//			}			
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
		loggerBookEntryGUI.trace("::exitProgram->getOwnedWindows has: "+getOwnedWindows().length);
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
