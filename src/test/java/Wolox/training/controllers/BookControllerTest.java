package wolox.training.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    private Book bookObj;

    private List<Book> books = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        bookObj = new Book("String title", "String author", "String gender", "String image", " String subtitle", "String publisher", "2019", 100, "String isbn");
        Book bookTest = new Book("Book Test", "Author2", "String gender", "String image", " String subtitle", "String publisher", "2019", 100, "String isbn");

        books.add(bookTest);
        books.add(bookObj);
    }

    @Test
    public void whenSendBookData_thenCreateIt() throws Exception {

        String bookData = new ObjectMapper().writeValueAsString(bookObj);

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookData))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenFindALl_aBooksListIsReturned() throws Exception {

        when(bookRepository.findAll()).thenReturn(books);

        mvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void whenFindABookById_aBookIsReturned() throws Exception {

        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookObj));

        mvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", containsString(bookObj.getTitle())));
    }

    @Test
    public void whenSendBookData_thenUpdateIt() throws Exception {
        String newTitle = "This is the new Title";
        bookObj.setTitle(newTitle);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode bookData = objectMapper.convertValue(bookObj, ObjectNode.class);
        bookData.put("id", 1);

        String requestBody = new ObjectMapper().writeValueAsString(bookData);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookObj));
        when(bookRepository.save(bookObj)).thenReturn(bookObj);

        mvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void givenABookId_thenTheBookIsDeleted() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookObj));

        mvc.perform(delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void whenFindANonExistentBook_anError404IsReturned() throws Exception {
        mvc.perform(get("/api/books/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenSendMisMatchBookData_thenError400IsReturned() throws Exception {
        String newTitle = "Title Updated";
        bookObj.setTitle(newTitle);

        String requestBody = new ObjectMapper().writeValueAsString(bookObj);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookObj));
        when(bookRepository.save(bookObj)).thenReturn(bookObj);

        mvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

}
