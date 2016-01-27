package maciejDB;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class MainBookLibraryTest {

	private Book b1;
	private Book b2;
	private MainBookLibrary mbl;

	public void addItems() {
		mbl.addBook(b1);
		mbl.addBook(b2);
	}
	
	public void setup() {
		b1 = new Book();
		b2 = new Book();
		mbl = new MainBookLibrary("Test");
	}

	@Test
	public void testAddRemoveBook() {
		//create test objects
		setup();		
		// test initial size
		assertEquals(0, mbl.getMainBookLibraryBooks().size());
		
		mbl.addBook(b1);
		mbl.addBook(b2);
		
		assertEquals(2, mbl.getMainBookLibraryBooks().size());
		
		assertEquals(0, mbl.getMainBookLibraryBooks().indexOf(b1));
		assertEquals(1, mbl.getMainBookLibraryBooks().indexOf(b2));

		mbl.removeBook(b1);
		
		assertEquals(1, mbl.getMainBookLibraryBooks().size());
		assertEquals(0, mbl.getMainBookLibraryBooks().indexOf(b2));
		
		mbl.removeBook(b2);
		assertEquals(0, mbl.getMainBookLibraryBooks().size());		
	}

	@Test
	public void testMyLibrary() {
		// test constructor
		MainBookLibrary mbl = new MainBookLibrary("Test Library");
		assertEquals("Test Library", mbl.libraryName);
		
		assertTrue(mbl.books instanceof ArrayList);
	}
	
	@Test
	public void testToString() {
		setup();
		addItems();
		assertEquals("Test: 2 books;", mbl.toString());
	}
}


