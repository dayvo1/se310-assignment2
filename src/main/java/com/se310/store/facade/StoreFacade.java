package com.se310.store.facade;

import com.se310.store.factory.CustomerFactory;
import com.se310.store.factory.ProductFactory;
import com.se310.store.model.Aisle;
import com.se310.store.model.AisleLocation;
import com.se310.store.model.Basket;
import com.se310.store.model.Customer;
import com.se310.store.model.CustomerType;
import com.se310.store.model.Device;
import com.se310.store.model.Inventory;
import com.se310.store.model.InventoryType;
import com.se310.store.model.Product;
import com.se310.store.model.Shelf;
import com.se310.store.model.ShelfLevel;
import com.se310.store.model.Store;
import com.se310.store.model.StoreException;
import com.se310.store.model.Temperature;
import com.se310.store.proxy.StoreServiceProxy;

/**
 * Facade Pattern implementation for the store subsystem
 * Hides the complexity of the StoreServiceProxy and factories by providing
 * a simplified unified interface for client code.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreFacade {

    private final StoreServiceProxy proxy;
    private final String token;

    public StoreFacade(String token) {
        this.token = token;
        this.proxy = new StoreServiceProxy(token);
    }

    // Store Methods
    public Store createStore(String storeID, String name, String address) throws StoreException {
        return proxy.provisionStore(storeID, name, address, token);
    }

    public Store getStore(String storeId) throws StoreException {
        return proxy.showStore(storeId, token);
    }

    // Aisle Methods
    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description, AisleLocation location) throws StoreException {
        return proxy.provisionAisle(storeId, aisleNumber, name, description, location, token);
    }

    public Aisle getAisle(String storeId, String aisleNumber) throws StoreException {
        return proxy.showAisle(storeId, aisleNumber, token);
    }

    // Shelf Methods
    public Shelf createShelf(String storeId, String aisleNumber, String shelfId, String name, ShelfLevel level, String description, Temperature temperature) throws StoreException {
        return proxy.provisionShelf(storeId, aisleNumber, shelfId, name, level, description, temperature, token);
    }

    public Shelf getShelf(String storeId, String aisleNumber, String shelfId) throws StoreException {
        return proxy.showShelf(storeId, aisleNumber, shelfId, token);
    }

    // Inventory Methods
    public Inventory createInventory(String inventoryId, String storeId, String aisleNumber, String shelfId, int capacity, int count, String productId, InventoryType type) throws StoreException {
        return proxy.provisionInventory(inventoryId, storeId, aisleNumber, shelfId, capacity, count, productId, type, token);
    }

    public Inventory getInventory(String inventoryId) throws StoreException {
        return proxy.showInventory(inventoryId, token);
    }

    public Inventory updateInventoryCount(String inventoryId, int count) throws StoreException {
        return proxy.updateInventory(inventoryId, count, token);
    }

    // Product Factory Methods
    public Product createProduct(String productId, String name, String description, String size, String category, double price, Temperature temperature) throws StoreException {
        Product product = ProductFactory.createProduct(productId, name, description, size, category, price, temperature);
        return proxy.provisionProduct(productId, product.getName(), product.getDescription(), product.getSize(), product.getCategory(), product.getPrice(), product.getTemperature(), token);
    }

    public Product getProduct(String productId) throws StoreException {
        return proxy.showProduct(productId, token);
    }

    // Customer Factory Methods
    public Customer createCustomer(String customerId, String firstName, String lastName, CustomerType type, String email, String address) throws StoreException {
        Customer customer = CustomerFactory.createCustomer(customerId, firstName, lastName, type, email, address);
        return proxy.provisionCustomer(customerId, firstName, lastName, type, email, address, token);
    }

    public Customer getCustomer(String customerId) throws StoreException {
        return proxy.showCustomer(customerId, token);
    }

    public Customer updateCustomerLocation(String customerId, String storeId, String aisleNumber) throws StoreException {
        return proxy.updateCustomer(customerId, storeId, aisleNumber, token);
    }

    // Basket Methods
    public Basket createBasket(String basketId) throws StoreException {
        return proxy.provisionBasket(basketId, token);
    }

    public Basket assignBasketToCustomer(String customerId, String basketId) throws StoreException {
        return proxy.assignCustomerBasket(customerId, basketId, token);
    }

    public Basket getCustomerBasket(String customerId) throws StoreException {
        return proxy.getCustomerBasket(customerId, token);
    }

    public Basket addProductToBasket(String basketId, String productId, int quantity) throws StoreException {
        return proxy.addBasketProduct(basketId, productId, quantity, token);
    }

    public Basket removeProductFromBasket(String basketId, String productId, int quantity) throws StoreException {
        return proxy.removeBasketProduct(basketId, productId, quantity, token);
    }

    public Basket emptyBasket(String basketId) throws StoreException {
        return proxy.clearBasket(basketId, token);
    }

    public Basket viewBasket(String basketId) throws StoreException {
        return proxy.showBasket(basketId, token);
    }

    // Device Methods
    public Device createDevice(String deviceId, String name, String deviceType, String storeId, String aisleNumber) throws StoreException {
        return proxy.provisionDevice(deviceId, name, deviceType, storeId, aisleNumber, token);
    }

    public Device getDevice(String deviceId) throws StoreException {
        return proxy.showDevice(deviceId, token);
    }

    // Event and Command Methods
    public void triggerEvent(String deviceId, String event) throws StoreException {
        proxy.raiseEvent(deviceId, event, token);
    }

    public void issueCommandToDevice(String deviceId, String command) throws StoreException {
        proxy.issueCommand(deviceId, command, token);
    }

}
