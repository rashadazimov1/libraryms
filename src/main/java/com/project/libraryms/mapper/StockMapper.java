package com.project.libraryms.mapper;

import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.entities.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDTO stockEntityToDto(Stock stock);

    Stock stockDtoToEntity(StockDTO stockDTO);

    List<StockDTO> toDtoList(List<Stock> stocks);
}
