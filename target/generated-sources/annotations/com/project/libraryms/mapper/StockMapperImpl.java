package com.project.libraryms.mapper;

import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.entities.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:05:30+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class StockMapperImpl implements StockMapper {

    @Override
    public StockDTO stockEntityToDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDTO stockDTO = new StockDTO();

        stockDTO.setId( stock.getId() );
        stockDTO.setQuantity( stock.getQuantity() );

        return stockDTO;
    }

    @Override
    public Stock stockDtoToEntity(StockDTO stockDTO) {
        if ( stockDTO == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setId( stockDTO.getId() );
        stock.setQuantity( stockDTO.getQuantity() );

        return stock;
    }

    @Override
    public List<StockDTO> toDtoList(List<Stock> stocks) {
        if ( stocks == null ) {
            return null;
        }

        List<StockDTO> list = new ArrayList<StockDTO>( stocks.size() );
        for ( Stock stock : stocks ) {
            list.add( stockEntityToDto( stock ) );
        }

        return list;
    }
}
