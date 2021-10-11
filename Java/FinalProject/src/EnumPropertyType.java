public enum EnumPropertyType {
    HOUSE("House"),
    APARTMENT("Apartment"),
    CONDO("Condo");

    final String name;

    EnumPropertyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
