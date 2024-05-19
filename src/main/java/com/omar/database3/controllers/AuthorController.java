package com.omar.database3.controllers;


import com.omar.database3.domain.dto.AuthorDto;
import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.mappers.Mapper;
import com.omar.database3.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

  @SuppressWarnings("checkstyle:JavadocVariable")
  private final AuthorService authorService;

  @SuppressWarnings("checkstyle:JavadocVariable")
  private final Mapper<AuthorEntity, AuthorDto> authorMapper;

  @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:MissingJavadocMethod"})
  public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
    this.authorService = authorService;
    this.authorMapper = authorMapper;
  }

  //@RequestBody : look at the http post request body for the authorEntity object represented as json
  //               it'll convert it into java.
  @SuppressWarnings({"checkstyle:LineLength", "checkstyle:WhitespaceAround", "checkstyle:FinalParameters",
    "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @PostMapping(path = "/authors")
  public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
    AuthorEntity authorEntity = authorMapper.mapFrom(author);
    AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
    return new ResponseEntity<AuthorDto>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
  }

  @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @GetMapping(path = "/authors")
  public List<AuthorDto> listAuthor() {
    List<AuthorEntity> authors = authorService.findAll();
    return authors.stream()
      .map(authorMapper::mapTo)
      .collect(Collectors.toList());
  }

  @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:WhitespaceAfter", "checkstyle:FinalParameters",
    "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @GetMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
    Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
    return foundAuthor.map(authorEntity -> {
      AuthorDto authorDto = authorMapper.mapTo(authorEntity);
      return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @SuppressWarnings({"checkstyle:WhitespaceAfter", "checkstyle:LineLength", "checkstyle:WhitespaceAround", "checkstyle:FinalParameters",
    "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @PutMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
    if (!authorService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    authorDto.setId(id);
    AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
    AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
    return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
  }

  @SuppressWarnings({"checkstyle:WhitespaceAfter", "checkstyle:LineLength", "checkstyle:WhitespaceAround", "checkstyle:FinalParameters",
    "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @PatchMapping(path = "/authors/{id}")
  public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
    if (!authorService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
    AuthorEntity updatedAuthor = authorService.partialUpdate(id, authorEntity);

    return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
  }

  @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:FinalParameters", "checkstyle:MethodName",
    "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
  @DeleteMapping(path = "authors/{id}")
  public ResponseEntity<AuthorDto> delete(@PathVariable("id") Long id) {
    authorService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
