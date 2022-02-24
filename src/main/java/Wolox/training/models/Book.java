package wolox.training.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@ApiModel(description = "Represents books from the OpenLibraryApi")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value="Represents the book title.", required = true)
    @Column(nullable = false, unique = true)
    private String title;

    @ApiModelProperty(value = "Author of the book.", required = true)
    @Column(nullable = false)
    private String author;

    @ApiModelProperty(value = "Author of the book.")
    @Column(nullable = true)
    private String gender;

    @ApiModelProperty(value = "Image of book cover page", required = true)
    @Column(nullable = false)
    private String image;

    @ApiModelProperty(value = "Book subtitle", required = true)
    @Column(nullable = false)
    private String subtitle;

    @ApiModelProperty(value = "publisher that published the book", required = true)
    @Column(nullable = false)
    private String publisher;


    @ApiModelProperty(value = "Year of publication", required = true)
    @Column(nullable = false)
    private String year;

    @ApiModelProperty(value = "Number of pages", required = true)
    @Column(nullable = false)
    private Integer pages;

    @ApiModelProperty(value = "ISBN code", required = true)
    @Column(nullable = false)
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Book(){}

    public Book(String title, String author, String gender, String image, String subtitle, String publisher, String year, Integer pages, String isbn) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.image = image;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        checkNotNull(title);
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        checkNotNull(author);
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        checkNotNull(gender);
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        checkNotNull(image);
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        checkNotNull(subtitle);
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        checkNotNull(publisher);
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        checkNotNull(year);
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        checkNotNull(pages);
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        checkNotNull(isbn);
        this.isbn = isbn;
    }
}
