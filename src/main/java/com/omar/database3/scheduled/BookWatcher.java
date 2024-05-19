package com.omar.database3.scheduled;

import com.omar.database3.repositories.BooksRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class BookWatcher {

  private final BooksRepo booksRepo;


  @Autowired
  public BookWatcher(BooksRepo booksRepo) {
    this.booksRepo = booksRepo;
  }

  @Scheduled(fixedDelay = 10000)
  public void printBookCount() {
    final long bookCount = booksRepo.count();
    log.info("There are {} many books in the database", bookCount);
    log.info("Go to the Postman ----------->");
    log.info("Use: Get, Put, Patch, Delete");
  }
}
