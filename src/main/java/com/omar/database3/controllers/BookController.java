package com.omar.database3.controllers;

import com.omar.database3.domain.dto.BookDto;
import com.omar.database3.domain.entities.BookEntity;
import com.omar.database3.mappers.Mapper;
import com.omar.database3.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {

  private final Mapper<BookEntity, BookDto> bookMapper;
  private final BookService bookService;

  public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
    this.bookMapper = bookMapper;
    this.bookService = bookService;
  }

  @PutMapping("/books/{isbn}")
  public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
    BookEntity bookEntity = bookMapper.mapFrom(bookDto);
    boolean bookExists = bookService.isExsits(isbn);
    BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
    BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

    if (bookExists) {
      return new ResponseEntity<BookDto>(savedBookDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<BookDto>(savedBookDto, HttpStatus.CREATED);
    }
  }

  @GetMapping(path = "/books")
  public Page<BookDto> listBook(Pageable pageable) {
    Page<BookEntity> books = bookService.findAll(pageable);
    return books.map(bookMapper::mapTo);
  }

  @GetMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> findOne(@PathVariable("isbn") String isbn) {
    Optional<BookEntity> foundBook = bookService.findOne(isbn);
    return foundBook.map(bookEntity -> {
      BookDto bookDto = bookMapper.mapTo(bookEntity);
      return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/books/authors/{authorId}")
  public Page<BookDto> listBooksByAuthor(@PathVariable("authorId") Long authorId, Pageable pageable) {
    Page<BookEntity> booksByAuthor = bookService.findByAuthorId(authorId, pageable);
    return booksByAuthor.map(bookMapper::mapTo);
  }


  @PatchMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> partialUpdate(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
    BookEntity bookEntity = bookMapper.mapFrom(bookDto);
    boolean bookExists = bookService.isExsits(isbn);

    if (!bookService.isExsits(isbn)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    BookEntity updatedBookEntity = bookService.patialUpdate(isbn, bookEntity);
    return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
  }

  @DeleteMapping(path = "/books/{isbn}")
  public ResponseEntity<BookDto> delete(@PathVariable("isbn") String isbn) {
    bookService.delete(isbn);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
