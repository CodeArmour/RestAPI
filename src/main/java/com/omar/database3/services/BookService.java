package com.omar.database3.services;

import com.omar.database3.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
  List<BookEntity> findAll();

  Page<BookEntity> findAll(Pageable pageable);

  BookEntity createUpdateBook(String isbn, BookEntity book);

  Optional<BookEntity> findOne(String isbn);

  boolean isExsits(String isbn);

  BookEntity patialUpdate(String isbn, BookEntity bookEntity);

  void delete(String isbn);

  Page<BookEntity> findByAuthorId(Long authorId, Pageable pageable);
}
