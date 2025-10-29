package com.se310.store.factory;
import com.se310.store.model.Product;
import com.se310.store.model.Temperature;

/**
 * Factory Pattern implementation for creating Product objects
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class ProductFactory {

    // //TODO: Implement Factory Pattern for creating discounted, premium and standard products
    public static Product createProduct(String id, String name, String description, String size, String category, Double price, Temperature temperature) {
        
        //extracting size (weight) from input string
        String num = "";
        int i = 0;
        while (i < size.length() && Character.isDigit(size.charAt(i))) {
            num += size.charAt(i);
            i++;
        }
        int weight = Integer.parseInt(num);

        //determining if a product is premium, standard, or discounted based on weight
        if(weight < 15) {
            return createPremiumProduct(id, name, description, size, category, price, temperature);
        }
        else if (weight < 100) {
            return createStandardProduct(id, name, description, size, category, price, temperature);
        }
        else {
            return createDiscountedProduct(id, name, description, size, category, price, temperature);
        }
    }

    //increasing, decreasing, or keeping the price based on type of product
    private static Product createPremiumProduct(String id, String name, String description, String size, String category, Double price, Temperature temperature) {
        final double PRICE_INCREASE_PERCENT = 5;

        Product premiumProduct = new Product(id, name, description, size, category, price + (price * PRICE_INCREASE_PERCENT / 100), temperature);

        return premiumProduct;
    }

    private static Product createStandardProduct(String id, String name, String description, String size, String category, Double price, Temperature temperature) {

        Product standardProduct = new Product(id, name, description, size, category, price, temperature);
        
        return standardProduct;
    }

    private static Product createDiscountedProduct(String id, String name, String description, String size, String category, Double price, Temperature temperature) {
        final double PRICE_DECREASE_PERCENT = 5;

        Product discountedProduct = new Product(id, name, description, size, category, price - (price * PRICE_DECREASE_PERCENT / 100), temperature);
        
        return discountedProduct;
    }
}