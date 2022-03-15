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
            "AND (b.gender is null or b.gender = :gender) " +
            "AND (b.year is null or b.year = :year)")
    List<Book> findByPublisherAndGenderAndYear(String publisher, String gender, String year);


    @Query("SELECT b FROM Book b WHERE (b.title = :title OR :title IS NULL) AND " +
            "(b.author = :author OR :author IS NULL) AND" +
            "(b.gender = :gender OR :gender IS NULL) AND" +
            "(b.subtitle = :subtitle OR :subtitle IS NULL) AND" +
            "(b.publisher = :publisher OR :publisher IS NULL) AND" +
            "(b.year = :year OR :year IS NULL) AND" +
            "(b.pages = :pages OR :pages IS NULL) AND" +
            "(b.isbn = :isbn OR :isbn IS NULL)")
    List<Book> findBy(String title, String author, String gender, String subtitle, String publisher, String year, Integer pages, String isbn);
}