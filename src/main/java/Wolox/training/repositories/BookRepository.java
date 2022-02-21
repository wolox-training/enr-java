package Wolox.training.repositories;

import Wolox.training.models.Book;
import org.springframework.data.repository.Repository;
import java.util.Optional;

public interface BookRepository extends Repository<Book, Long> {

    Optional<Book> findByAuthor(String author);
}