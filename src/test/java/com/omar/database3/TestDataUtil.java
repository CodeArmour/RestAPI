package com.omar.database3;


import com.omar.database3.domain.dto.AuthorDto;
import com.omar.database3.domain.dto.BookDto;
import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.domain.entities.BookEntity;


public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static AuthorEntity creatTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Jazmin Nabhan")
                .age(22)
                .build();
    }

    public static AuthorEntity creatTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Omar Maysara")
                .age(22)
                .build();
    }

        public static AuthorEntity creatTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Ali Rawi")
                .age(21)
                .build();
    }
        public static AuthorDto creatTestAuthorDtoC() {
        return AuthorDto.builder()
                .id(3l)
                .name("Ali Rawi")
                .age(21)
                .build();
    }


    public static BookEntity creatTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("ABC")
                .title("Moo")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto creatTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("ABC")
                .title("Moo")
                .author(author)
                .build();
    }


    public static BookEntity creatTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("DEF")
                .title("Maa")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity creatTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("GHI")
                .title("Mee")
                .authorEntity(authorEntity)
                .build();
    }
}
