package libraryclasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class User {
	private String name;
	private Map<Book,Integer> listOfBooks=new HashMap<Book,Integer>();
	private int max_limit_for_book_count;
	private int totalBookCount=1;
	
	public User(String name, Map<Book, Integer> listOfBooks, int max_limit_for_book_count) {
		super();
		this.name = name;
		this.listOfBooks = listOfBooks;
		this.max_limit_for_book_count = max_limit_for_book_count;
	}
	
	
	
	public User(String name, int max_limit_for_book_count) {
		super();
		this.name = name;
		this.max_limit_for_book_count = max_limit_for_book_count;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Book, Integer> getListOfBooks() {
		return listOfBooks;
	}
	public void setListOfBooks(Map<Book, Integer> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	public int getMax_limit_for_book_count() {
		return max_limit_for_book_count;
	}
	public void setMax_limit_for_book_count(int max_limit_for_book_count) {
		this.max_limit_for_book_count = max_limit_for_book_count;
	}
	public boolean issueBook(Book book,Library library) {
		library.registerUserToLibrary(this);
		if(totalBookCount>max_limit_for_book_count)
			return false;
		System.out.println(totalBookCount+" "+max_limit_for_book_count);
		if(listOfBooks.containsKey(book)){
			int bookCount=listOfBooks.get(book);
			if(bookCount+1<=max_limit_for_book_count){
				listOfBooks.put(book, bookCount+1);
				totalBookCount=totalBookCount+1;
				return true;
			}
			System.out.println("Book count exceeded the max limit for the user!!!");
			return false;
		}
		listOfBooks.put(book, 1);
		totalBookCount=totalBookCount+1;
		return true;
		
	}
	public boolean returnBook(Book book,Library library) {
		library.registerUserToLibrary(this);
		if(listOfBooks.containsKey(book)){
			int bookCount=listOfBooks.get(book);
			if(bookCount-1==0){
				listOfBooks.remove(book);
				totalBookCount=totalBookCount-1;
				return true;
			}
			listOfBooks.put(book, bookCount-1);
			totalBookCount=totalBookCount-1;
			return true;
		}
		return false;
	}
	
	public boolean issueBook(Book book,Library library,int count) {
		library.registerUserToLibrary(this);
		if(listOfBooks.containsKey(book)){
			int bookCount=listOfBooks.get(book);
			if(bookCount+count<=max_limit_for_book_count){
				listOfBooks.put(book, bookCount+count);
				return true;
			}
			System.out.println("Book count exceeded the max limit for the user!!!");
			return false;
		}
		listOfBooks.put(book, count);
		return true;
		
	}
	public boolean returnBook(Book book,Library library,int count) {
		library.registerUserToLibrary(this);
		if(listOfBooks.containsKey(book)){
			int bookCount=listOfBooks.get(book);
			if(bookCount-count<=0){
				listOfBooks.remove(book);
				return true;
			}
			listOfBooks.put(book, bookCount-count);
			return true;
		}
		return false;
	}
	
	public void printListOfBooks(){
		for(Entry<Book,Integer> entry:listOfBooks.entrySet()){
			System.out.println("Book "+entry.getKey().getTitle());
		}
	}
	
}
