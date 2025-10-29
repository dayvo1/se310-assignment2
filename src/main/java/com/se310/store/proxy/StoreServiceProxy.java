package com.se310.store.proxy;

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
import com.se310.store.singleton.RealStoreService;

/**
 * Proxy Pattern implementation for the StoreService
 * The Proxy Pattern provides a surrogate or placeholder for another object to control access to it
 * This implementation adds access control before delegating to the real StoreService
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreServiceProxy implements StoreService{

    private RealStoreService store;
    private String token;

    public StoreServiceProxy(String token){
        this.token = token;
        this.store = RealStoreService.getInstance();
    }

    public void authenticate(String token) throws StoreException{
        if(!(("key123").equals(token)) || token == null){
            throw new StoreException("Authenticate",
             "Invalid Token");
        }
    }

    @Override
    public Store provisionStore(String storeID, String name, String address, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionStore(storeID, name, address, token);
    }

    @Override
    public Store showStore(String storeId, String token) throws StoreException{
        return store.showStore(storeId, token);
    }
        
    @Override
    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description, AisleLocation location, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionAisle(storeId, aisleNumber, name, description, location, token);
    }

    @Override
    public Aisle showAisle(String storeId, String aisleNumber, String token) throws StoreException{
        return store.showAisle(storeId, aisleNumber, token);
    }

    @Override
    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId, String name, ShelfLevel level, String description, Temperature temperature, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionShelf(storeId, aisleNumber, shelfId, name, level, description, temperature, token);
    }

    @Override
    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreException{
        return store.showShelf(storeId, aisleNumber, shelfId, token);
    }

    @Override
    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber, String shelfId, int capacity, int count, String productId, InventoryType type, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionInventory(inventoryId, storeId, aisleNumber, shelfId, capacity, count, productId, type, token);
    }

    @Override
    public Inventory showInventory(String inventoryId, String token) throws StoreException{
        return store.showInventory(inventoryId, token);
    }

    @Override
    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreException{
        authenticate(this.token);
        return store.updateInventory(inventoryId, count, token);
    }

    @Override
    public Product provisionProduct(String productId, String name, String description, String size, String category, double price, Temperature temperature, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionProduct(productId, name, description, size, category, price, temperature, token);
    }

    @Override
    public Product showProduct(String productId, String token) throws StoreException{
        return store.showProduct(productId, token);
    }

    @Override
    public Customer provisionCustomer(String customerId, String firstName, String lastName, CustomerType type, String email, String address, String token)throws StoreException{
        authenticate(this.token);
        return store.provisionCustomer(customerId, firstName, lastName, type, email, address, token);
    }

    @Override
    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token) throws StoreException{
        authenticate(this.token);
        return store.updateCustomer(customerId, storeId, aisleNumber, token);
    }

    @Override
    public Customer showCustomer(String customerId, String token) throws StoreException{
        authenticate(this.token);
        return store.showCustomer(customerId, token);
    }

    @Override
    public Basket provisionBasket(String basketId, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionBasket(basketId, token);
    }

    @Override
    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreException{
        authenticate(this.token);
        return store.assignCustomerBasket(customerId, basketId, token);
    }

    @Override
    public Basket getCustomerBasket(String customerId, String token) throws StoreException{
        authenticate(this.token);
        return store.getCustomerBasket(customerId, token);
    }

    @Override
    public Basket addBasketProduct(String basketId, String productId, int count, String token) throws StoreException{
        authenticate(this.token);
        return store.addBasketProduct(basketId, productId, count, token);
    }

    @Override
    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws StoreException{
        authenticate(this.token);
        return store.removeBasketProduct(basketId, productId, count, token);
    }

    @Override
    public Basket clearBasket(String basketId, String token) throws StoreException{
        authenticate(this.token);
        return store.clearBasket(basketId, token);
    }

    @Override
    public Basket showBasket(String basketId, String token) throws StoreException{
        return store.showBasket(basketId, token);
    }

    @Override
    public Device provisionDevice(String deviceId, String name, String deviceType, String storeId, String aisleNumber, String token) throws StoreException{
        authenticate(this.token);
        return store.provisionDevice(deviceId, name, deviceType, storeId, aisleNumber, token);
    }

    @Override
    public Device showDevice(String deviceId, String token) throws StoreException{
        return store.showDevice(deviceId, token);
    }

    @Override
    public void raiseEvent(String deviceId, String event, String token) throws StoreException{
        authenticate(this.token);
        store.raiseEvent(deviceId, event, token);   
    }

    @Override
    public void issueCommand(String deviceId, String command, String token) throws StoreException{
        authenticate(token);
        store.issueCommand(deviceId, command, token);
    }
}

