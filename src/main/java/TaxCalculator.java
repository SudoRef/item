import utils.ProductTaxCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaxCalculator {

    private static final ProductTaxCalculator taxCalculator = new ProductTaxCalculator();

    public static void main(String[] args) {

        String item;
        List<String> inputs = new ArrayList<>();
        do {
            System.out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                item = reader.readLine();
                if(!item.isEmpty()) {
                    inputs.add(item);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("bad input");
            }
        } while (!Objects.equals(item, ""));
        System.out.println(taxCalculator.calculateTax(inputs));
    }
}
