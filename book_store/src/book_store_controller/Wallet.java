package book_store_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Wallet {
	public static void main(String[] args) throws Throwable {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","root");
		Statement s = con.createStatement();
		s.execute("insert into person values(1000)");
		s.close();
		con.close();
	}
}
