package wolox.training.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;
import wolox.training.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static wolox.training.constants.Pagination.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    public UserController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method gets all the saved {@link User}s
     * @return saved {@link User}s
     */
    @GetMapping
    public List<User> getAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = DEFAULT_SORTING) String sort_by
    ) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort_by));
        return userRepository.findBy(username, name, birthDate, paging);
    }

    /***
     *This method find a user by id
     *
     * @param id: identifier of the {@link User}
     * @return a {@link User} instance
     */
    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /***
     * This method creates a {@link User}
     *
     * @param user: contain the user's data
     * @return {@link User} created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    /***
     * This method updates a {@link User}
     *
     * @param user: contain the user's data
     * @param id: identifier of the user to update
     * @return {@link User} updated.
     */
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        if ( !id.equals(user.getId())) {
            throw new UserIdMismatchException();
        }
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }

    /***
     * This method remove a {@link User}
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
     * This method adds a {@link Book} to the book's user collection
     * @param bookId is the book identifier to be added
     * @param id is the user identifier to be updated
     * @return the books collection
     */
    @PostMapping("/{id}/books/{bookId}")
    public List<Book> addBook(@PathVariable Long id, @PathVariable Long bookId) {
        User user;
        user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        Book book;
        book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Book> books = user.addBook(book);
        userRepository.save(user);

        return books;
    }

    /***
     * This method remove a {@link Book} from book's user collection
     * @param id is the user identifier to be updated
     * @param bookId is the book identifier to be deleted
     * @return the books collection
     */
    @DeleteMapping("/{id}/books/{bookId}")
    public List<Book> removeBook(@PathVariable Long id, @PathVariable Long bookId ) {
        User user;
        user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        Book book;
        book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Book> books = user.removeBook(book);
        userRepository.save(user);
        return books;
    }


    @GetMapping
    public List<User> getBookBy(
            @RequestParam(value = "startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam String name
    ) throws Exception {
        return userRepository.findByBirthDateBetweenAndNameContainingIgnoreCase(startDate, endDate, name);
    }

}
