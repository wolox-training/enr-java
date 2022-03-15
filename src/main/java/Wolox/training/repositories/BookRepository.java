package wolox.training.repositories;

import org.springframework.data.jpa.repository.Query;
import wolox.training.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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

    /***
     * This methods find a book by publisher, gender and year
     * @param publisher
     * @param gender
     * @param year
     * @returna books List
     */
    @Query("SELECT b FROM Book b WHERE (b.publisher is null or b.publisher = :publisher) " +
            "AND (:gender IS NULL OR b.gender = :gender) " +
            "AND (:year IS NULL OR b.year = :year)")
    List<Book> findByPublisherAndGenderAndYear(String publisher, String gender, String year);
}