public interface Store {
    // Add methods for each of the following behaviors

    // Opening the store
    void open();

    // Closing the store
    void close();

    // Restocking shelves
    void restock();

    // Sell a product
    void sell();

    // Add another method of your own that would be common behavior for a store
    int getRemainingStock();

    boolean isOpen();

    String getItem();
}
