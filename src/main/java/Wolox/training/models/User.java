package wolox.training.models;

import wolox.training.exceptions.BookAlreadyOwnedException;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_user",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    private List<Book> books;

    public User(String username, String name, String birthDate) {
        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        checkNotNull(id);
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        checkNotNull(username);
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkNotNull(name);
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        checkNotNull(birthDate);
        this.birthDate = birthDate;
    }

    /***
     * This method return an unmodifiable book list.
     * @return List<Book>
     */
    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        checkArgument(!books.isEmpty());
        this.books = books;
    }

    /***
     * This method adds a book only if that book not exist in the list
     *
     * @param book object to be added.
     * @return List<Book>
     */
    public List<Book> addBook(Book book){
        if (books.contains(book)) {
            throw new BookAlreadyOwnedException();
        }
        books.add(book);
        return this.books;
    }

    /***
     * This method removes a book from the list
     *
     * @param book object to be deleted
     * @return List<Book>
     */
    public List<Book> removeBook(Book book){
        this.books.remove(book);
        return  this.books;
    }
}
