package product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    String title;
    Boolean isImported;
    Type type;
    Double price;

}
