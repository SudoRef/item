import org.junit.jupiter.api.Test;
import product.Basket;
import utils.ProductTaxCalculator;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTaxCalculatorTest {

    ProductTaxCalculator calculator = new ProductTaxCalculator();

    @Test
    public void calculateTax_happyCase1() {
        List<String> input = TestUtils.INPUT_1;
        Basket basket = TestUtils.BASKET_1;
        assertEquals(basket, calculator.calculateTax(input));
    }

    @Test
    public void calculateTax_happyCase2() {
        List<String> input = TestUtils.INPUT_2;
        Basket basket = TestUtils.BASKET_2;
        assertEquals(basket, calculator.calculateTax(input));
    }

    @Test
    public void calculateTax_happyCase3() {
        List<String> input = TestUtils.INPUT_3;
        Basket basket = TestUtils.BASKET_3;
        assertEquals(basket, calculator.calculateTax(input));
    }

    @Test
    public void whenDerivedExceptionThrown_thenAssertionSucceds1(){
        List<String> input = List.of("bottle of perfume: 20.89");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTax(input);
        });
        String expectedMessage = "format is incorrect";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void whenDerivedExceptionThrown_thenAssertionSucceds2(){
        List<String> input = List.of("1 bottle of perfume:");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTax(input);
        });
        String expectedMessage = "format is incorrect";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
