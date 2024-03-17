package batchexecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import book_store.dto.Book;

public class BookBatch {
	public static void main(String[] args) throws Throwable {
		List<Book> list = new ArrayList<Book>();
		list.add(new Book(2, "html", "tim", 100, "English"));
		list.add(new Book(3, "CSS", "lee", 150, "english"));
		list.add(new Book(4, "Javascript", "lie", 200, "korean"));
		
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
	}
}
