package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Author {

    @JsonProperty("url")
    private String url;

    @JsonProperty("name")
    private String name;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
