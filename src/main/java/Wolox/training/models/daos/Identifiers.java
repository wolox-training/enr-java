package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Identifiers {
    @JsonProperty("librarything")
    private List<String> librarything;

    @JsonProperty("goodreads")
    private List<String> goodreads;

    @JsonProperty("isbn_10")
    private List<String> isbn10;

    @JsonProperty("lccn")
    private List<String> lccn;

    @JsonProperty("openlibrary")
    private List<String> openlibrary;
}
