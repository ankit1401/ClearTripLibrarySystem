package libraryclasses;

public class Book {
	private String title;
	private String author;
	private int count;
	public Book(String title, String author, int count) {
		super();
		this.title = title;
		this.author = author;
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
