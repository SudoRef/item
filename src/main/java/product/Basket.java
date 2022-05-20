package product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    Set<Product> productList;
    Double salesTaxes;
    Double total;

    @Override
    public String toString() {
        StringBuilder view = new StringBuilder();

        for(Product product: productList){
            view.append(product.getTitle()).append("\n");
        }
        view.append("Sales taxes: ").append(salesTaxes).append("\n");
        view.append("Total: ").append(total);

        return view.toString();
    }
}
