package utils;

import product.Basket;
import product.Product;
import product.Type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class ProductTaxCalculator {

    public Basket calculateTax(List<String> items) {
        List<Product> productList = new ArrayList<>();
        for (String itm : items) {
            itm = itm.trim();
            int amount = getAmountOfProducts(itm);
            while (amount > 0) {
                productList.add(parseItem(itm));
                amount--;
            }
        }
        return getBasket(productList);
    }

    public Basket getBasket(List<Product> productList) {
        Basket basket = new Basket();
        basket.setProductList(new HashSet<>(productList));
        basket.setSalesTaxes(calculateTaxes(productList));
        basket.setTotal(calculateTotalPrice(basket.getSalesTaxes(), productList));
        return basket;
    }
    private Double calculateTaxes(List<Product> products){
        double tax = 0.0;
        for (Product product: products) {
            if(product.getIsImported()){
                tax+=product.getPrice()*0.05;
            }
            if(Type.OTHER.equals(product.getType())){
                tax+=product.getPrice()*0.1;
            }
        }

        return Math.round(tax * 20.0) / 20.0;
    }

    private Double calculateTotalPrice(Double tax, List<Product> products) {
        Double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        totalPrice += tax;
        return BigDecimal.valueOf(totalPrice)
                .round( new MathContext(4, RoundingMode.UP) )
                .doubleValue();
    }


    private Integer getAmountOfProducts(String itm) {
        try {
            return Integer.parseInt(itm.split(" ")[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("format is incorrect");
        }
    }

    private Product parseItem(String item) {
        String[] splitItem = item.split(" ");
        try {
            Double price = Double.parseDouble(splitItem[splitItem.length - 1]);
            Type productType = getProductType(item.toLowerCase(Locale.ROOT));
            return Product.builder()
                    .price(price)
                    .isImported(item.contains("import"))
                    .title(item)
                    .type(productType)
                    .build();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("format is incorrect");
        }

    }

    private Type getProductType(String item) {

        if (item.contains("book")) {
            return Type.BOOK;
        } else if (item.contains("medicine") || item.contains("pills")) {
            return Type.MEDICINE;
        } else if (item.contains("chocolate") || item.contains("food")) {
            return Type.FOOD;
        }
        return Type.OTHER;
    }
}
