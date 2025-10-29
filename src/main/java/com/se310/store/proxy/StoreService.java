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

public interface StoreService {
    public Store provisionStore(String storeID, String name, String address, String token) throws StoreException;

    public Store showStore(String storeId, String token) throws StoreException;
                            
    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description, AisleLocation location, String token) throws StoreException;

    public Aisle showAisle(String storeId, String aisleNumber, String token) throws StoreException;  

    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId, String name, ShelfLevel level, String description, Temperature temperature, String token) throws StoreException;

    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreException;

    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber, String shelfId, int capacity, int count, String productId, InventoryType type, String token) throws StoreException;

    public Inventory showInventory(String inventoryId, String token) throws StoreException;

    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreException;

    public Product provisionProduct(String productId, String name, String description, String size, String category, double price, Temperature temperature, String token) throws StoreException;

    public Product showProduct(String productId, String token) throws StoreException;

    public Customer provisionCustomer(String customerId, String firstName, String lastName, CustomerType type, String email, String address, String token)throws StoreException;

    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token) throws StoreException;

    public Customer showCustomer(String customerId, String token) throws StoreException;

    public Basket provisionBasket(String basketId, String token) throws StoreException;

    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreException;

    public Basket getCustomerBasket(String customerId, String token) throws StoreException;

    public Basket addBasketProduct(String basketId, String productId, int count, String token) throws StoreException;

    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws StoreException;

    public Basket clearBasket(String basketId, String token) throws StoreException;

    public Basket showBasket(String basketId, String token) throws StoreException;

    public Device provisionDevice(String deviceId, String name, String deviceType, String storeId, String aisleNumber, String token) throws StoreException;

    public Device showDevice(String deviceId, String token) throws StoreException;

    public void raiseEvent(String deviceId, String event, String token) throws StoreException;

    public void issueCommand(String deviceId, String command, String token) throws StoreException;
}   

