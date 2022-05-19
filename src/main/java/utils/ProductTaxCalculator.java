package utils;

import product.Basket;
import product.Product;
import product.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

public class ProductTaxCalculator {

    public Basket getBasket(List<Product> productList){
        Basket basket = new Basket();
        basket.setProductList(new HashSet<>(productList));
        basket.setSalesTaxes(calculateTaxes(productList));
        basket.setTotal(calculateTotalPrice(basket.getSalesTaxes(), productList));
        return basket;
    }
    private Double calculateTaxes(List<Product> products){
        Double tax = 0.0;
        for (Product product: products) {
            if(product.getIsImported()){
                tax+=product.getPrice()*0.05;
            }
            if(Type.OTHER.equals(product.getType())){
                tax+=product.getPrice()*0.1;
            }
        }
        return BigDecimal.valueOf(tax)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
    private Double calculateTotalPrice(Double tax, List<Product> products){
        Double totalPrice = 0.0;
        for(Product product: products){
            totalPrice+=product.getPrice();
        }
        totalPrice+=tax;
        return BigDecimal.valueOf(totalPrice)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
