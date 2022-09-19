package com.project.libraryms.serviceimpl;

import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.entities.Stock;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.mapper.StockMapper;
import com.project.libraryms.repos.StockRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @CachePut(value = "stock", key = "#id")
    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setQuantity(stockDTO.getQuantity());
        stock.setBook(stock.getBook());
        stockRepository.save(stock);
        return stockMapper.stockEntityToDto(stock);
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
    public StockDTO getStockByBookId(Long id) {
        return stockRepository.findStockByBookId(id);
    }
}
