package batchexecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import book_store.dto.Book;

public class DynamicBookBatch {
	public static void main(String[] args) throws Throwable {
		Scanner sc = new Scanner(System.in);
		List<Book> list = new ArrayList<Book>();
		boolean choice = true;
		do {
			System.out.println("Enter an id : ");
			int id = sc.nextInt();
			System.out.println("Enter an name : ");
			String  name = sc.next();
			System.out.println("Enter an author : ");
			String  author = sc.next();
			System.out.println("Enter an price : ");
			double price = sc.nextDouble();
			System.out.println("Enter an lang : ");
			String  lang = sc.next();
			list.add(new Book(id, name, author, price, lang));
			System.out.println("Are you willing to add another book if Yes enter 1 otherwise enter 2 : ");
			int n = sc.nextInt();
			if(n==1) {
				choice = true;
			} else {
				choice = false;
			}
		} while (choice);	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
		PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?)");
		for(Book book : list) {
			ps.setInt(1, book.getId());
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			ps.setDouble(4, book.getPrice());
			ps.setString(5, book.getLanguage());
			ps.addBatch();
			System.out.println("added");
		}
		ps.executeBatch();
		ps.close();
		con.close();
		System.out.println("done..!");
		sc.close();
	}
}
