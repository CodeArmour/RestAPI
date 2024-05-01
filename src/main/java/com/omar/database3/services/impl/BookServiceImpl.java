package com.omar.database3.services.impl;

import com.omar.database3.domain.entities.BookEntity;
import com.omar.database3.repositories.BooksRepo;
import com.omar.database3.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class BookServiceImpl implements BookService {

    private BooksRepo booksRepo;

    public BookServiceImpl(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }


    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return booksRepo.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport
                .stream(booksRepo
                        .findAll()
                        .spliterator(),FALSE)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return booksRepo.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
       return booksRepo.findById(isbn);
    }

    @Override
    public List<BookEntity> findByAuthorId(Long authorId) {
        return booksRepo.findByAuthorEntityId(authorId);
    }

    @Override
    public boolean isExsits(String isbn) {
        return booksRepo.existsById(isbn);
    }

    @Override
    public BookEntity patialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return  booksRepo.findById(isbn).map(existingbook ->{
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingbook::setTitle);
            return booksRepo.save(existingbook);

        }).orElseThrow(()->new RuntimeException("Book Dose Not Exist"));
    }

    @Override
    public void delete(String isbn) {
        booksRepo.deleteById(isbn);
    }

}
