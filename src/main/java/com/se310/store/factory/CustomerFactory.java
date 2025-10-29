package com.se310.store.factory;
import com.se310.store.model.Customer;
import com.se310.store.model.CustomerType;

/**
 * Factory Pattern implementation for creating Customer objects
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class CustomerFactory {

    public static Customer createCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {

        if(type == null) {
            //create customers as guest registered customer
            return createStandardCustomer(id, firstName, lastName, type, email, accountAddress);
        }
        else {
            switch (type) {
                case guest:
                    return createGuestCustomer(id, firstName, lastName, type, email, accountAddress);
                case registered:
                    return createRegisteredCustomer(id, firstName, lastName, type, email, accountAddress);  
                default: 
                    System.out.println("Not a valid customer type");   
                    throw new IllegalArgumentException();
            }
        }       
    }
    private static Customer createStandardCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {
        //easy ability to add customizations for standard customers
        Customer standardCustomer = new Customer(id, firstName, lastName, type, email, accountAddress);

        return standardCustomer;
    }
    private static Customer createGuestCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {
        //easy ability to add customizations for guest customers
        Customer guestCustomer = new Customer(id, firstName, lastName, type, email, accountAddress);

        return guestCustomer;
    }

    private static Customer createRegisteredCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {
        //easy ability to add customizations for registered customers
        Customer registeredCustomer = new Customer(id, firstName, lastName, type, email, accountAddress);
        
        return registeredCustomer;
    }
}
