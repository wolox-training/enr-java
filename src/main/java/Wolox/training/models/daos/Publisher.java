package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Publisher {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
