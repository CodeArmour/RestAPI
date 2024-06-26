package com.omar.database3.mappers.impl;

import com.omar.database3.domain.dto.BookDto;
import com.omar.database3.domain.entities.BookEntity;
import com.omar.database3.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

  private final ModelMapper modelMapper;

  public BookMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public BookDto mapTo(BookEntity book) {
    return modelMapper.map(book, BookDto.class);
  }

  @Override
  public BookEntity mapFrom(BookDto bookDto) {
    return modelMapper.map(bookDto, BookEntity.class);
  }
}
