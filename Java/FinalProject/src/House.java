public class House extends Property {
    public House(String address, int bedrooms, int bathrooms) {
        super(address, bedrooms, bathrooms);
    }

    @Override
    public boolean parking() {
        return true;
    }

    @Override
    public boolean smoking() {
        return true;
    }

    @Override
    public boolean pet() {
        return true;
    }

    @Override
    public EnumPropertyType getPropertyType() {
        return EnumPropertyType.HOUSE;
    }

    public int getAverageRent(int bedrooms) {
        if (isRent()) {
            int rent = 0;
            switch (bedrooms) {
                case 1:
                    return 800;
                case 2:
                    return 1600;
                case 3:
                    return 1800;
                case 4:
                    return 2000;
                default:
                    return -1;
            }
        }
        return -1;
    }

    @Override
    public boolean includeUtility() {
        return false;
    }
}
