package maciejDB;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class MyUtilitiesTest {

	@Test
	// test save and get string from file in one method, makes sense
	public void saveStringToFile() {
		String saveString = "this is test line one\n" +
							"this is test line two \n";
		
		File testFile = new File("testsavetostring.txt");
		testFile.delete();
		assertFalse("File should not exist", testFile.exists());
		
		assertTrue("File should have been saved", 
				MyUtilities.saveStringToFile("testsavestring.txt", 
						saveString));
		
		String newString = 
					MyUtilities.getStringFromFile("testsavestring.txt");
		
		assertTrue("Save and get strings should be equal", 
				saveString.equals(newString));
		
		// this will fail because of non-existant directory
		assertFalse("File should not be saved", 
				MyUtilities.saveStringToFile(
						"Non-existent dir/thisshouldfail.txt",
				saveString));
		
		String emptyString = MyUtilities.getStringFromFile("badfilename.txt");
		assertTrue("String should be empty", emptyString.length() == 0);				
	}

	public MainBookLibrary createMyLibrary() {
		// create a new MainKnowledge
		MainBookLibrary tmn = new MainBookLibrary("My Utilities Test Library");
		Book b1 = new Book();
		Book b2 = new Book();
		b2.setAuthorLastName("Palczak"); 
	
		tmn.addBook(b1);
		tmn.addBook(b2);
	
		return tmn;
	}
	
	@Test
	public void convertToFromXML() {
		MainBookLibrary startMyLibrary = createMyLibrary();
		String testXMLOut = MyUtilities.convertToXML(startMyLibrary);
		MainBookLibrary endMyLibrary = MyUtilities.convertFromXML(testXMLOut);
		
		assertEquals("My Utilities Test Library", 
											endMyLibrary.getMainBookLibraryName());
		assertEquals(2, endMyLibrary.getMainBookLibraryBooks().size());
		assertEquals("Palczak", 
		endMyLibrary.getMainBookLibraryBooks().get(1).getAuthorLastName());
	}
	
	@Test
	public void saveGetToFromXMLFile() {
		MainBookLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.xml";
		File testFile = new File(fileName); 
		testFile.delete();
		assertFalse("File should not exist", testFile.exists());
		assertTrue("File should have been saved", 
				MyUtilities.saveMyLibraryToXMLFlie(fileName, startMyLibrary));
		
		MainBookLibrary endMyLibrary = MyUtilities.getMyLibraryFromXMLFile(fileName);
		assertEquals("My Utilities Test Library", 
							endMyLibrary.getMainBookLibraryName());
		assertEquals(2, endMyLibrary.getMainBookLibraryBooks().size());
		assertEquals("Palczak", 
				endMyLibrary.getMainBookLibraryBooks().get(1).getAuthorLastName());		
	}
	
	@Test
	public void saveGetToFromSerialFile() {
		MainBookLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.ser";
		File testFile = new File(fileName); 
		testFile.delete();
		assertFalse("File should not exist", testFile.exists());
		assertTrue("File should have been saved", 
				MyUtilities.
				saveMyLibraryToSerialFlie(fileName, startMyLibrary));
		
		MainBookLibrary endMyLibrary = MyUtilities.
				getMyLibraryFromSerialFile(fileName);
		assertEquals("My Utilities Test Library", 
							endMyLibrary.getMainBookLibraryName());
		assertEquals(2, endMyLibrary.getMainBookLibraryBooks().size());
		assertEquals("Palczak", 
				endMyLibrary.getMainBookLibraryBooks().get(1).getAuthorLastName());
		
	}
}
