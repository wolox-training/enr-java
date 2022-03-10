package wolox.training.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import wolox.training.mocks.TestData;
import wolox.training.repositories.BookRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;


    @Test
    public void whenSendBookData_thenCreateIt() throws Exception {

        String bookData = new ObjectMapper().writeValueAsString(TestData.BOOK);

        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookData))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenFindALl_aBooksListIsReturned() throws Exception {

        when(bookRepository.findAll()).thenReturn(TestData.BOOKS);

        mvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(TestData.BOOKS.size())));

    }

    @Test
    public void whenFindABookById_aBookIsReturned() throws Exception {

        when(bookRepository.findById(1L)).thenReturn(Optional.of(TestData.BOOK));

        mvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", containsString(TestData.BOOK.getTitle())));
    }

    @Test
    public void whenSendBookData_thenUpdateIt() throws Exception {
        String newTitle = "This is the new Title";
        TestData.BOOK.setTitle(newTitle);

        ObjectNode bookData = objectMapper.convertValue(TestData.BOOK, ObjectNode.class);
        bookData.put("id", 1);

        String requestBody = new ObjectMapper().writeValueAsString(bookData);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(TestData.BOOK));
        when(bookRepository.save(TestData.BOOK)).thenReturn(TestData.BOOK);

        mvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void givenABookId_thenTheBookIsDeleted() throws Exception {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(TestData.BOOK));

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
        TestData.BOOK.setTitle(newTitle);
        String requestBody = new ObjectMapper().writeValueAsString(TestData.BOOK);

        mvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isBadRequest());
    }

}
