public class Grocery implements Store {
    private int stock = 10;
    boolean isOpen = false;
    private final String item;
    private String category;

    public Grocery(String item) {
        this.item = item;
    }

    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
        }
        System.out.println("open...");
    }

    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
        }
        System.out.println("closed...");
    }

    @Override
    public void restock() {
        System.out.println("restocking..");
    }

    @Override
    public void sell() {
        if (stock > 0) {
            stock -= 1;
            System.out.println("sold");
        } else {
            System.out.println("sold out");
        }
    }

    @Override
    public int getRemainingStock() {
        return stock;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public String getItem() {
        return item;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
