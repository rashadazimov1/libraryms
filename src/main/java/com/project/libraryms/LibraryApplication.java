package com.project.libraryms;

import com.project.libraryms.entities.*;
import com.project.libraryms.repos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository, UserRepository userRepository, BookRentRepository bookRentRepository, StockRepository stockRepository, CategoryRepository categoryRepository) {
		return args -> {
			if (bookRepository.count() == 0 && authorRepository.count() == 0 && userRepository.count() == 0 && bookRentRepository.count() == 0 && stockRepository.count() == 0 && categoryRepository.count() == 0) {
				Book book1 = new Book("ABC123NNM", "Xemse", " Nizami genecevini bes poemasi xemsesi");
				Book book2 = new Book("RNM999THE", "	Leyli ve 	MECNUN", "Mehemed fuzulini poemasi..");
				Book book3 = new Book("AAA000BBB", "Sefiller", "Vikto Huqonun Sefiller Romani...");
				Book book4 = new Book("BAR444OBA", "Tom Soyerin maceralari", "Mark Tven TomSoyyerin Maceralari...");
				Book book5 = new Book("OVE555LEV", "Yeddi Gozel", "Mizami Gencevini Yeddi Gozel eseri xemseden  ...");

				Author author1 = new Author("Nizami Gencevi", "1141/08/04");
				Author author2 = new Author("Mehemmed Fuzuli", "1209/02/17");
				Author author3 = new Author("Viktor Huqo", "1934/03/09");
				Author author4 = new Author("Mark Tven", "1962/04/01");


				User user1 = new User("Rashad azimov", "rashad1", "234/12/11", "Baku", "test1@gmail.com", "12345");
				User user2 = new User("Rashad azimov", "rashad2", "234/12/11", "lankaran", "test2@gmail.com", "12345");
				User user3 = new User("Rashad azimov", "rashad3", "234/12/11", "Quba", "test3@gmail.com", "12345");


				book1.getAuthors().add(author3);
				book1.getAuthors().add(author2);
				book2.getAuthors().add(author2);
				book4.getAuthors().add(author1);
				book3.getAuthors().add(author4);
				book5.getAuthors().add(author3);

				author3.getBooks().add(book1);
				author2.getBooks().add(book1);
				author2.getBooks().add(book2);
				author1.getBooks().add(book4);
				author4.getBooks().add(book3);
				author3.getBooks().add(book5);


				bookRepository.save(book1);
				bookRepository.save(book2);
				bookRepository.save(book3);
				bookRepository.save(book4);
				bookRepository.save(book5);

				userRepository.save(user1);
				userRepository.save(user2);
				userRepository.save(user3);

				bookRentRepository.save(new BookRent("2022-09-02", "2022-10-20", true, false, user1, book1));
				bookRentRepository.save(new BookRent("2022-09-03", "2022-10-21", true, false, user1, book2));
				bookRentRepository.save(new BookRent("2022-09-02", "2022-10-20", true, false, user3, book5));


				stockRepository.save(new Stock(20, book1));
				stockRepository.save(new Stock(15, book2));
				stockRepository.save(new Stock(30, book3));
				stockRepository.save(new Stock(8,  book4));
				stockRepository.save(new Stock(16, book5));

				categoryRepository.save(new Category(Categories.POYEMA, book1));
				categoryRepository.save(new Category(Categories.DASTAN, book2));
				categoryRepository.save(new Category(Categories.POVEST, book3));
				categoryRepository.save(new Category(Categories.ROMAN, book4));
				categoryRepository.save(new Category(Categories.ROMAN, book5));
			}
		};


	}
}