package com.aos.repo.mapper;

import com.aos.repo.entity.Book;
import com.aos.repo.vo.BookVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookVOMapper {

  BookVOMapper MAPPER = Mappers.getMapper(BookVOMapper.class);

  BookVO toBookVO(Book book);
}
