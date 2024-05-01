package com.omar.database3.repositories;

import com.omar.database3.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends CrudRepository<BookEntity, String>,
        PagingAndSortingRepository<BookEntity,String> {
    List<BookEntity> findByAuthorEntityId(Long authorId);

}
