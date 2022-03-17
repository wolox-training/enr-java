package wolox.training.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.dtos.BookInfoDTO;
import wolox.training.repositories.BookRepository;
import wolox.training.services.OpenLibraryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Api
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    OpenLibraryService openLibraryService;

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

    /***
     *This method find a book by isbn on database or External API
     *
     * @param isbn: isbn of the book
     * @return a {@link Book} instance
     */
    @GetMapping("/open-library/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) throws Exception {
        Optional<Book> dbBook = bookRepository.findByIsbn(isbn);
        if (dbBook.isPresent()){
            return ResponseEntity.ok(dbBook);
        }

        BookInfoDTO externalBook = Optional.ofNullable(openLibraryService.bookInfo(isbn))
                .orElseThrow(BookNotFoundException::new);
        Book newBook = new Book(
                externalBook.getTitle(),
                externalBook.getAuthors().get(0).getName(),
                "gender",
                "image",
                externalBook.getSubtitle() != null ? externalBook.getSubtitle() : externalBook.getTitle(),
                externalBook.getPublishers().get(0).getName(),
                externalBook.getYear(),
                externalBook.getPages(),
                externalBook.getIsbn());

        newBook = bookRepository.save(newBook);

        return ResponseEntity.created(URI.create("/api/books/"+isbn)).body(newBook);

    }

        Book newBook = new Book(
                externalBook.getTitle(),
                externalBook.getAuthors().get(0).getName(),
                "gender",
                "image",
                externalBook.getSubtitle() != null ? externalBook.getSubtitle() : externalBook.getTitle(),
                externalBook.getPublishers().get(0).getName(),
                externalBook.getYear(),
                externalBook.getPages(),
                externalBook.getIsbn());

        newBook = bookRepository.save(newBook);

        return ResponseEntity.created(URI.create("/api/books/"+isbn)).body(newBook);

    }

    @GetMapping("/findby-publisher-gender-year")
    public List<Book> getBookBy(
            @RequestParam String publisher,
            @RequestParam String gender,
            @RequestParam String year

    ) throws Exception {
        return bookRepository.findByPublisherAndGenderAndYear(publisher, gender, year);
    }

}
