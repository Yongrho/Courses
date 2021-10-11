public class Course {
    private final String name;
    private final boolean hasTextbook;

    public Course(String name, boolean hasTextbook) {
        this.name = name;
        this.hasTextbook = hasTextbook;
    }

    public String getName() {
        return name;
    }

    public boolean hasTextbook() {
        return hasTextbook;
    }

    public void code() {
        System.out.println("COMM1000");
    }

    public String getHighlightedTopic() {
        return "Depends on the course, but I can assure you it is exciting!";
    }

    public void explain() {
        System.out.println("You will be exciting from this course.");
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", hasTextbook=" + hasTextbook +
                '}';
    }
}
