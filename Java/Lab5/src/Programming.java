import java.util.Random;

public class Programming extends Course {
    private final String[] arrayProgrammingHighlightedTopic = {"Java", "Python"};

    public Programming(String name, boolean hasTextbook) {
        super(name, hasTextbook);
    }

    @Override
    public void explain() {
        System.out.println("Welcome to Programming(Java) world.");
    }
}
