/**
 * The entry point of the program.
 */
public class Main {
    /**
     * The main function of the program.
     *
     * @param args  A collection of string values representing program arguments
     */
    public static void main(String[] args) {
        // run spaceString() method to insert a space between characters
        System.out.println(StringUtils.spaceString("Hello"));

        // run switchCase() method to transform odd character to uppercase
        System.out.println(StringUtils.switchCase("Good Morning"));

        // run reverse() method to reverse a string
        System.out.println(StringUtils.reverse("Nick. How are you?"));

        // run getAccumulatedCharacters() method to show the total count of characters which are passed as arguments
        System.out.println(StringUtils.getAccumulatedCharacters());
    }
}
