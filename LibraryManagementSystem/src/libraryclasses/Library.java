package libraryclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Library {
	private int totalNumberOfBooks;
	private Map<String,User> listOfRegisteredUsers=new HashMap<String,User>();
	private Map<Book,List<User>> listOfIssuedBooks=new HashMap<Book,List<User>>();
	private Map<String,Book> listOfUnIssuedBooks=new HashMap<String,Book>();
	
	public Library(int totalNumberOfBooks, Map<String, User> listOfRegisteredUsers, Map<Book, List<User>> listOfIssuedBooks,
			Map<String, Book> listOfUnIssuedBooks) {
		super();
		this.totalNumberOfBooks = totalNumberOfBooks;
		this.listOfRegisteredUsers = listOfRegisteredUsers;
		this.listOfIssuedBooks = listOfIssuedBooks;
		this.listOfUnIssuedBooks = listOfUnIssuedBooks;
	}
	
	
	public Library() {
		super();
		this.totalNumberOfBooks = 0;
	}


	public int getTotalNumberOfBooks() {
		return totalNumberOfBooks;
	}
	public void setTotalNumberOfBooks(int totalNumberOfBooks) {
		this.totalNumberOfBooks = totalNumberOfBooks;
	}
	public Map<String, User> getListOfRegisteredUsers() {
		return listOfRegisteredUsers;
	}
	public void setListOfRegisteredUsers(Map<String, User> listOfRegisteredUsers) {
		this.listOfRegisteredUsers = listOfRegisteredUsers;
	}
	public Map<Book, List<User>> getListOfIssuedBooks() {
		return listOfIssuedBooks;
	}
	public void setListOfIssuedBooks(Map<Book, List<User>> listOfIssuedBooks) {
		this.listOfIssuedBooks = listOfIssuedBooks;
	}
	public Map<String, Book> getListOfUnIssuedBooks() {
		return listOfUnIssuedBooks;
	}
	public void setListOfUnIssuedBooks(Map<String, Book> listOfUnIssuedBooks) {
		this.listOfUnIssuedBooks = listOfUnIssuedBooks;
	}
	
	public void registerUserToLibrary(User user){
		User checkUser=listOfRegisteredUsers.get(user.getName());
		if(checkUser==null){
			listOfRegisteredUsers.put(user.getName(), user);
			System.out.println("User is successfully registered to the Library");
		}
		else{
			System.out.println("User already registered to the Library");
		}
	}
	
	public void addBookToLibrary(Book book){
		Book checkBook=listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor());
		if(checkBook==null){
			listOfUnIssuedBooks.put(book.getTitle()+book.getAuthor(), book);
			totalNumberOfBooks=totalNumberOfBooks+1;
			System.out.println("Book is successfully added to the Library");
		}
		else{
			System.out.println("Book already added to the Library");
		}
	}
	
	public boolean issueBookToUser(User user,Book book){
		if(listOfRegisteredUsers.get(user.getName())==null || listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor())==null){
			System.out.println("Book not avaliable in the library");
			return false;
		}
		
		User issuer=listOfRegisteredUsers.get(user.getName());
		boolean issueBook=issuer.issueBook(book,this);
		if(issueBook){
			listOfRegisteredUsers.put(user.getName(),issuer);
			Book unIssuedBook=listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor());
			if(unIssuedBook.getCount()-1==0){
				listOfUnIssuedBooks.remove(book.getTitle()+book.getAuthor());
			}
			else{
				unIssuedBook.setCount(unIssuedBook.getCount()-1);
				listOfUnIssuedBooks.put(book.getTitle()+book.getAuthor(),unIssuedBook);
			}
			totalNumberOfBooks=totalNumberOfBooks-1;
			if(listOfIssuedBooks.get(book)==null){
				ArrayList<User> listOfIssuers=new ArrayList<User>();
				listOfIssuers.add(issuer);
				listOfIssuedBooks.put(book, listOfIssuers);
				System.out.println("Book "+book.getTitle()+" is successfully issued by the user "+user.getName());
				return true;
			}
			else{
				ArrayList<User> listOfIssuers=(ArrayList<User>) listOfIssuedBooks.get(book);
				if(listOfIssuers.contains(user)){
					System.out.println("Book "+book.getTitle()+"is already issued by user "+user.getName());
					return true;
				}
				listOfIssuers.add(user);
				listOfIssuedBooks.put(book, listOfIssuers);
				System.out.println("Book "+book.getTitle()+" is successfully issued by the user "+user.getName());
				return true;
			}	
		}
		else{
				System.out.println("Book "+book.getTitle()+" could not be issued by the user "+user.getName());
				return false;
		}
		
		
	}
	
	public boolean returnBookToLibrary(User user,Book book){
		if(listOfRegisteredUsers.get(user.getName())==null){
			return false;
		}
		User issuer=listOfRegisteredUsers.get(user.getName());
		boolean returnBook=issuer.returnBook(book,this);
		if(returnBook){
			listOfRegisteredUsers.put(user.getName(),issuer);
			if(listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor())==null){
				book.setCount(1);
				listOfUnIssuedBooks.put(book.getTitle()+book.getAuthor(),book);
			}
			else{
				Book unissued=listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor());
				unissued.setCount(unissued.getCount()+1);
				listOfUnIssuedBooks.put(book.getTitle()+book.getAuthor(),unissued);
			}
			totalNumberOfBooks=totalNumberOfBooks+1;
			ArrayList<User> listOfIssuers=(ArrayList<User>) listOfIssuedBooks.get(book);
			if(listOfIssuers.contains(user)){
				listOfIssuers.remove(user);
				if(listOfIssuers.size()==0){
					listOfIssuers.remove(book);
				}
				else{
					listOfIssuedBooks.put(book, listOfIssuers);
				}
				System.out.println("Book "+book.getTitle()+" is successfully returned by the user "+user.getName());
				return true;
			}
			return true;
			
		}
		else{
			System.out.println("Book "+book.getTitle()+" could not be returned by the user "+user.getName());
		}
		return false;
		
	}
	
	public void printIssuedBooks(){
		for(Entry<Book, List<User>> entry: listOfIssuedBooks.entrySet()){
			System.out.println("Book title is "+entry.getKey().getTitle()+" Book Author is "+entry.getKey().getAuthor());
		}
	}
	
	public void printUnIssuedBooks(){
		for(Entry<String, Book> entry: listOfUnIssuedBooks.entrySet()){
			System.out.println("Book title is "+entry.getValue().getTitle()+" Book Author is "+entry.getValue().getAuthor()+" Book count is "+entry.getValue().getCount());
		}
	}
	
	public void printRegisteredUsers(){
		for(Entry<String, User> entry: listOfRegisteredUsers.entrySet()){
			System.out.println("The registered user is "+entry.getValue().getName());
		}
	}
	
	public void searchUserInLibraryByName(User user){
		if(listOfRegisteredUsers.get(user.getName())!=null){
			System.out.println("User "+user.getName()+" found in the Library");
		}
		System.out.println("User "+user.getName()+" is not registered in the Library");
	}
	
	public void searchBookInLibrary(Book book){
		if(listOfUnIssuedBooks.get(book.getTitle()+book.getAuthor())!=null){
			System.out.println("Book "+book.getTitle()+" found in the Library");
		}
		System.out.println("Book "+book.getTitle()+" is not found in the Library");
	}
	
}
