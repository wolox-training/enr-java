package wolox.training.models.dtos;

import wolox.training.models.daos.Author;
import wolox.training.models.daos.BookInfoDAO;
import wolox.training.models.daos.Publisher;

import java.util.List;


public class BookInfoDTO {

    private String isbn;
    private String title;
    private String subtitle;
    private List<Publisher> publishers;
    private String year;
    private Integer pages;
    private List<Author> authors;

    private BookInfoDTO(BookInfoDAO bookInfoDAO, String isbn){
        this.isbn = isbn;
        this.title = bookInfoDAO.getBook().getTitle();
        this.subtitle = bookInfoDAO.getBook().getSubtitle();
        this.publishers = bookInfoDAO.getBook().getPublishers();
        this.year = bookInfoDAO.getBook().getPublishDate();
        this.pages = bookInfoDAO.getBook().getNumberOfPages();
        this.authors = bookInfoDAO.getBook().getAuthors();
    }

    public static BookInfoDTO build(BookInfoDAO bookInfoDAO, String isbn){
        return new BookInfoDTO(bookInfoDAO, isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
