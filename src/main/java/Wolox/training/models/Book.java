package wolox.training.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static wolox.training.constants.PreconditionMessage.*;


@Entity
@ApiModel(description = "Represents books from the OpenLibraryApi")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

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

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @ApiModelProperty(value = "ISBN code", required = true)
    @Column(nullable = false, unique = true)
    private String isbn;

    public Book() {}

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
        checkArgument(!isNullOrEmpty(title), NOT_NULL_OR_EMPTY);
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        checkArgument(!isNullOrEmpty(author), NOT_NULL_OR_EMPTY);
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        checkArgument(!isNullOrEmpty(gender), NOT_NULL_OR_EMPTY);
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        checkArgument(!isNullOrEmpty(image), NOT_NULL_OR_EMPTY);
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        checkArgument(!isNullOrEmpty(subtitle), NOT_NULL_OR_EMPTY);
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        checkArgument(!isNullOrEmpty(publisher), NOT_NULL_OR_EMPTY);
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        checkArgument(!isNullOrEmpty(year), NOT_NULL_OR_EMPTY);
        checkArgument(Integer.parseInt(year) > 0, YEAR_IS_LESS);
        checkArgument(Integer.parseInt(year) <= Year.now().getValue(), YEAR_IS_GREATER);
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        checkArgument(!isNullOrEmpty(pages), NOT_NULL_OR_EMPTY);
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        checkArgument(!isNullOrEmpty(isbn), NOT_NULL_OR_EMPTY);
        this.isbn = isbn;
    }
}
