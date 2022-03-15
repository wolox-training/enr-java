package wolox.training.repositories;

import wolox.training.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/***
 * This is a repository to work on book operations
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /***
     * This method finds a book by author
     *
     * @param author
     * @return a book
     */
    Optional<Book> findByAuthor(String author);

    /***
     * This method find a book by isbn
     *
     * @param isbn
     * @return a book
     */
    Optional<Book> findByIsbn(String isbn);
}