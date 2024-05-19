package com.omar.database3.repositories;

import com.omar.database3.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepo extends CrudRepository<AuthorEntity, Long> {
}
