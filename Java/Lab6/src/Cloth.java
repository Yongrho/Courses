public class Cloth implements Store {
    private int stock;
    boolean isOpen = false;
    private final int SELL_PER_UNIT = 10;
    private final String PRODUCT = "Jeans";

    public Cloth(int stock) {
        this.stock = stock;
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
            System.out.println("closed...");
        } else {
            System.out.println("We are closed.");
        }
    }

    @Override
    public void restock() {
        System.out.println("restocking..");
    }

    @Override
    public void sell() {
        if (stock > 0) {
            stock -= SELL_PER_UNIT;
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
        return PRODUCT;
    }

    public String getSize() {
        return "S/L/XL";
    }

}
