package batchexecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import book_store.dto.Book;

public class Delete {
	public static void main(String[] args) throws Throwable {
		List<Book> list = new ArrayList<Book>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
		PreparedStatement ps = con.prepareStatement("delete from book where id =?");
		for(Book book : list) {
			ps.setInt(1,book.getId());
			ps.addBatch();
			System.out.println("added");
		}
		ps.executeBatch();
		ps.close();
		con.close();
		System.out.println("done..!");
	}
}
