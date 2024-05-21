package com.omar.database3.repositories;

import com.omar.database3.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends CrudRepository<BookEntity, String>,
  PagingAndSortingRepository<BookEntity, String> {
  Page<BookEntity> findByAuthorEntityId(Long authorId, Pageable pageable);

}
