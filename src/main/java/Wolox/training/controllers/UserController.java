package wolox.training.controllers;

import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;
import wolox.training.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private BookRepository bookRepository;

    /***
     *This method find a user by id
     *
     * @param id: identifier of the user
     * @return a User instance
     */
    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /***
     * This method creates a user
     *
     * @param user: contain the user's data
     * @return User created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    /***
     * This method updates a user
     *
     * @param user: contain the user's data
     * @param id: identifier of the user to update
     * @return User updated.
     */
    @PutMapping("/{id}")
    public User updateBook(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new UserNotFoundException();
        }
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }

    /***
     * This method remove a user
     *
     * @param id: identifier of the user to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    /***
     * This method adds a book to the book's user collection
     * @param bookId is the book identifier to be added
     * @param id is the user identifier to be updated
     * @return the books collection
     */
    @PostMapping("/user/{id}/book/{bookId}")
    public List<Book> addBook(@PathVariable Long id, @PathVariable Long bookId) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Book> books = user.addBook(book);

        return books;
    }

    /***
     * This method remove a book from book's user collection
     * @param id is the user identifier to be updated
     * @param bookId is the book identifier to be deleted
     * @return the books collection
     */
    @DeleteMapping("/user/{id}/book/{bookId}")
    public List<Book> removeBook(@PathVariable Long id, @PathVariable Long bookId ) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Book> books = user.removeBook(book);

        return books;
    }

}
