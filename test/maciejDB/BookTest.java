package maciejDB;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest implements BookAttributes {

	@Test
	public void testBook() {
		Book b1 = new Book();
		assertEquals("not set", b1.title);
		assertEquals("not set", b1.authorLastName);
		assertEquals("not set", b1.authorFirstName);		
		assertEquals("not set", b1.summary);
  		assertEquals("NotSet", b1.category.toString());
		assertEquals("NotSet", b1.rating.toString());	
	}

	@Test
	public void  testGetSetTitle() {
		Book b2 = new Book();
		b2.setTitle("Tee Cycki");
		String tempTitle = b2.getTitle();
		assertEquals("Tee Cycki", tempTitle);
	}
	
	@Test
	public void  testGetSetAuthorLastName() {
		Book b3 = new Book();
		b3.setAuthorLastName("Palczak");
		assertEquals("Palczak", b3.getAuthorLastName());
	}
	
	@Test
	public void  testGetSetAuthorFirstName() {
		Book b4 = new Book();
		b4.setAuthorFirstName("Maciej");
		assertEquals("Maciej", b4.getAuthorFirstName());
	}
	
	@Test
	public void  testGetSetSummary() {
		Book b5 = new Book();
		b5.setSummary("She is hot");
		assertEquals("She is hot", b5.getSummary());
	}

	@Test
	public void  testGetSetCategory() {
		Book b6 = new Book();
		b6.setCategory(Category.FamilyAndRelationships);
		assertEquals("FamilyAndRelationships", b6.getCategory().toString());
	}
	
	@Test
	public void  testGetSetRating() {
		Book b7 = new Book();
		b7.setRating(Rating.Excellent);
		assertEquals("Excellent", b7.getRating().toString());
	}
	
	@Test
	public void testToString() {
		Book b8 = new Book();
		b8.setTitle("Life of Tee");
		b8.setAuthorLastName("Palczak");
		b8.setAuthorFirstName("Maciej");
		b8.setSummary("It is special");
		b8.setCategory(Category.Adventures);
		b8.setRating(Rating.Excellent);
		assertEquals("Life of Tee; Palczak; Maciej; It is special; Adventures; Excellent; ", 
				b8.toString());
		
		
	}
	
}
