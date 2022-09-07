package com.project.libraryms.repos;

import com.project.libraryms.entities.Stock;
import com.project.libraryms.dto.dto.StockDTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query("SELECT new com.project.libraryms.dto.dto.StockDTO( s.id, s.quantity, i.barCode, i.title) FROM Stock s INNER JOIN s.book i WHERE i.title like %:title%")
    List<StockDTO> findBookStockByTitle(String title);

    @Query("SELECT new com.project.libraryms.dto.dto.StockDTO( s.id, s.quantity, i.barCode, i.title) FROM Stock s INNER JOIN s.book i")
    List<StockDTO> findAllBooksStocks();

    @Query("SELECT new com.project.libraryms.dto.dto.StockDTO( s.id, s.quantity, i.barCode, i.title) FROM Stock s INNER JOIN s.book i INNER JOIN i.authors a WHERE a.fullName LIKE %:fullName%")
    List<StockDTO> findBooksStockByAuthorName(String fullName);

    @Query("SELECT DISTINCT s FROM Stock s INNER JOIN FETCH s.book i WHERE i.id=:id")
    Stock findStockByBookId(@Param("id") Long id);

}
