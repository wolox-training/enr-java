package Wolox.training.repositories;

import Wolox.training.models.Book;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface BookRepository extends Repository<Book, Long> {

    List<Book> findByAuthor(String author);
}