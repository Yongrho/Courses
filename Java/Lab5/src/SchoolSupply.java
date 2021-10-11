public class SchoolSupply {
    private final String name;
    private final int cost;

    public SchoolSupply(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "SchoolSupply{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
