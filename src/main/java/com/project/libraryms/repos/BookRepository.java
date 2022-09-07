package com.project.libraryms.repos;

import com.project.libraryms.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

//	@Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
//	public List<Book> search(String keyword);
	//Optional<Book> findByName(String name);


	@Query("SELECT i FROM Book i where i.title like %:title%")
	Iterable<Book> findBookByTitle(@Param("title") String title);

	@Query("SELECT DISTINCT i FROM Book i INNER JOIN FETCH i.authors a WHERE a.fullName LIKE %:fullName%")
	Iterable<Book> findItemByAuthorName(@Param("fullName") String fullName);

	Book findBookByBarCode(String barCode);

	Book findBookById(Long id);


}
