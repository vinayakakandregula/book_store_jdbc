package book_store_controller;

import java.util.Scanner;

import book_store.dao.BookCrud;
import book_store.dto.Book;

public class Book_Store_Main {

	public static void main(String[] args) throws Throwable {
		BookCrud bookCrud = new BookCrud();
//		bookCrud.createTable();
//		System.out.println(book);
//		bookCrud.saveTable(book);
		System.out.println("Welcome to Bookstore..!");
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		do {
			System.out.println("1.book \n2.user \n3.exit");
			int select = sc.nextInt();
			boolean check1 = true;
			switch (select) {
			case 1: {
				do {
					System.out.println("1.insert\n2.update\n3.delete\n4.exit");
					int select1 = sc.nextInt();
					switch (select1) {
					case 1: {
						System.out.println("Enter an Id : ");
						int id = sc.nextInt();
						System.out.println("Enter an Name : ");
						String name = sc.next();
						System.out.println("Enter an Author : ");
						String author = sc.next();
						System.out.println("Enter price : ");
						double price = sc.nextDouble();
						System.out.println("Enter language : ");
						String language = sc.next();
						Book book = new Book(id, name, author, price, language);
						bookCrud.saveTable(book);
						break;
					}
					case 2: {
						System.out.println("Enter an updating prize : ");
						double prize = sc.nextDouble();
						bookCrud.update(prize);
						break;
					}
					case 3: {
						System.out.println("Enter an id for delete the record : ");
						int id = sc.nextInt();
						bookCrud.delete(id);
						break;
					}
					case 4: {
						check1 = false;
					}
					}
				} while (check1);
				break;
			}
			case 2: {
				boolean check3 = true;
				do {
					System.out.println("1.Buy a book\n2.Update Wallet Amount\n3.Exit");
					int key = sc.nextInt();
					switch (key) {
					case 1:
						boolean check2 = false;
						do {
							bookCrud.display();
							System.out.println("Select the book : ");
							String book = sc.next();
							bookCrud.check(book);
							int amount = bookCrud.personWallet();
							System.out.println("You are succesfully the buyed the "+book+" book");
							System.out.println("Remaining Amount : "+amount);
							System.out.println("If u want buy again enter 1 otherwise 2 : ");
							int again = sc.nextInt();
							if (again == 1) {
								check2 = true;
							} else {
								check2 = false;
							}
						} while (check2);
						break;

					case 2: {
						System.out.println("Enter the Amount : ");
						double amt = sc.nextDouble();
						bookCrud.updateAmount(amt);
						break;
					}
					case 3: {
						check3 = false;
					}
					}
				} while (check3);
				break;
			}
			case 3: {
				check = false;

			}
			}
		} while (check);
	}

}
