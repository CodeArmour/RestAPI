package com.omar.database3.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omar.database3.TestDataUtil;
import com.omar.database3.domain.dto.AuthorDto;
import com.omar.database3.domain.entities.AuthorEntity;
import com.omar.database3.services.AuthorService;
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
public class AuthorControllerIntegrationTest {


    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc,AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService=authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Jazmin Nabhan")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("22")
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        authorService.save(testAuthorA);

        //AuthorEntity testAuthorB = TestDataUtil.creatTestAuthorB();
        //authorService.createAuthor(testAuthorB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Jazmin Nabhan")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(22)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorB = TestDataUtil.creatTestAuthorB();
        authorService.save(testAuthorB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Jazmin Nabhan")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(22)
        );
    }


    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetUpdateAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        AuthorDto testAuthorDtoC = TestDataUtil.creatTestAuthorDtoC();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoC);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetUpdateAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorC = TestDataUtil.creatTestAuthorC();
        AuthorEntity savedAuthor = authorService.save(testAuthorC);

        AuthorDto testAuthorDtoC = TestDataUtil.creatTestAuthorDtoC();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoC);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+ savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdateExistingAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorEntity authorDto = TestDataUtil.creatTestAuthorC();
        authorDto.setId(savedAuthor.getId());

        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+ savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Ali Rawi")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(21)
        );

    }

    @Test
    public void testThatGetPartialUpdateAuthorReturnsHttpStatus200Ok() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorEntity authorDto = TestDataUtil.creatTestAuthorC();
        authorDto.setId(savedAuthor.getId());
        authorDto.setName("UPDATED");

        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+ savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetPartialUpdateAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorEntity authorDto = TestDataUtil.creatTestAuthorC();
        authorDto.setId(savedAuthor.getId());
        authorDto.setName("UPDATED");

        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+ savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNotExistingAuthor() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception{
        AuthorEntity testAuthorA = TestDataUtil.creatTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }





}
