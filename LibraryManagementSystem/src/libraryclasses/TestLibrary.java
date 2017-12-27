package libraryclasses;

public class TestLibrary {
	public static void main(String[] args) {
		Library library=new Library();
		User user1=new User("ankit",2);
		User user2=new User("raj",2);
		User user3=new User("rahul",2);
		Book book1=new Book("Jungle book","Siddharth gupta",5);
		Book book2=new Book("Harry Potter","J K rowling",3);
		Book book3=new Book("Hello ABC", "Merry Halo",3);
		Book book4=new Book("Hello C", "Dennis Ritchie",3);
		library.registerUserToLibrary(user1);
		library.registerUserToLibrary(user2);
		library.registerUserToLibrary(user3);
		library.addBookToLibrary(book1);
		library.addBookToLibrary(book2);
		library.addBookToLibrary(book3);
		library.addBookToLibrary(book4);
		
		library.issueBookToUser(user1, book2);
		library.issueBookToUser(user1, book1);
		
		
		library.issueBookToUser(user2, book1);
		library.issueBookToUser(user2, book3);
		
		library.issueBookToUser(user3, book1);
		library.issueBookToUser(user3, book3);
		
		System.out.println("The registered users of library are ");
		library.printRegisteredUsers();
		System.out.println("The issued books are ");
		library.printIssuedBooks();
		System.out.println("The unissued books with count are ");
		library.printUnIssuedBooks();
		
		System.out.println("List of books issued by user 1");
		user1.printListOfBooks();
		System.out.println("List of books issued by user 2");
		user2.printListOfBooks();
		System.out.println("List of books issued by user 3");
		user3.printListOfBooks();
		
		library.issueBookToUser(user1, book4);
		
		library.returnBookToLibrary(user1, book2);
		
	}
	
}
