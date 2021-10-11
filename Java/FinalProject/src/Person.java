public class Person extends Owner{
    private float seedMoney;

    public Person(String name, String telephone, OwnerType ownerType) {
        super(name, telephone, ownerType);
    }

    public void setSeedMoney(int money) {
        seedMoney = money;
    }

    public float getSeedMoney() {
        return seedMoney;
    }
}
