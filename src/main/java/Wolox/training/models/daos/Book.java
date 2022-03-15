package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty("url")
    private String url;

    @JsonProperty("key")
    private String key;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("authors")
    private List<Author> authors;

    @JsonProperty("number_of_pages")
    private Integer numberOfPages;

    @JsonProperty("pagination")
    private String pagination;

    @JsonProperty("identifiers")
    private Identifiers identifiers;

    @JsonProperty("classifications")
    private Classifications classifications;

    @JsonProperty("publishers")
    private List<Publisher> publishers;

    @JsonProperty("publish_places")
    private List<PublishPlace> publishPlaces;

    @JsonProperty("publish_date")
    private String publishDate;

    @JsonProperty("subjects")
    private List<Subject> subjects;

    @JsonProperty("cover")
    private Cover cover;


    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public String getPagination() {
        return pagination;
    }

    public Identifiers getIdentifiers() {
        return identifiers;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public List<PublishPlace> getPublishPlaces() {
        return publishPlaces;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Cover getCover() {
        return cover;
    }
}
