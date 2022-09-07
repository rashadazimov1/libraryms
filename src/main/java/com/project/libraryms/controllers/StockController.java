package com.project.libraryms.controllers;


import com.project.libraryms.dto.dto.QuantityDto;
import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.entities.Stock;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.service.impl.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {


    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @PutMapping("/id/{id}")
    public Stock updateLogin(@RequestBody QuantityDto quantityToJson, @PathVariable Long id) {
        return stockService.updateStockQuantity(quantityToJson.getQuantity(), id);
    }


    @GetMapping("/all")
    public List<StockDTO> getAllBooksStocks(){
        return stockService.getAllBooksStocks();
    }


    @GetMapping("/id/{id}")
    public Stock getStockByBookId(@PathVariable Long id){
        Stock stock = stockService.getStockByBookId(id);
        if (stock != null){
            return stock;
        }else {
            throw new NotFoundException("Item not found with id :" + id);
        }
    }
}
