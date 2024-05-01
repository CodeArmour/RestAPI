package com.omar.database3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omar.database3.TestDataUtil;
import com.omar.database3.domain.dto.BookDto;
import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.domain.entities.BookEntity;
import com.omar.database3.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    private BookService bookService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc,BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService=bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttp201Created()throws Exception{
        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateBookReturnsHttp200Ok()throws Exception{
        BookEntity testBookEntityA = TestDataUtil.creatTestBookEntityA(null);
        BookEntity savdBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        bookDto.setIsbn(savdBookEntity.getIsbn());
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook()throws Exception{
        BookEntity testBookEntityA = TestDataUtil.creatTestBookEntityA(null);
        BookEntity savdBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        bookDto.setIsbn(savdBookEntity.getIsbn());
        bookDto.setTitle("UPDATED");
        String createBookJson = objectMapper.writeValueAsString(bookDto);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }


    @Test
    public void testThatCreateBookReturnsCreatedBook()throws Exception{
        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttp200OkCreated()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsBooksReturnsHttp200OkCreated()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsBooksReturnsBooksList()throws Exception{

        AuthorEntity testAuthor = TestDataUtil.creatTestAuthorA();

        BookEntity testBookB = TestDataUtil.creatTestBookB(testAuthor);
        bookService.createUpdateBook(testBookB.getIsbn(),testBookB);



        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/authors/"+testAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(testBookB.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(testBookB.getTitle())
        );
    }



    @Test
    public void testThatListBookReturnsBook() throws Exception {
        BookEntity testBookA = TestDataUtil.creatTestBookEntityA(null);
        bookService.createUpdateBook(testBookA.getIsbn(),testBookA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].isbn").value(testBookA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].title").value(testBookA.getTitle())
        );
    }

    @Test
    public void testThatGetBookReturnsHttp2000kCreatedWhenBookExists()throws Exception{

        BookEntity testBookB = TestDataUtil.creatTestBookB(null);
        bookService.createUpdateBook(testBookB.getIsbn(),testBookB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/DEF")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("DEF")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Maa")
        );
    }

    @Test
    public void testThatGetBookReturnsBookWhenBookExists()throws Exception{
        BookEntity testBookB = TestDataUtil.creatTestBookB(null);
        bookService.createUpdateBook(testBookB.getIsbn(),testBookB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/DEF")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBookReturnsHttp404CreatedWhenBookNotExists()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/DEF")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetPartialUpdateBookReturnsStatus200Ok()throws Exception{

        BookEntity testBookEntityA = TestDataUtil.creatTestBookEntityA(null);
        BookEntity savdBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        bookDto.setIsbn(savdBookEntity.getIsbn());
        bookDto.setTitle("UPDATED");
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook()throws Exception{

        BookEntity testBookEntityA = TestDataUtil.creatTestBookEntityA(null);
        BookEntity savdBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
        bookDto.setIsbn(savdBookEntity.getIsbn());
        bookDto.setTitle("UPDATED");
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testBookEntityA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatDeleteBookReturnsStatus204ForNotExistingBook() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteBookReturnsStatus204ForExistingBook() throws Exception{
        BookEntity testBookEntityA = TestDataUtil.creatTestBookEntityA(null);
        BookEntity savdBookEntity = bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/"+savdBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }






}
