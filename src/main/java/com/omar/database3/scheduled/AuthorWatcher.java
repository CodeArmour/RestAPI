package com.omar.database3.scheduled;

import com.omar.database3.repositories.AuthorsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;


@Component
@Slf4j
public class AuthorWatcher {

    private final AuthorsRepo authorsRepo;

    @Autowired
    public AuthorWatcher(AuthorsRepo authorsRepo) {
        this.authorsRepo = authorsRepo;
    }


    @Scheduled(fixedDelay = 10000)
    public void authorCount(){
        final long authorCount = authorsRepo.count();

        log.info("There are {} many authors in the database",authorCount);



    }


}
