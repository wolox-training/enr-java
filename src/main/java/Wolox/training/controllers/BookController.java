package wolox.training.controllers;

import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * This method gets all the saved {@link Book}s
     * @return saved {@link Book}s
     */
    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    /***
     * This method creates a book
     *
     * @param book: contain the book's data
     * @return {@link Book} created
     */
    @PostMapping
    @ApiOperation(value = "Giving an object returns a book", response = Book.class)
    @ApiResponse(code = 200, message = "The book was created successfully.")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /***
     *This method find a book
     *
     * @param id: identifier of the book
     * @return a {@link Book} instance
     */
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    /***
     * This method updates a book
     *
     * @param book: contain the book's data
     * @param id: identifier of the book to update
     * @return {@link Book} updated.
     */
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (!id.equals(book.getId())) {
            throw new BookIdMismatchException();
        }
       bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }

    /***
     * This method remove a {@link Book}
     *
     * @param id: identifier of the book to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }



}
