import java.util.Random;

public class Math extends Course {
    private final String[] arrayMathHighlightedTopic = {"Algebra", "Geometry"};
    private SchoolSupply[] schoolSupplies;

    public Math(String name, boolean hasTextbook) {
        super(name, hasTextbook);
        this.schoolSupplies = null;
    }

    public void addSchoolSupply(SchoolSupply[] schoolSupplies) {
        this.schoolSupplies = schoolSupplies;
    }

    public void addSchoolSupply(SchoolSupply supply) {
        schoolSupplies[schoolSupplies.length + 1] = supply;
    }

    public void listSupplies() {
        for (SchoolSupply supply : schoolSupplies) {
            System.out.println(supply);
        }
    }

    @Override
    public void code() {
        System.out.println("MATH1000");
    }

    @Override
    public void explain() {
        System.out.println("Welcome to Math world.");
    }

    @Override
    public String getHighlightedTopic() {
        return "You are going to learn about "
                + arrayMathHighlightedTopic[new Random().nextInt(arrayMathHighlightedTopic.length)]
                + ". I can assure you it is exciting";
    }
}
