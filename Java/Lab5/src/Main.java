import java.util.ArrayList;

public class Main {
    /**
        Create a Main class with a main function
        Create a class of your choice which will act as a superclass for two more subclasses
            Add at least three methods in the superclass with default implementations (ideally each method
            prints something to the console to help with testing purposes later)
        Create three subclasses that will inherit from your superclass
            In your first subclass, override all of the methods in the superclass
            In your second subclass, don't override any methods of the superclass, instead add a new method
            unique to that subclass
            In your third subclass, only override one of the three methods from the superclass
        In your main function, instantiate one instance of each of your classes (superclass and subclasses)
        Add all of your objects to a collection (ArrayList or primitive array)
            Loop through the collection of objects, calling each of the methods of the superclass on each instance
            Observe the results
    */

    public static void main(String[] args) {
        // add school supplies
        SchoolSupply pencil = new SchoolSupply("Pencil", 1);
        SchoolSupply eraser = new SchoolSupply("Eraser", 1);
        SchoolSupply sharpener = new SchoolSupply("Sharpener", 2);
        SchoolSupply ruler = new SchoolSupply("Ruler", 2);
        SchoolSupply calculator = new SchoolSupply("Calculator", 3);

        Course genericCourse = new Course("Generic Course", false);
        Math math = new Math("Math", true);
        Biology biology = new Biology("Biology", true);
        Programming prog = new Programming("Programming", false);

        math.addSchoolSupply(new SchoolSupply[] {pencil, eraser, sharpener, ruler, calculator});

        // We can put all of our course into a single collection of Course types
        ArrayList<Course> arrayCourses = new ArrayList<>();
        arrayCourses.add(genericCourse);

        // In your first subclass, override all of the methods in the superclass
        arrayCourses.add(math);

        // In your second subclass, don't override any methods of the superclass, instead add a new method
        // unique to that subclass
        arrayCourses.add(biology);

        // In your third subclass, only override one of the three methods from the superclass
        arrayCourses.add(prog);

        // Loop through the collection of objects, calling each of the methods of the superclass on each instance
        // Observe the results
        for (Course course : arrayCourses) {
            course.code();
            course.explain();
            System.out.println("HighlightedTopic: " + course.getHighlightedTopic());
        }
    }
}
