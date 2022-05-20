package utils;

import product.Basket;
import product.Product;
import product.Type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

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
        basket.setTotal(calculateTotalPrice(productList));
        return basket;
    }

    private Double calculateTaxes(List<Product> products) {
        double tax = 0.0;
        for (Product product : products) {
            double productTax = 0.0;
            if (product.getIsImported()) {
                productTax += product.getPrice() * 0.05;
            }
            if (Type.OTHER.equals(product.getType())) {
                productTax += product.getPrice() * 0.1;

            }
            tax += productTax;
            double scale = Math.pow(10, 2);
            product.setPrice(Math.round((product.getPrice() + productTax) * scale) / scale);
            updatePrice(product);
        }

        return Math.round(tax * 20.0) / 20.0;
    }

    private void updatePrice(Product product) {
        String[] title = product.getTitle().split(" ");
        title[title.length - 1] = String.valueOf(product.getPrice());
        StringBuilder sb = new StringBuilder();
        for (String str : title) {
            if ("at".equals(str)) {
                sb.append(":");
                continue;
            }
            sb.append(" ").append(str);
        }
        product.setTitle(sb.toString().trim());
    }

    private Double calculateTotalPrice(List<Product> products) {
        Double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        return BigDecimal.valueOf(totalPrice)
                .round(new MathContext(4, RoundingMode.UP))
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
