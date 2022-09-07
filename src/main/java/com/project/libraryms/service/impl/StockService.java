package com.project.libraryms.service.impl;

import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.entities.Stock;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.repos.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {
    @Autowired
     private StockRepository stockRepository;

    @CachePut(value = "stock", key = "#id")
    public Stock createStock(Stock stock){
        return stockRepository.save(stock);
    }
    @CacheEvict(value = "stock", allEntries = true)

    public Stock updateStockQuantity(int newQuantity, Long id){
        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setQuantity(newQuantity);
                    return stockRepository.save(stock);
                })
                .orElseThrow(() -> new NotFoundException("Stock not found with id :" + id)
                );
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<StockDTO> getBookStockByTitle(String title){
        return stockRepository.findBookStockByTitle(title);
    }
    @Cacheable(value = "stock")
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<StockDTO> getAllBooksStocks(){
        return stockRepository.findAllBooksStocks();
    }
    public List<StockDTO> findBookStockByAuthorName(String fullName){
        return stockRepository.findBooksStockByAuthorName(fullName);
    }
    @CacheEvict(value = "stock", allEntries = true)
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Stock getStockByBookId(Long id){
        return stockRepository.findStockByBookId(id);
    }
}
