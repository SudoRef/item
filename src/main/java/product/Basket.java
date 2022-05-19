package product;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
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
