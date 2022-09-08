package com.project.libraryms.repos;


import com.project.libraryms.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT DISTINCT a FROM Author a INNER JOIN FETCH a.books i WHERE i.id=:id")
    Iterable<Author> findAuthorByItems_Id(@Param("id") Long id);
    //public List<Author> getAuthors();

}
