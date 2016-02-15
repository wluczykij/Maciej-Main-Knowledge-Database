package maciejDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.log4j.Logger;

import maciejDB.BookAttributes.Category;
import maciejDB.BookAttributes.Rating;
import org.apache.log4j.*;
	
public class Book implements BookAttributes, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7351096138471467939L;
	final static Logger logBook = Logger.getLogger(Book.class);
		
	protected String authorLastName;
	protected String authorFirstName;
	protected String title;	
	protected Category category;
	protected Rating rating;
	protected String summary;
	
	Book() {
		logBook.trace("::Book()");
		title = "not set";
		authorLastName = "not set";
		authorFirstName = "not set";
		summary = "not set";
		category = Category.NotSet;
		rating = Rating.NotSet;
		
	}
	
	Book(String inputAuthorLastName,
		String inputAuthorFirstName,
		String inputTitle,
		Category inputCategory,
		Rating inputRating,
		String inputSummary) {
		
		logBook.trace("::Book Copy Constructor");
		authorLastName = inputAuthorLastName;
		authorFirstName = inputAuthorFirstName;
		title = inputTitle;
		category = inputCategory;
		rating = inputRating;
		summary = inputSummary;
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorFirstName() {
		return authorFirstName;		
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;		
	}

	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;		
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category setCategory) {
		this.category = setCategory;
	}		
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating setRating) {
		this.rating = setRating;
	}
	
	@Override
	public String toString() {
		return this.getTitle() +"; " +this.getAuthorLastName() +"; " +
				this.getAuthorFirstName() +"; " +this.getSummary() +"; " +
				this.getCategory() +"; " +this.getRating() +"; ";		
	}
}
