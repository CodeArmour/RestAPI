/*
package com.omar.database3.repositories;

import com.omar.database3.TestDataUtil;
import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegraionTest {


    private BooksRepo underTest;

    @Autowired

    public BookEntityRepositoryIntegraionTest(BooksRepo underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.creatTestAuthorA();
        BookEntity bookEntity = TestDataUtil.creatTestBookA(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){

        AuthorEntity authorEntity = TestDataUtil.creatTestAuthorA();


        BookEntity bookEntityA = TestDataUtil.creatTestBookA(authorEntity);
        underTest.save(bookEntityA);

        BookEntity bookEntityB = TestDataUtil.creatTestBookB(authorEntity);
        underTest.save(bookEntityB);

        BookEntity bookEntityC = TestDataUtil.creatTestBookC(authorEntity);
        underTest.save(bookEntityC);


        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(bookEntityA, bookEntityB, bookEntityC);

    }

    @Test
    public void testThatBookCanBeUpdate(){

        AuthorEntity authorEntityA = TestDataUtil.creatTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.creatTestBookA(authorEntityA);
        underTest.save(bookEntityA);

        bookEntityA.setTitle("UPDATE");
        underTest.save(bookEntityA);


        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntityA);

    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.creatTestAuthorA();

        BookEntity bookEntity = TestDataUtil.creatTestBookA(authorEntity);
        underTest.save(bookEntity);

        underTest.deleteById(bookEntity.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isEmpty();
    }
}
*/
