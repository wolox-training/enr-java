package wolox.training.mocks;

import wolox.training.models.Book;
import wolox.training.models.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public final static List<User> USERS = Arrays.asList(
            new User("user.name.1", "User One", LocalDate.of(1991, Month.MAY, 10)),
            new User("user.name.2", "User Two", LocalDate.of(1991, Month.MAY, 11)),
            new User("user.name.3", "User three", LocalDate.of(1991, Month.MAY, 12)));

    public final static User USER = new User("user.test", "User Test", LocalDate.of(1991, Month.MAY, 12));

    public final static Book BOOK = new Book("String title", "String author", "String gender",
            "String image", " String subtitle", "String publisher",
            "2019", 100, "String isbn");

    public final static List<Book> BOOKS = Arrays.asList(
            new Book("Book One", "String author", "String gender","String image", " String subtitle", "String publisher","2019", 100, "String isbn"),
            new Book("Book Two", "String author", "String gender","String image", " String subtitle", "String publisher","2019", 100, "String isbn"));

}
