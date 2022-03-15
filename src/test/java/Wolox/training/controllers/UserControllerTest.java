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
import wolox.training.repositories.UserRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookRepository bookRepository;


    @Test
    public void whenSendUserData_thenAUserIsCreated() throws Exception {

        ObjectNode userData = objectMapper.convertValue(TestData.USER, ObjectNode.class);
        userData.remove("id");
        userData.remove("books");

        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(userData)))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    public void whenFindALl_aUsersListIsReturned() throws Exception {

        when(userRepository.findAll()).thenReturn(TestData.USERS);

        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(TestData.USERS.size())));

    }

    @Test
    public void whenFindAUserById_aUserIsReturned() throws Exception {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(TestData.USER));

        mvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", containsString(TestData.USER.getUsername())));
    }

    @Test
    public void whenSendUserData_thenTheUserIsUpdated() throws Exception {
        String newUsername = "new.username";
        TestData.USER.setUsername(newUsername);

        ObjectNode userData = objectMapper.convertValue(TestData.USER, ObjectNode.class);
        userData.put("id", 1);
        userData.remove("books");

        String requestBody = objectMapper.writeValueAsString(userData);

        when(userRepository.findById(1L)).thenReturn(Optional.of(TestData.USER));
        when(userRepository.save(TestData.USER)).thenReturn(TestData.USER);

        mvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAUserId_thenTheUserIsDeleted() throws Exception {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(TestData.USER));

        mvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserAndBookID_thenTheBookIsAddedToUser() throws Exception {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(TestData.USER));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(TestData.BOOK));

        mvc.perform(post("/api/users/1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", containsString(TestData.BOOK.getTitle())));
    }

    @Test
    public void whenFindANonExistentUser_anError404IsReturned() throws Exception {
        mvc.perform(get("/api/users/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenSendMisMatchUserData_thenError400IsReturned() throws Exception {
        String newUsername = "new.username";
        TestData.USER.setUsername(newUsername);

        ObjectNode userData = objectMapper.convertValue(TestData.USER, ObjectNode.class);

        String requestBody = objectMapper.writeValueAsString(userData);

        mvc.perform(put("/api/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
