package com.omar.database3.services.impl;


import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.repositories.AuthorsRepo;
import com.omar.database3.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Boolean.FALSE;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorsRepo authorsRepo;

  public AuthorServiceImpl(AuthorsRepo authorsRepo) {
    this.authorsRepo = authorsRepo;
  }

  @Override
  public AuthorEntity save(AuthorEntity authorEntity) {
    return authorsRepo.save(authorEntity);
  }

  @Override
  public List<AuthorEntity> findAll() {
    return StreamSupport
      .stream(authorsRepo
                .findAll()
                .spliterator(), FALSE)
      .collect(Collectors.toList());
  }

  @Override
  public Optional<AuthorEntity> findOne(Long id) {
    return authorsRepo.findById(id);
  }

  @Override
  public boolean isExists(Long id) {
    return authorsRepo.existsById(id);
  }

  @Override
  public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
    authorEntity.setId(id);

    return authorsRepo.findById(id).map(existingAuthor -> {
      Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
      Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
      return authorsRepo.save(existingAuthor);
    }).orElseThrow(() -> new RuntimeException("Author Dose Not Exist"));
  }

  @Override
  public void delete(Long id) {
    authorsRepo.deleteById(id);
  }
}
