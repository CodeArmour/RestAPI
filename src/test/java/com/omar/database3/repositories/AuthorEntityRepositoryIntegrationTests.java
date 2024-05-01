/*
package com.omar.database3.repositories;


import com.omar.database3.TestDataUtil;
import com.omar.database3.domain.entities.AuthorEntity;
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

public class AuthorEntityRepositoryIntegrationTests {

    private AuthorsRepo underTest;
    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorsRepo underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.creatTestAuthorA();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);

    }


    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtil.creatTestAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.creatTestAuthorB();
        underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.creatTestAuthorC();
        underTest.save(authorEntityC);

        Iterable<AuthorEntity> result =underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorEntityA, authorEntityB, authorEntityC);

    }

    @Test
    public void testThatAuthorCanBeUpdate(){
        AuthorEntity authorEntityA = TestDataUtil.creatTestAuthorA();
        underTest.save(authorEntityA);
        authorEntityA.setName("UPDATE");
        underTest.save(authorEntityA);
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntityA);

    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        AuthorEntity authorEntityA = TestDataUtil.creatTestAuthorA();
        underTest.save(authorEntityA);

        underTest.deleteById(authorEntityA.getId());

        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isEmpty();

    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity testAuthorAEntity = TestDataUtil.creatTestAuthorA();
        underTest.save(testAuthorAEntity);
        AuthorEntity testAuthorBEntity = TestDataUtil.creatTestAuthorB();
        underTest.save(testAuthorBEntity);
        AuthorEntity testAuthorCEntity = TestDataUtil.creatTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorAEntity, testAuthorBEntity, testAuthorCEntity);
    }

    @Test
    public void testThatAuthorWithAgeGreaterThan(){
        AuthorEntity testAuthorAEntity = TestDataUtil.creatTestAuthorA();
        underTest.save(testAuthorAEntity);
        AuthorEntity testAuthorBEntity = TestDataUtil.creatTestAuthorB();
        underTest.save(testAuthorBEntity);
        AuthorEntity testAuthorCEntity = TestDataUtil.creatTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<AuthorEntity> result = underTest.findAuthorWithAgeGreaterThan(21);
        assertThat(result).containsExactly(testAuthorAEntity, testAuthorBEntity);
    }
}
*/
