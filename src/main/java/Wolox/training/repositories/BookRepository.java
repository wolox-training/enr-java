package Wolox.training.repositories;

import Wolox.training.models.Book;
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
}