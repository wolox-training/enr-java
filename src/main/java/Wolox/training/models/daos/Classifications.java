package wolox.training.models.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Classifications {

    @JsonProperty("lc_classifications")
    private List<String> lcClassifications;

    @JsonProperty("dewey_decimal_class")
    private List<String> deweyDecimalClass;
}
