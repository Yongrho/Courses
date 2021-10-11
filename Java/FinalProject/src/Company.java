public class Company extends Owner {
    private String residentManager = null;

    public Company(String name, String telephone, OwnerType ownerType) {
        super(name, telephone, ownerType);
    }

    public String getResidentManager() {
        return residentManager;
    }

    public void setResidentManager(String residentManager) {
        this.residentManager = residentManager;
    }
}
