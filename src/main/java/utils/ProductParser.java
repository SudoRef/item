package utils;

import product.Product;
import product.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductParser {

    public List<Product> parseBasket(String[] items) {
        List<Product> productList = new ArrayList<>();
        for (String itm : items) {
            int amount = getAmountOfProducts(itm);
            while (amount > 0) {
                productList.add(parseItem(itm));
                amount--;
            }
        }
        return productList;
    }

    private Integer getAmountOfProducts(String itm)  {
        try {
            return Integer.parseInt(itm.split(" ")[0]);
        }catch (NumberFormatException e){
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
