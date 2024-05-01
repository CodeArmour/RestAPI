# Rest API

### IDE
- intellij idea

### dependencies
- org.springframework.boot
- org.springframework.boot
- org.modelmapper
- org.postgresql
- org.projectlombok
- org.springframework.boot
- com.h2database
- junit.

### plugins
- org.springframework.boot
- org.projectlombok

### Used Databases
- **Postgres** for run program
- Spring Data **JPA**

### Database Manager
- DBeaver

### Other technics Used
- Model Mapper
- Docker

## About this REST API
- The API displays the authors and books stored in the database. It also displays the books to you according to the serial number of each book. It displays the authors according to their ID number. You can also display the books by author. For example, there is an author with the number 102 that will be displayed to you. All the books written by this author. You can also add books and authors as well.

## Database description

| authors  | books      |
|----------|------------|
| :key: id | :key: isbn |
| name     | title      |
| age      | author_id  |

## Using API

| Job                                   |                                Http header | Type of request                     | notes |
|---------------------------------------|-------------------------------------------:|-------------------------------------|-------|
| List all the Authors                  |            `http://localhost:8090/authors` | <span style="color:#41C48A">Get     | -     |
| List all the Books                    |             `http://localhost:8090/books ` | <span style="color:#41C48A">Get     | -     |
| List Author by Id                     |      `http://localhost:8090/authors/{id} ` | <span style="color:#41C48A">Get     | -     |
| List Book by Isbn                     |      `http://localhost:8090/books/{isbn} ` | <span style="color:#41C48A">Get     | -     |
| List the books that created by author | `http://localhost:8090/books/{author_id} ` | <span style="color:#41C48A">Get     | -     |
| Create new author                     |            `http://localhost:8090/authors` | <span style="color:yellow">Post     | +json |
| Create new book                       |       `http://localhost:8090/books/{isbn}` | <span style="color:#6CD3E5">Put     | +json |
| Update Author information             |       `http://localhost:8090/authors/{id}` | <span style="color:#6CD3E5">Put     | +json |
| Update Book information               |       `http://localhost:8090/books/{isbn}` | <span style="color:#6CD3E5">Put     | +json |
| partial update author                 |       `http://localhost:8090/authors/{id}` | <span style="color:#A744C4">Patch   | +json |
| partial update book                   |       `http://localhost:8090/books/{isbn}` | <span style="color:#A744C4">Patch   | +json |
| Delete Author                         |       `http://localhost:8090/authors/{id}` | <span style="color:#BE4163">Deletes | -     |
| Delete Book                           |       `http://localhost:8090/books/{isbn}` | <span style="color:#BE4163">Delete  | -     |



## Some additional types of headers:
+ `http://localhost:8090/books?size=1`
  + **that mean in one page just one element put**
    + return at the top of body for example :
      ```json
      {
      "totalElements": 2,
      "totalPages": 2,
      "first": true,
      "last": false,
      "numberOfElements": 1,
      "size": 1,
      "content": [
      { ...
      ```

+ `http://localhost:8090/books?size=1&pages=1`
  + **that mean in one page just one element put and show page number 1**
     + ```json
       {
        "totalElements": 2,
        "totalPages": 2,
        "first": false,
        "last": true,
        "numberOfElements": 1,
        "size": 1,
        "content": [
        {
        "isbn": "439-452-Q",
        "title": "Never die",
        "author": null
        }
        ],
        "number": 1,
       ```
       

# Links:
- https://web.postman.co
- https://modelmapper.org
- https://mvnrepository.com
- https://spring.io
- https://start.spring.io
- https://github.com
- https://www.docker.com
- https://htmlcolorcodes.com
- https://stackedit.io




