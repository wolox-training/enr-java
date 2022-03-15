package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Subject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
