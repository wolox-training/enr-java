package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PublishPlace {
    @JsonProperty("name")
    private String name;
}
