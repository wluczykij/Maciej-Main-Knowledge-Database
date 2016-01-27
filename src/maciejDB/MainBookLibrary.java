package maciejDB;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class MainBookLibrary implements BookAttributes,Serializable {
	/**
	 * 
	 */
	final static Logger logger = Logger.getLogger(MainBookLibrary.class);
	private static final long serialVersionUID = -5451146017846456157L;
	String libraryName;
	ArrayList<Book> books;	
	Book b;
	
	MainBookLibrary(String inputLibraryName) {
		logger.info("constructor, library name: "+inputLibraryName);
		this.libraryName = inputLibraryName;
		books = new ArrayList<Book>();
	}
	
	public String getMainBookLibraryName() {
		return libraryName;
	}

	public ArrayList<Book> getMainBookLibraryBooks() {
		return books;
	}

	public void addBook(Book b1) {
		this.books.add(b1);
		
	}

	public void removeBook(Book b1) {
		this.books.remove(b1);
		
	}
	
	public void addBookToLibrary (String inputAuthorLastName, 
								  String inputAuthorFirstName,
								  String inputTitle,
								  Category inputCategory,
								  Rating inputRating,
								  String inputSummary) {
		
		this.addBook(createBookEntry(inputAuthorLastName, 
									inputAuthorFirstName, 
									inputTitle, 
									inputCategory, 
									inputRating, 
									inputSummary));
		logger.info("addBookToLibrary - Book added");		
	}
	
	public Book createBookEntry(String inputAuthorLastName, 
								String inputAuthorFirstName,
								String inputTitle,
								Category inputCategory,
								Rating inputRating,
								String inputSummary) {
		b = new Book(inputAuthorLastName, 
							inputAuthorFirstName,
							inputTitle,
							inputCategory,	
							inputRating,
							inputSummary);				
		return (b);
	}
	
	@Override
	public String toString() {
		return this.getMainBookLibraryName() + 
		": " + this.getMainBookLibraryBooks().size() + " books;"; 
	}
	
	public void testMainBookLibrary() {
		logger.info("::testMainBookLibrary");
		
		System.out.println("Just Created New Main Library");
		this.printStatus();
		
		MyUtilities.saveMyLibraryToXMLFlie("testMain.xml", this);
		
		MainBookLibrary newMyLibrary = MyUtilities.
				getMyLibraryFromXMLFile("testMain.xml");
		System.out.println("Printing information from saved Main xml file");
		newMyLibrary.printStatus();
		
		System.out.println("Saving Main Library to Serial File");
		MyUtilities.saveMyLibraryToSerialFlie("testMain.ser", this);

		MainBookLibrary newFromSerialLibrary = MyUtilities.
				getMyLibraryFromSerialFile("testMain.ser");
		System.out.println("Printing information from saved Main ser file");
		newFromSerialLibrary.printStatus();
	}
	
	void printStatus() {
		System.out.println("Status Report of MainBookLibrary: \n" + 
				this.toString());
		
		for (Book thisBook : this.getMainBookLibraryBooks()) {
			System.out.println(thisBook);
		}
		System.out.println("--- End of Status Report---\n");
	}
}


