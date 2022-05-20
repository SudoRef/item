import product.Basket;
import product.Product;
import product.Type;

import java.util.HashSet;
import java.util.List;

public class TestUtils {
    // Input 1
    public static final List<String> INPUT_1 = List.of("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85");
    public static final List<Product> PRODUCT_LIST_1 = List.of(
            new Product("1 book: 12.49", false, Type.BOOK, 12.49),
            new Product("1 music CD: 16.49", false, Type.OTHER, 16.49),
            new Product("1 chocolate bar: 0.85", false, Type.FOOD, 0.85));
    public static final Basket BASKET_1 = Basket.builder()
            .productList(new HashSet<>(PRODUCT_LIST_1))
            .salesTaxes(1.50)
            .total(29.83).build();
    // Input 2
    public static final List<String> INPUT_2 = List.of("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50");
    public static final List<Product> PRODUCT_LIST_2 = List.of(
            new Product("1 imported box of chocolates: 10.5", true, Type.FOOD, 10.50),
            new Product("1 imported bottle of perfume: 54.63", true, Type.OTHER, 54.63));
    public static final Basket BASKET_2 = Basket.builder()
            .productList(new HashSet<>(PRODUCT_LIST_2))
            .salesTaxes(7.65)
            .total(65.13).build();

    // Input 3
    public static final List<String> INPUT_3 = List.of("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99","1 packet of headache pills at 9.75","1 box of imported chocolates at 11.25");
    public static final List<Product> PRODUCT_LIST_3 = List.of(
            new Product("1 imported bottle of perfume: 32.19", true, Type.OTHER, 32.19),
            new Product("1 bottle of perfume: 20.89", false, Type.OTHER, 20.89),
            new Product("1 packet of headache pills: 9.75", false, Type.MEDICINE, 9.75),
            new Product("1 imported box of chocolates: 11.85", true, Type.FOOD, 11.85));
    public static final Basket BASKET_3 = Basket.builder()
            .productList(new HashSet<>(PRODUCT_LIST_3))
            .salesTaxes(6.65)
            .total(74.64).build();
}
