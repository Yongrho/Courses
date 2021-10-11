public abstract class Property implements PropertyType {
    private final String address;
    private final int bedrooms;
    private final int bathrooms;
    private float marketValue;
    private boolean isRent = false;
    private int rentPerMonth = 0;
    private Owner owner;

    public Property(String address, int bedrooms, int bathrooms) {
        this.address = address;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    abstract public boolean parking();
    abstract public boolean smoking();
    abstract public boolean pet();

    public float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public boolean isRent() {
        return isRent;
    }

    public int getRentPerMonth() {
        return rentPerMonth;
    }

    public void setRentPerMonth(int amount) {
        if (amount < 50) {
            System.out.println("Please check the rent.");
            return;
        }
        rentPerMonth = amount;
        isRent = true;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    @Override
    public String toString() {
        return "Property{" +
                "address='" + address + '\'' +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", marketValue=" + marketValue +
                ", isRent=" + isRent +
                '}';
    }
}
