package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static wolox.training.constants.OpenLibraryServiceConstants.OPEN_LIBRARY_RESPONSE_KEY;


@Data
public class BookInfoDAO {

    @JsonProperty(OPEN_LIBRARY_RESPONSE_KEY)
    private Book book;

    public Book getBook() {
        return book;
    }
}
