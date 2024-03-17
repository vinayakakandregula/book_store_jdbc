package book_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import book_store.dto.Book;

public class BookCrud {

	public void createTable() throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true", "root", "root");
		Statement statement = con.createStatement();
		statement.execute(
				"create table book(id int primary key,name varchar(45),author varchar(45),price double,language varchar(45))");
		con.close();
	}

	public void saveTable(Book book) throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?)");
		ps.setInt(1, book.getId());
		ps.setString(2, book.getName());
		ps.setString(3, book.getAuthor());
		ps.setDouble(4, book.getPrice());
		ps.setString(5, book.getLanguage());
		ps.execute();
		ps.close();
		con.close();
	}

	public void update(double prize) throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("update book set prize = ?");
		ps.setDouble(1, prize);
		ps.execute();
		ps.close();
		con.close();
	}

	public void delete(int id) throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("delete from book where id=?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		con.close();
		System.out.println("The record is deleted : " + id);
	}

	public void display() throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from book");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4)
					+ " " + rs.getString(5));
		}
		s.close();
		con.close();
	}
	public void check(String book) throws Throwable {
		int bprize=0,walletAmount=personWallet();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from book");
		while (rs.next()) {
			if(book.equals(rs.getString(2))){
				bprize = rs.getInt(4);
				int ch = walletAmount - bprize;
				PreparedStatement s1 = con.prepareStatement("update person set wallet=?");
				s1.setInt(1, ch);
				s1.execute();
			}
		}
	}
	public void updateAmount(double amt) throws Throwable{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("update person set wallet=?");
		ps.setDouble(1, amt);
		ps.execute();
		ps.close();
		con.close();
	}
	public int personWallet() throws Throwable {
		int walletAmount=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		Statement s = con.createStatement();
		ResultSet rs1 = s.executeQuery("select * from person");
		while(rs1.next()) {
			walletAmount=rs1.getInt(1);
		}
		return walletAmount;
	}
}
