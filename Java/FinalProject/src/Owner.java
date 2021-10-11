public class Owner {
    private final String name;
    private final String telephone;
    private final OwnerType ownerType;

    public Owner(String name, String telephone, OwnerType ownerType) {
        this.name = name;
        this.telephone = telephone;
        this.ownerType = ownerType;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ownerType=" + ownerType +
                '}';
    }
}
