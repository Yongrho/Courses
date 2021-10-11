public class Condo extends Property {
    private boolean parking = true;
    private boolean smoking = false;
    private boolean pet = false;

    public Condo(String address, int bedrooms, int bathrooms) {
        super(address, bedrooms, bathrooms);
    }

    public void enableParking(boolean enable) {
        this.parking = enable;
    }

    public void enableSmoking(boolean enable) {
        this.smoking = enable;
    }

    public void enablePet(boolean enable) {
        this.pet = enable;
    }

    @Override
    public boolean parking() {
        return parking;
    }

    @Override
    public boolean smoking() {
        return smoking;
    }

    @Override
    public boolean pet() {
        return pet;
    }

    @Override
    public EnumPropertyType getPropertyType() {
        return EnumPropertyType.CONDO;
    }

    public int getAverageRent(int bedrooms) {
        if (isRent()) {
            int rent = 0;
            switch (bedrooms) {
                case 1:
                    return 1000;
                case 2:
                    return 1300;
                case 3:
                    return 1600;
                default:
                    return -1;
            }
        }
        return -1;
    }

    @Override
    public boolean includeUtility() {
        if (isRent()) {
            return true;
        }
        return false;
    }
}
