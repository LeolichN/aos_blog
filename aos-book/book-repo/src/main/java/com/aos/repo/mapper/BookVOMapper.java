package com.aos.repo.mapper;

import com.aos.repo.dto.BookOperateDTO;
import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookVOMapper {

  BookVOMapper MAPPER = Mappers.getMapper(BookVOMapper.class);

  BookVO toBookVO(Book book);

  Book toBook(BookOperateDTO operateDTO);
}
