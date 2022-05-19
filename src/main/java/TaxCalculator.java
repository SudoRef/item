import product.Basket;
import product.Product;
import utils.ProductParser;
import utils.ProductTaxCalculator;

import java.util.List;

public class TaxCalculator {

    private static final ProductParser productParser = new ProductParser();
    private static final ProductTaxCalculator taxCalculator = new ProductTaxCalculator();
    public static void main(String[] args) {
        String[] strings = new String[]{"2 book at 12.49","1 music CD at 14.99","1 chocolate bar at 0.85"};
        List<Product> list = productParser.parseBasket(strings);
        Basket basket = taxCalculator.getBasket(list);
        System.out.println(basket);

    }
}
